package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Channel;
import model.User;
import utilities.AppConstants;

/**
 * Servlet implementation class ChannelServlet
 */
@WebServlet("/ChannelServlet")
public class ChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String query ="";                                              
		System.out.println(query); //TODO:earse later
		Context context;
		ResultSet resultset = null;
		Connection connection = null;
		BasicDataSource datasource;
		PreparedStatement statement = null;
		ArrayList<String> rs = new ArrayList<String>();
		
		try 
		{
			/*reading the httpRequest message body*/
		    StringBuilder buffer = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line); 
		        buffer.append(line);
		    }
		    
		    /*reading the httpRequest query parameter*/
		    query = request.getParameter("query");
			context = new InitialContext();
			datasource = (BasicDataSource)context.lookup(getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);

			
			switch(query)
			{
				case "select": /*DOESN'T WORK, REVISE !!*/
					query = AppConstants.SELECT_ALL_USERS_STMT;
					resultset = statement.executeQuery(query);
					rs.add("{\n");
					while (resultset.next())
					{
						rs.add("{\n");
						rs.add("username:" + (char)34 + resultset.getString(1) + (char)34 + (char)44);
						rs.add("password:" + (char)34 + resultset.getString(2) + (char)34 + (char)44);
						rs.add("nickname:" + (char)34 + resultset.getString(3) + (char)34 + (char)44);
						rs.add("description:" + (char)34 + resultset.getString(4) + (char)34 + (char)44);
						rs.add("photo:" + (char)34 + resultset.getString(5) + (char)34);
						rs.add("}"); 
					}
					rs.add("}");	
					
					break;
					
				case "insert":
					try 
					{
						Gson gson = new GsonBuilder().create();
						Channel channel = gson.fromJson(buffer.toString(), Channel.class); 
						/*System.out.println(channel.getUserName());
						System.out.println(channel.getPassword());
						System.out.println(channel.getnickName());
						System.out.println(channel.getDescription());
						System.out.println(channel.getPhoto()); */
						
						if(datasource != null)
						{
							connection = datasource.getConnection();
							statement = connection.prepareStatement(AppConstants.INSERT_USERS_STMT);
							/*statement.setString(1, channel.getUserName());
							statement.setString(2, channel.getPassword());
							statement.setString(3, channel.getnickName());
							statement.setString(4, channel.getDescription());
							statement.setString(5, channel.getPhoto());*/
						}
						
						statement.executeUpdate(); 
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					} 
					break;
					
				case "update":
					query="";
					break;
				default:
					break;
			}
			
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//response.getWriter().append("Served at: ").append(uri);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(rs);
	}

}
