package utilities;

import java.util.ArrayList;

public final class Parser 
{
	public static ArrayList<String> getValues(String input)
	{
		char pa = 65;
		int lastIndex = 1, size;
		ArrayList<String> result = new ArrayList<String>();
		String[] values;
		String[] pairs;
		
		try
		{
			//split the json string according to ':' char
			System.out.println(input);
			input = input.substring(1, input.length()-1); 
			input.replace((char)34, (char)48);		//replace " with space char
			input.replaceAll("\"" , "*"); 
			System.out.println(input);
			values = input.split(":");
			//System.out.println(values);
			size = values.length;
			System.out.println(size);
			
			//split all partitioned strings into pairs, and select the first one
			for(int i=0;i<size;i++){
				{ 
					if (i==size-1) lastIndex = 2;
					
					System.out.println(values[i]);
					pairs = values[i].split(",");
					System.out.println(values[i]);
					//System.out.println(pairs[0] = pairs[0].substring(1, pairs[0].length()-lastIndex)); 
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
