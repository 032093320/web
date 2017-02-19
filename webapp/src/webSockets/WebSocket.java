package webSockets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.server.ServerEndpoint;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import dataBase.DataBase;
import model.Message;
import utilities.AppConstants;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import com.google.gson.*; 

@ServerEndpoint("/message")
public class WebSocket 
{
	ResultSet resultset = null;
	Connection connection = null;
	BasicDataSource datasource;
	javax.sql.DataSource source;
	Statement statement = null;
	Context context = null;
	//DataBase db;
	
	//tracks all active sessions
	private static Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	//constructor
	public WebSocket()
	{
		System.out.println("web socket instanstiated"); 
		//db = new DataBase();
	}
	
	@OnOpen
	public void addClient(Session session) throws IOException{
		allSessions.add(session);
	}
	
	@OnMessage
	public void message(String msg, Session session) throws IOException, NamingException
	{
		String command = "insert into message values(?,?,?,?,?,?,?)";
		String contextString = "";
		DataBase db;
		int maxMsgID = 0;
		int maxid;
		Connection connection = null;
		PreparedStatement statement = null;
		
		System.out.println("message: " + msg);
		
		try
		{
			Gson gson = new GsonBuilder().create();
			Message message	= gson.fromJson(msg, Message.class); 
			  
            //read the <env-entry> named DB_DATASOURCE value, 
			//and construct the context string:"java:comp/env/jdbc/UsersDatasourceOpen"
			Context env = (Context)new InitialContext().lookup("java:comp/env");
			contextString = (String)env.lookup(AppConstants.DB_DATASOURCE);
			
			//find max msg id
			db = new DataBase();
			maxid = db.findMaxMessageID();
			
			//Get a connection and execute the query
			context = new InitialContext();
			datasource = (BasicDataSource)context.lookup(contextString + AppConstants.OPEN);

            if (datasource != null) 
            {	
            	connection = datasource.getConnection();
            	statement = connection.prepareStatement(command); 
            	
            	statement.setInt(1, maxid+1);
            	statement.setString(2, message.getUser());
            	statement.setString(3, message.getTimeStamp());
            	statement.setString(4, message.getContent());
            	statement.setInt(5, message.getIsReplyable());
            	statement.setInt(6, message.getReplyedTo());
            	statement.setInt(7, message.getOffset());
            	
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
		if (session.isOpen())
		{
			//tracks all active sessions
			session.getBasicRemote().sendText("Received message:" + msg); 
		}
	}
	
	@OnClose
	public void removeClient(Session session) throws IOException{
		allSessions.remove(session);
	}
	
	@OnError
	public void error(Throwable t) throws IOException
	{
		t.printStackTrace();
	}

}
