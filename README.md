# web
***********************************************************************
*																	  *
*							README FILE								  *
* 																	  *
***********************************************************************

1. Datae base name is : UsersDB 
2. To access the DB; user name:"user" password:1234
3. DB url = "localhost:/1527/jdbc:derby:UsersDB;create=true";
4. DB type: "javax.sql.DataSource"

set system varialbe DERBY_HOME = C:\Apache\db-derby-10.12.1.1-bin
5. To use DB from console (use ij tool):
	5.1 Access ij tool: java -jar %DERBY_HOME%\lib\derbyrun.jar ij
	5.2 create DB connection: CONNECT 'jdbc:derby:firstdb;create=true';
	5.3 To dissconnect from the DB : disconnect
	5.4 To exit the ij tool: exit
	
