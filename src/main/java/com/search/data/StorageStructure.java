package com.search.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.search.threads.ThreadPool;

/**
 * Basically a Concurrent Hash Map so that multiple threads can access it while loading the data. 
 * 
 * Responsible for providing the space to store relations and exposing methods to interact with this data.
 * 
 * @author tushar
 *
 */
public class StorageStructure extends ConcurrentHashMap<String, Set<Integer>>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2491231403991409329L;
	
	@SuppressWarnings("rawtypes")
	private static Map<Integer, IRecord> BY_ID_MAP = new HashMap<Integer, IRecord>();
	private static StorageStructure SINGLETON = null;
	private static Integer idCounter = 0;
	
	/**
	 * Implements Double-chained locking for initialization of singleton instance.
	 * 
	 * @return StorageStructure singleton instance
	 */
	public static StorageStructure get()
	{
		if(SINGLETON == null)
		{
			synchronized(StorageStructure.class)
			{
				if(SINGLETON == null)
					SINGLETON = new StorageStructure();
			}
		}
		
		return SINGLETON;
	}
	
	/**
	 * Works on a new IRecord object.
	 * 
	 * @param record - a single abstract data record.
	 */
	@SuppressWarnings("rawtypes")
	public void insertAndAnalyzeNew(IRecord record)
	{		
		BY_ID_MAP.put(++ idCounter, record);
		
		// Call to the executor framework to run the algorithm thread.
		ThreadPool.get().submit(new IndexOnTokenAlgorithm(idCounter, record));
	}

	/**
	 * Returns a set of object their ids.
	 * 
	 * @param ids - a set of integer keys, each of which is a unique identifier for a data record.
	 * @return Set of abstract data records.
	 */
	@SuppressWarnings("rawtypes")
	public Set<IRecord> getByIds(Set<Integer> ids)
	{
		Set<IRecord> resultSet = new TreeSet<IRecord>();
		
		for(Integer id : ids)
		{
			if(BY_ID_MAP.containsKey(id))
				resultSet.add((IRecord)BY_ID_MAP.get(id));
		}
				
		return resultSet;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder("[Storage Structure with " + keySet().size() + " entries.]");
		
		for(String key : keySet())
		{
			sb.append("\n - " + key + " : ").append(get(key));
		}
		
		return sb.toString();
	}
}
