package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Message;
import utilities.AppConstants;

public class DataBase 
{
	ResultSet resultset = null;
	Connection connection = null;
	BasicDataSource datasource;
	javax.sql.DataSource source;
	Statement statement = null;
	Context context = null;
	
	/*
	 * constructor
	 */
	public DataBase()
	{
		System.out.println("ctor instantiated"); 
	}
	
	public Connection openConnection() throws Exception
	{
		int result = 0;
		String contextString = "";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			//read the <env-entry> named DB_DATASOURCE value, 
			//and construct the context string:"java:comp/env/jdbc/UsersDatasourceOpen"
			Context env = (Context)new InitialContext().lookup("java:comp/env");
			contextString = (String)env.lookup(AppConstants.DB_DATASOURCE);
			
			//Get a connection and execute the query
			context = new InitialContext();
			datasource = (BasicDataSource)context.lookup(contextString + AppConstants.OPEN);
        	connection = datasource.getConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection = null;
		}
		
		return connection;
	}
	
	public void insert(String table, String msg) throws Exception 
	{
		String command = "insert into " + table + " values(?,?,?,?,?,?,?)";
		String contextString = "";
		int maxMsgID = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			Gson gson = new GsonBuilder().create();
			Message message	= gson.fromJson(msg, Message.class); 
		
			//read the <env-entry> named DB_DATASOURCE value, 
			//and construct the context string:"java:comp/env/jdbc/UsersDatasourceOpen"
			Context env = (Context)new InitialContext().lookup("java:comp/env");
			contextString = (String)env.lookup(AppConstants.DB_DATASOURCE);
		
			//Get a connection and execute the query
			context = new InitialContext();
			datasource = (BasicDataSource)context.lookup(contextString + AppConstants.OPEN);

			if (datasource != null) 
			{	
				connection = datasource.getConnection();
				statement = connection.prepareStatement(command); 
				
				switch(table)
				{
				case("message"):
					statement.setInt(1, message.getId());
					statement.setString(2, message.getUser());
					statement.setString(3, message.getTimeStamp());
					statement.setString(4, message.getContent());
					statement.setInt(5, message.getIsReplyable());
					statement.setInt(6, message.getReplyedTo());
					statement.setInt(7, message.getOffset());
					break;
				
				case("user"):
					break;
				
				case("channel"):
					break;
				}
        	
				statement.executeUpdate();
			}
			else
			{
				System.out.println("failed");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{				
				context.close();
				if (statement != null && !statement.isClosed()) statement.close();
				if (!connection.isClosed()) connection.close();	
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/*
	 * find the max. id number in message table
	 */
	public int findMaxMessageID() throws Exception
	{
		int max = 0;
		String command = "select max(ID) from message";
		ResultSet result = null;
		Connection conn = null;
		PreparedStatement statement = null;
		
		try
		{
			if ((conn = openConnection()) != null)
			{
				System.out.println("command:"+command); 
				statement = conn.prepareStatement(command); 
				result = statement.executeQuery();
				System.out.println("last index:" + result);
			}
			
			while(result.next())
				max = result.getInt(1); 
		}
		catch(Exception e)
		{
			System.out.println("exception while trying to get max id");
			e.printStackTrace(); 
		}
		finally
		{
			if (statement!=null) statement.close();
			if(result!=null) result.close();
			if(connection != null) connection.close();
		}
		
		return max;
	}
	
	
	/**
	 * 
	 * @param in string in json format
	 * @return an array of strings 
	 * @throws Exception
	 */
	public String[] getValues(String in) throws Exception
	{
		
		String[] values = in.split(":"); 
		for(int i=1;i<values.length;i++)
		{
			if(i%2==0)
			{
				System.out.println(values[i]);
				values[i]=values[i].split(",")[0];
			}
				 
			System.out.println(values[i]);
		}
		
		return values;
	}
}
