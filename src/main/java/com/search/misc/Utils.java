package com.search.misc;

import com.google.gson.Gson;

/**
 * Utility class and common methods.
 * 
 * @author tushar
 *
 */
public class Utils 
{
	private static final Gson GSON = new Gson();
	
	/**
	 * Counts number of algorithm threads that are currently running.
	 */
	private static Integer algoThreads = 0;
	
	public static String convertObjectToJSONString(Object obj)
	{
		return GSON.toJson(obj);
	}
	
	public static <T> T convertJSONStringToObject(String json, Class<T> clazz)
	{
		return GSON.fromJson(json, clazz);
	}

	public static synchronized void updateAlgoThreads(boolean increment) 
	{
		if(increment)
			algoThreads ++;
		else
			algoThreads --;
	}
	
	public static boolean noAlgoThreadsRunning()
	{
		return algoThreads == 0;
	}
}
