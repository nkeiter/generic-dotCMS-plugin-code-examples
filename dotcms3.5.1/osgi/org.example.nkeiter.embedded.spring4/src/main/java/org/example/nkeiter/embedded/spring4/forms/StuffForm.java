package org.example.nkeiter.embedded.spring4.forms;

import com.dotcms.repackage.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.dotmarketing.util.UtilMethods;

import org.example.nkeiter.embedded.spring4.exceptions.InvalidFormException;

@JsonIgnoreProperties( ignoreUnknown = true )
public class StuffForm
{
	private String stuffId;

	public StuffForm()
	{
		// do nothing
	}

	public String getStuffId()
	{
		return this.stuffId;
	}

	public StuffForm setStuffId( String stuffId )
	{
		this.stuffId = stuffId;
		return this;
	}

	public void validate() throws InvalidFormException
	{
		// validate stuffIds
		if ( !UtilMethods.isSet( this.stuffId ) )
		{
			throw new InvalidFormException( "Please select stuff." );
		}
	}
}
