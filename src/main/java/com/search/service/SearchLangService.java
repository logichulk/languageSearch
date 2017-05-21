package com.search.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.search.load.Bootstrap;
import com.search.lookup.SearchProcessor;

/**
 * RESTful service to expose method to get search result.
 * 
 * @author tushar
 *
 */
@Path("/search")
public class SearchLangService 
{
	static
	{
		Bootstrap.loadOnce();
	}
	
	/**
	 * Method to be called by the client side - currently the single point of contact with the server-side.
	 * 
	 * @param searchTerm - the text entered by the user on the front end.
	 * @return JSON Response
	 */
	@GET
	@Path("/{param}")
	public Response getResults(@PathParam("param") String searchTerm)
	{
		String result = SearchProcessor.get().getResults(searchTerm);
		
		return Response.status(200).entity(result).build();
	}
}
