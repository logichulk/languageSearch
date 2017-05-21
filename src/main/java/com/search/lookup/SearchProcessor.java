package com.search.lookup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.search.data.IRecord;
import com.search.data.StorageStructure;
import com.search.misc.Utils;

/**
 * Handles the business logic of breaking down a search term. looking it up in the
 * Storage Structure, working on it using principles of set theory and preparing the results.
 * 
 * @author tushar
 *
 */
public class SearchProcessor 
{
	private static SearchProcessor SINGLETON = null;
	
	/**
	 * Implements Double-chained locking for initialization of singleton instance.
	 * 
	 * @return SearchProcessor singleton instance
	 */
	public static SearchProcessor get()
	{
		if(SINGLETON == null)
		{
			synchronized(SearchProcessor.class)
			{
				if(SINGLETON == null)
					SINGLETON = new SearchProcessor();
			}
		}
		
		return SINGLETON;
	}
	
	/**
	 * Works on the principles of set theory and boolean algebra. Provides exact matches.
	 * 
	 * @param searchTerm - the text entered by the user.
	 * @return Search Results in the form of JSON string.
	 */
	@SuppressWarnings("rawtypes")
	public String getResults(String searchTerm)
	{
		String[] searchTermParts = searchTerm.split(" ");
		
		List<Set<Integer>> combinedListOfPosSets = new ArrayList<>();
		List<Set<Integer>> combinedListOfNegSets = new ArrayList<>();
		
		boolean positive = true;
		
		for(String part : searchTermParts)
		{
			//Take care of the '-' sign operator.
			if(part.startsWith("-"))
			{
				
				positive = false;
				part = part.substring(1, part.length());
			}
			else
			{
				positive = true;
			}
			
			part = part.toLowerCase().trim();//Standardize in the expected key format.
				
			Set<Integer> found = StorageStructure.get().get(part);
			
			if(found != null)
			{
				if(positive)
					combinedListOfPosSets.add(found);
				else
					combinedListOfNegSets.add(found);
			}
				
		}
		
		Set<Integer> aggTotSet = findIntersectionOfSets(combinedListOfPosSets);
		Set<Integer> aggNegSet = findUnionOfSets(combinedListOfNegSets);
		
		aggTotSet.removeAll(aggNegSet);//Remove the results for the search with the '-' prefix.
		
		Set<IRecord> langResultSet = StorageStructure.get().getByIds(aggTotSet);
		
		return Utils.convertObjectToJSONString(langResultSet);
	}
	
	/**
	 * Finds the intersection of multiple sets of values.
	 * 
	 * @param setList
	 * @return
	 */
	private Set<Integer> findIntersectionOfSets(List<Set<Integer>> setList)
	{
		Set<Integer> intersectionSet = new HashSet<Integer>();
		boolean firstLoop = true;
		
		for(Set<Integer> intSet : setList)
		{
			if(firstLoop)
			{
				intersectionSet.addAll(intSet);
				firstLoop = false;
			}
			else
				intersectionSet.retainAll(intSet);
		}
		
		return intersectionSet;
	}
	
	/**
	 * Finds the union of set. Used for negating.
	 * 
	 * @param setList
	 * @return
	 */
	private Set<Integer> findUnionOfSets(List<Set<Integer>> setList)
	{
		Set<Integer> unionSet = new HashSet<Integer>();
		
		for(Set<Integer> set : setList)
		{
			unionSet.addAll(set);
		}
		
		return unionSet;
	}
}
