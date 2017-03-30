package org.example.nkeiter.embedded.spring4.exceptions;

public class InvalidFormException extends Exception
{
	private static final long serialVersionUID = -7922658376220141957L;

	private String reason;

	public InvalidFormException( String reason )
	{
		this.reason = reason;
	}

	public String getReason()
	{
		return this.reason;
	}
}
