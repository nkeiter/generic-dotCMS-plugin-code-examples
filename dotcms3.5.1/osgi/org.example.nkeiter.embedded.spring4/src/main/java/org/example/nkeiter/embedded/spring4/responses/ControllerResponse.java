package org.example.nkeiter.embedded.spring4.responses;

public abstract class ControllerResponse
{
	private String message;
	private String status;

	public String getMessage()
	{
		return message;
	}

	public String getStatus()
	{
		return status;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	protected void setStatus( String status )
	{
		this.status = status;
	}
}
