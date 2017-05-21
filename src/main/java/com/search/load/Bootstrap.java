package com.search.load;

import java.util.Arrays;

import com.search.data.LanguageRecord;
import com.search.data.StorageStructure;
import com.search.misc.Utils;

/**
 * Called at start up time to initialize the system.
 * 
 * @author tushar
 *
 */
public class Bootstrap 
{
	private static boolean loaded = false;
	
	/**
	 * Loads data from data.json into the system and analyzes it during the parsing stage.
	 * 
	 * @return loaded
	 */
	public static boolean loadOnce()
	{
		if(loaded)
			return false;
		else
		{
			// 1. Read. This can be from a file or an outside source in the future.
			String rawJSON = readJSON();
			
			// 2. Parse. All the loading and analyzing logic runs in this stage when the
			// objects are created from JSON. This is done by wiring next steps with the
			// constructor calls and using multi-threading to parallelize the whole exercise.
			// (Please check LanguageRecord.java's constructor).
			LanguageRecord[] records = Utils.convertJSONStringToObject(rawJSON, LanguageRecord[].class);
			
			Arrays.stream(records).forEach(record -> StorageStructure.get().insertAndAnalyzeNew(record));			
			
			return loaded = true;
		}
	}
	
	private static String readJSON() 
	{
		return DataStringHolder.RAW_JSON_DATA;
	}
}
