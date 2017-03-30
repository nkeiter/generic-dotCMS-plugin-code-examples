package org.example.nkeiter.embedded.spring4.exceptions;

public class StuffNotFoundException extends Exception
{
	private static final long serialVersionUID = -3483934071387382587L;

	private String stuffId;

	public StuffNotFoundException( String stuffId )
	{
		this.stuffId = stuffId;
	}

	public String getStuffId()
	{
		return stuffId;
	}
}
