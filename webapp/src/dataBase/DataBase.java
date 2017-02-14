package dataBase;

import java.util.ArrayList;

public class DataBase {
	
	public int openConnection() throws Exception
	{
		int result = 0;
		
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(String table, ArrayList<String> values) throws Exception 
	{
		int result = 0;
		int size = values.size();
		String query = "";
		
		try
		{
			query = "INSERT INTO " 
					+ table
					+ " VALUES(";
			for(int i = 0; i < size; i++){
				query = query + "'" + values.get(i) + "'"; 
				if(i<size - 1)
					query = query + ", ";
			}
			query.concat(")"); 		

		}
		catch(Exception e){
			e.printStackTrace();
			result =-1;
		}
		
		return result;
	}

}
