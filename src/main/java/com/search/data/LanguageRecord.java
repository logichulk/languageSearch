package com.search.data;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a single record about a language in the given data.json.
 * 
 * @author tushar
 *
 */
public final class LanguageRecord implements IRecord<LanguageRecord>
{	
	@SearchField 
	@SerializedName("Name")
	public String name = null;
	
	@SearchField 
	@SerializedName("Type")
	public String type = null;
	
	@SearchField 
	@SerializedName("Designed by")
	public String designedBy = null;
	
	/**
	 * Gson uses a no-arg constructor so this is what it will be calling when asked to convert JSON to object(s). 
	 */
	public LanguageRecord() 
	{
		super();
	}
	
	/** 
	 * @return name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * @return type
	 */
	public String getType() 
	{
		return type;
	}
	
	/**
	 * @return designedBy
	 */
	public String getDesignedBy() 
	{
		return designedBy;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof LanguageRecord)
		{
			LanguageRecord lr = (LanguageRecord)obj;
			
			if(name != null && lr.getName() != null)
				return name.equals(lr.getName());
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode() + 535;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder("\nName: " + name);
		
		builder.append("\nType: ");		
		builder.append(type.replace("[", "").replace("]", "")
				.replaceAll("\"", ""));
				
		builder.append("\nDesigned by: ");
		builder.append(type.replace("[", "").replace("]", "")
				.replaceAll("\"", ""));
		
		builder.append("\n");
		
		return builder.toString();
	}

	/**
	 * Will be used implicitly by Collections.sort()
	 */
	public int compareTo(LanguageRecord o) 
	{
		return name.compareTo(o.name);
	}
}
