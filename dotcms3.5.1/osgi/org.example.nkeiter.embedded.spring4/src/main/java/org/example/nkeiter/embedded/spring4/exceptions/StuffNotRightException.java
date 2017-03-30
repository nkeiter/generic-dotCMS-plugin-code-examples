package org.example.nkeiter.embedded.spring4.exceptions;

public class StuffNotRightException extends Exception
{
	private static final long serialVersionUID = -2014900660911344816L;

	private String reason;

	public StuffNotRightException( String reason )
	{
		this.reason = reason;
	}

	public String getReason()
	{
		return this.reason;
	}
}
