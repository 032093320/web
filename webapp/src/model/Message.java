package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Message {
	
	private int id;
	private String user;
	private String timeStamp; 
	private String content;
	private int isReplyable;
	private int replyedTo;
	private int offset;
	
	/*
	 * constructor
	 */
	public Message()
	{
		
	}
	public Message(int m_id, String m_user, String m_timeStamp, String m_content, int m_isReplyable, int m_replyedTo, int m_offset)
	{
		id=m_id;
		user=m_user;
		timeStamp=m_timeStamp;
		content=m_content;
		isReplyable = m_isReplyable;
		replyedTo = m_replyedTo;
		offset = m_offset;
		System.out.println("time when message created: "+this.timeStamp); 
	}
	
	public ArrayList<String> getValues()
	{
		ArrayList<String> result = new ArrayList<String>();
		String m_id = "", m_isRep = "", m_rep = "", m_off = "";
		m_id = m_id + this.id;
		m_isRep = m_isRep + this.isReplyable;
		m_rep = m_rep + this.replyedTo;
		m_off = m_off + this.offset; 
		
		result.add(m_id); 
		result.add(user); 
		result.add(timeStamp); 
		result.add(content); 
		result.add(m_isRep); 
		result.add(m_rep); 
		result.add(m_off);  
		
		return result;
	}
	
	/*getters-setters*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsReplyable() {
		return isReplyable;
	}
	public void setIsReplyable(char isReplyable) {
		this.isReplyable = isReplyable;
	}
	public int getReplyedTo() {
		return replyedTo;
	}
	public void setReplyedTo(int replyedTo) {
		this.replyedTo = replyedTo;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
