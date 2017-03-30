package org.example.nkeiter.embedded.spring4.responses;

public class SuccessResponse extends ControllerResponse
{
	public SuccessResponse()
	{
		setStatus( "success" );
	}

	public SuccessResponse( String message )
	{
		setStatus( "success" );
		setMessage( message );
	}
}
