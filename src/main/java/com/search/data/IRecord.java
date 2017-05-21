package com.search.data;

/**
 * Any kind of record. Can help in generalizing the solution to the given problem 
 * in the future.
 * 
 * @author tushar
 *
 */
public interface IRecord<T> extends Comparable<T>
{
	default void register()
	{
		StorageStructure.get().insertAndAnalyzeNew(this);
	}
}
