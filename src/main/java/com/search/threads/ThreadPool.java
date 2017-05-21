package com.search.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.search.data.StorageStructure;

/**
 * Uses the executor framework to manage a thread pool and is utilized during the analysis
 * phase for a better overall throughput/timing by using more cores.
 * 
 * @author tushar
 *
 */
public class ThreadPool 
{
	private static ThreadPool SINGLETON = null;
	
	private ExecutorService executor = null;
	
	/**
	 * Implements Double-chained locking for initialization of singleton instance.
	 * 
	 * @return ThreadPool singleton instance
	 */
	public static ThreadPool get()
	{
		if(SINGLETON == null)
		{
			synchronized(StorageStructure.class)
			{
				if(SINGLETON == null)
					SINGLETON = new ThreadPool();
				
				//Up to 4 threads for Quad-core processor.
				SINGLETON.executor = Executors.newFixedThreadPool(4);
			}
		}
		
		return SINGLETON;
	}
	
	public void submit(Runnable task)
	{
		executor.submit(task);
	}
}
