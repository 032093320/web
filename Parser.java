package utilities;

import java.util.ArrayList;

public final class Parser 
{
	public static ArrayList<String> getValues(String input)
	{
		int lastIndex = 1, size;
		ArrayList<String> result = new ArrayList<String>();
		String[] values;
		String[] pairs;
		
		try
		{
			//split the json string according to ':' char
			values = input.split(":");
			size = values.length;
			
			//split all partitioned strings into pairs, and select the first one
			for(int i=1;i<size;i++){
				{ 
					if (i==size-1) lastIndex = 2;
					pairs = values[i].split(",");
					System.out.println(pairs[0] = pairs[0].substring(1, pairs[0].length()-lastIndex)); 
					result.add(pairs[0]); 	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}

		return result;
	}
}
