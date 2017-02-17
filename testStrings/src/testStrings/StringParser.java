package testStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class StringParser 
{
    private static int size;
	private static int lastIndex;
	private static ArrayList<String> result;
	private static ArrayList<KeyValue> map;
    private String[] values;

	public static void main(String[] args) throws Exception 
    { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter String");
        String s = br.readLine();
        int counter = 0;
        System.out.print("Enter Integer:");
        result = new ArrayList<String>();
        Map<String,String> map;
		boolean key_flag = false;
		boolean val_flag = false;  
		String key = "", val = ""; 
		
        /*String inputString = "{name:" + "mickey," + "password:" + "1234}";
        String s=inputString;*/
        
        try
        { 
			System.out.println(s);
			map = new HashMap<String,String>(); 
			//String[] values = s.split(":");
			//System.out.println(values);
			//size = values.length;
			//System.out.println(size);
			
			//1. remove first and last characters {}
			s = s.substring(1, s.length()-1); 
			
			//2. find key-value
			size = s.length();

			while((size-counter) != 0)
			{				
				char c = s.charAt(counter);
				
				//2.1 found " char
				if ((int)c == 34) 
				{
					key = getName(s, counter++); 
					counter = counter + key.length(); 
				}
				//2.2 found second : char
				if (((int)c == 58))
				{
					if ((int)(s.charAt(counter + 1)) == 34) val = getName(s,counter);	//value is string
					else val = getName(s, counter);
					
					map.put(key, val);
					counter = counter + val.length() + 1; 
				}
				else
					counter++;
			}
        }
        catch(NumberFormatException nfe)
        {
            System.err.println("Invalid Format!");
        }
    }

	public static String getName(String input, int pos) throws Exception
	{
		String name = "";
		char c;		
		int position = pos + 1;
		try
		{
			while((int)(c = input.charAt(position)) != 34)
			{
				if(position > (input.length())) throw new Exception(); 
				name += c;
				position++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return name;
	}
	
	public static String getValue(String input, int pos) throws Exception
	{
		String value = "";
		int position = pos;
		char c;
		
		try
		{
			while((int)(c = input.charAt(position)) != 44 && input.charAt(position + 1) != 34)
			{
				if (position > 500) throw new Exception(); 
				value += c;
				position++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
}
