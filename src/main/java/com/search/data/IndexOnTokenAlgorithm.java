package com.search.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.search.misc.Utils;

/**
 * This uses hashing to index and populates into Storage structure.
 * 
 * @author tushar
 *
 */
@SuppressWarnings("rawtypes")
public class IndexOnTokenAlgorithm implements Runnable
{
	Integer id;
	IRecord record;
	
	IndexOnTokenAlgorithm(Integer id, IRecord record)
	{
		this.id = id;
		this.record = record;
	}
	
	@Override
	public void run() 
	{
		try
		{
			Utils.updateAlgoThreads(true);
			
			List<String> valueList = fetchSearchFieldValues(record);
			
			for(String str : valueList)
			{
				if(str == null)
					continue;
				
				String[] tokenArr = str.split(" ");//Split on space (Tokenize)
				
				for(String token : tokenArr)
				{
					token = standardize(token);//Remove commas, etc
					
					Set<Integer> idSet = StorageStructure.get().get(token);
					
					if(idSet == null)
					{
						idSet = new HashSet<Integer>();					
						StorageStructure.get().put(token, idSet);
					}
					
					idSet.add(id);
				}
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			Utils.updateAlgoThreads(false);
		}
	}
	
	/**
	 * Collects the annotated fields using reflection. This enables one to add an extra field in the record 
	 * in the future without changing much code (As it is dynamic).
	 * 
	 * @param record
	 * @return
	 */
	private List<String> fetchSearchFieldValues(IRecord record)
	{
	    // Get all declared fields.
	    Field[] fields = record.getClass().getDeclaredFields();
	    List<String> valueList = new ArrayList<String>();
	    
	    for(Field field: fields)
	    {
	        // If the field is annotated by @SearchField
	        if(field.isAnnotationPresent(SearchField.class))
	        {
	            // If the field is a String (add more checks as needed)
	            if(String.class.equals(field.getType()))
	            {
	            	try 
	            	{
						valueList.add((String)field.get(record));
					} 
	            	catch (IllegalArgumentException
							| IllegalAccessException e) 
	            	{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    }
	    
	    return valueList;
	}
	
	/**
	 * Removes unwanted characters/white spaces and convert to lower-case string.
	 * 
	 * @param input
	 * @return
	 */
	private String standardize(String input)
	{
		if(input != null)
			return input.replace(",", "").toLowerCase().trim();
		
		return null;
	}
}
