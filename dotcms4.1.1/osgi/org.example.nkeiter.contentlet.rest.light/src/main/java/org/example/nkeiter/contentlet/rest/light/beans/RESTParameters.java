package org.example.nkeiter.contentlet.rest.light.beans;

public enum RESTParameters
{
	RENDER   ( "render" ),
	TYPE     ( "type" ),
	QUERY    ( "query" ),
	ORDERBY  ( "orderby" ),
	LIMIT    ( "limit" ),
	OFFSET   ( "offset" ),
	USER     ( "user" ),
	PASSWORD ( "password" ),
	ID       ( "id" ),
	LIVE     ( "live" ),
	LANGUAGE ( "language" ),
	CALLBACK ( "callback" ),
	INODE    ( "inode" ),
	INCLUDE_FIELDS  ( "include-fields" );

	public static Class<RESTParameters> clazz = RESTParameters.class;

	private final String value;

	RESTParameters( String value )
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
