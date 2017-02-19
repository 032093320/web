package utilities;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import model.User;

/**
 * A simple place to hold global application constants
 */
public interface AppConstants {

	public final String USERS = "Users";
	public final String CUSTOMERS_FILE = USERS + ".json";
	public final String NAME = "username";
	public final Type USER_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	//derby constants
	public final String DB_NAME = "DB_NAME";
	public final String DB_DATASOURCE = "DB_DATASOURCE"; 
	public final String PROTOCOL = "jdbc:derby:"; 
	public final String OPEN = "Open";
	public final String SHUTDOWN = "Shutdown";
	
	//sql statements
	//create tables
	public final String CREATE_USERS_TABLE = "CREATE TABLE USERS(" 
			+ "USERNAME varchar(10) PRIMARY KEY,"
			+ "PASSWORD varchar(8),"
			+ "NICKNAME varchar(20),"
			+ "DESCRIPTION varchar(50),"
			+ "PHOTO varchar(100))";
	
	public final String CREATE_MESSAGE_TABLE = "CREATE TABLE MESSAGE("
			+ "ID int PRIMARY KEY,"
			+ "USERNAME varchar(10),"
			+ "TIMESTAMP varchar(30),"
			+ "CONTENT varchar(500),"
			+ "REPLYABLE int,"
			+ "REPLYEDTO int,"
			+ "OFFSET int)";
	
	public final String CREATE_CHANNEL_TABLE = "CREATE TABLE CHANNEL("
			+ "NAME varchar(30) PRIMARY KEY,"
			+ "CREATOR varchar(10),"
			+ "DESCRIPTION varchar(500),"
			+ "ISPUBLIC char)"; 
	
	public final String INSERT_USERS_STMT = "INSERT INTO USERS VALUES(?,?,?,?,?)";
	public final String SELECT_ALL_USERS_STMT = "SELECT * FROM USERS";
	public final String SELECT_USER_BY_NAME_STMT = "SELECT * FROM USERS WHERE USERNAME=?";
	public final String SELECT_MAX_ID = "SELECT MAX(ID) FROM MESSAGE)";
}
