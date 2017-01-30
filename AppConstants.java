package utilities;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import model.Users;

/**
 * A simple place to hold global application constants
 */
public interface AppConstants {

	public final String USERS = "Users";
	public final String CUSTOMERS_FILE = USERS + ".json";
	public final String NAME = "username";
	public final Type USER_COLLECTION = new TypeToken<Collection<Users>>() {}.getType();
	//derby constants
	public final String DB_NAME = "DB_NAME";
	public final String DB_DATASOURCE = "DB_DATASOURCE";
	public final String PROTOCOL = "jdbc:derby:"; 
	public final String OPEN = "Open";
	public final String SHUTDOWN = "Shutdown";
	
	//sql statements
	public final String CREATE_USERS_TABLE = "CREATE TABLE USERS(" 
			+ "USERNAME varchar(10),"
			+ "PASSWORD varchar(8),"
			+ "NICKNAME varchar(20),"
			+ "DESCRIPTION varchar(50),"
			+ "PHOTO varchar(100),"
			+ "PRIMARY KEY(USERNAME))";
	public final String CREATE_MESSAGE_TABLE = "CREATE TABLE MESSAGE("
			+ "PHOTO varchar(100) PRIMARY KEY,"
			+ "NICKNAME varchar(20),"
			+ "TIME timestamp,"
			+ "CONTENT varchar(500),"
			+ "REPLYABLE char)"; 
	/*public final String CREATE_CHANNEL_TABLE = "CREATE TABEL CHANNEL("
			+ "NAME varchar(30),"
			+ "DESCRIPTION(500)"
			+ ""
			+ ")";*/
	
	public final String INSERT_USERS_STMT = "INSERT INTO USERS VALUES(?,?,?,?,?)";
	public final String SELECT_ALL_USERS_STMT = "SELECT * FROM USERS";
	public final String SELECT_USER_BY_NAME_STMT = "SELECT * FROM USERS WHERE USERNAME=?";
}
