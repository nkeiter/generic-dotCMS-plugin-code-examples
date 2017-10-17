package org.example.nkeiter.template.viewtool.log;

import java.net.InetAddress;

public class Logger extends com.dotmarketing.util.Logger
{
	public static Class<Logger> clazz = Logger.class;

	// Environment Keys
	public static final String DEVELOPMENT = "development1";
	public static final String STAGE = "staging1";
	public static final String PRODUCTION_1 = "production1";
	public static final String PRODUCTION_2 = "production2";

	public static final String getServerName()
	{
		String serverName = "";

		try
		{
			InetAddress inetAddress = InetAddress.getLocalHost();
			serverName = inetAddress.getHostName();
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "getServerName()", exception );
		}

		return serverName;
	}

	public static void info( Object object, String message )
	{
		try
		{
			String serverName = getServerName();

			if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
			{
				com.dotmarketing.util.Logger.info( object, message);
			}

		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "info( Object, String )", exception );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public static void info( Class clazzz, String message )
	{
		try
		{
			String serverName = getServerName();

			if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
			{
				com.dotmarketing.util.Logger.info( clazzz, message);
			}

		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "info( Class, String )", exception );
		}
	}

	public static void debug( Object object, String message )
	{
		try
		{
			String serverName = getServerName();

		if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
		{
			com.dotmarketing.util.Logger.debug( object, message);
		}
		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "debug( Object, String )", exception );
		}
	}

	public static void debug( Object object, String message, Throwable throwable )
	{
		try
		{
			String serverName = getServerName();

			if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
			{
				com.dotmarketing.util.Logger.debug( object, message, throwable );
			}

		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "debug( Object, String, Throwable )", exception );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public static void debug( Class clazzz, String message )
	{
		try
		{
			String serverName = getServerName();

			if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
			{
				com.dotmarketing.util.Logger.debug( clazzz, message );
			}

		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "debug( Class, String )", exception );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public static void debug( Class clazzz, String message, Throwable throwable )
	{
		try
		{
			String serverName = getServerName();

			if ( serverName.equals( DEVELOPMENT ) || serverName.equals( STAGE ) )
			{
				com.dotmarketing.util.Logger.debug( clazzz, message, throwable );
			}

		}
		catch ( Exception exception )
		{
			com.dotmarketing.util.Logger.error( clazz, "debug( Class, String, Throwable )", exception );
		}
	}
}
