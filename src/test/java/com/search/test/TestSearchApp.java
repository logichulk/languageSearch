package com.search.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.search.data.StorageStructure;
import com.search.load.Bootstrap;
import com.search.lookup.SearchProcessor;
import com.search.misc.Utils;

/**
 * Junit case to test the entire server-side application logic.
 * 
 * @author tushar
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSearchApp
{	
	@BeforeClass
	public static void setUp()
	{
		System.out.println("Bootstrapping before running the tests.");
		
		Bootstrap.loadOnce();
	}
	
	@Test
	public void printStorageStructure()
	{
		waitIfLoading();
		
		System.out.println("\n\n" + "printStorageStructure():\n");
		System.out.println(StorageStructure.get());
		
		assert StorageStructure.get().keySet().size() == 412;
	}

	@Test
	public void searchSingleTerms()
	{
		waitIfLoading();
		
		String resultData = null;
		String searchTerm = "Microsoft";
		
		resultData = SearchProcessor.get().getResults(searchTerm);
		
		System.out.println("\n\n" + "searchSingleTerms('" + searchTerm + "'):\n" + resultData);
		
		assert(resultData.contains("Microsoft"));//Basic test
		
		Pattern searchTermPattern = Pattern.compile(searchTerm);
		Matcher matcher = searchTermPattern.matcher(resultData);
		
		int totalMatches = 0;
		
		while(matcher.find())	
			totalMatches ++;
		
		assert(totalMatches == 8);//Result accuracy test
	}
	
	@Test
	public void searchTwoTermsSeparatedBySpace1()
	{
		waitIfLoading();
		
		String resultData = null;
		String searchTerm = "Thomas Eugene";
		
		resultData = SearchProcessor.get().getResults(searchTerm);
		
		System.out.println("\n\n" + "searchTwoTermsSeparatedBySpace1('" + searchTerm + "'):\n" + resultData);
		
		assert(resultData.contains("Thomas Eugene"));
	}
	
	@Test
	public void searchTwoTermsSeparatedBySpace2()
	{
		waitIfLoading();
		
		String resultData = null;
		String searchTerm = "Scripting Microsoft";
		
		resultData = SearchProcessor.get().getResults(searchTerm);
		
		System.out.println("\n\n" + "searchTwoTermsSeparatedBySpace2('" + searchTerm + "'):\n" + resultData);
		
		assert(resultData.contains("Scripting") && resultData.contains("Microsoft"));
	}
	
	@Test
	public void searchWithMinusOperator()
	{
		waitIfLoading();
		
		String resultData = null;
		String searchTerm = "john -array";
		
		resultData = SearchProcessor.get().getResults(searchTerm);
		
		System.out.println("\n\n" + "searchWithMinusOperator('" + searchTerm + "'):\n" + resultData);
		
		assert(resultData.contains("John") && ! resultData.contains("Array"));
	}
	
	/**
	 * Makes sure loading isn't going on so that results of test aren't inconsistent from the expected.
	 */
	private void waitIfLoading() 
	{
		while(! Utils.noAlgoThreadsRunning())
		{
			try 
			{
				Thread.sleep(100L);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
