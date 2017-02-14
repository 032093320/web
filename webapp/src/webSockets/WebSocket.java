package webSockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@ServerEndpoint("/echo")
public class WebSocket {
	
	//tracks all active sessions
	private static Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	//ctor
	public WebSocket()
	{
		System.out.println("web socket instanstiated"); 
	}
	
	@OnOpen
	public void addClient(Session session) throws IOException{
		allSessions.add(session);
	}
	
	@OnMessage
	public void echo(Session session, String msg) throws IOException
	{
		System.out.println("message: " + msg.toString()); 
		if (session.isOpen())
		{
			//tracks all active sessions
			session.getBasicRemote().sendText("Received message:" +msg); 
		}
	}
	
	@OnClose
	public void removeClient(Session session) throws IOException{
	allSessions.remove(session);
	}

}
