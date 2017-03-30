package org.example.nkeiter.embedded.spring4.responses;

public class ErrorResponse extends ControllerResponse
{
	private String error;

	public ErrorResponse( String error )
	{
		setStatus( "error" );
		this.error = error;
		setMessage( error );
	}

	public ErrorResponse( String error, String message )
	{
		setStatus( "error" );
		this.error = error;
		setMessage( message );
	}

	public String getError()
	{
		return error;
	}

	public void setError( String error )
	{
		this.error = error;
	}
}
