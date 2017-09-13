package org.example.nkeiter.ip.restricted.servlet.servlet;

import com.dotmarketing.util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpRestrictedServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static final List<String> ALLOWED_IP_LIST;
	static
	{
		List<String> allowedIpList = new ArrayList<String>();

		// TODO Change these values to match your approved IP list.
		allowedIpList.add( "199.1.1.1" ); // TEST
		allowedIpList.add( "199.1.1.2" ); // STAGE
		allowedIpList.add( "199.1.1.3" ); // PROD1
		allowedIpList.add( "199.1.1.4" ); // PROD2

		ALLOWED_IP_LIST = Collections.unmodifiableList( allowedIpList );
	}

	public IpRestrictedServlet()
	{
		//Logger.info( this, "Got to IpRestrictedServlet()" );
	}

	@Override
	public void destroy()
	{
		//Logger.info( this, "Got to IpRestrictedServlet.destroy()" );

		super.destroy();
	}

	@Override
	public void init( ServletConfig servletConfig ) throws ServletException
	{
		//Logger.info( this, "Got to IpRestrictedServlet.init( ServletConfig ) [" + servletConfig + "]" );

		super.init( servletConfig );  
	}

	@Override
	public void service( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		//Logger.info( this, "Got to IpRestrictedServlet.service( HttpServletRequest, HttpServletResponse )" );

		try
		{
			String ipAddress = httpServletRequest.getRemoteAddr();
			Logger.info( IpRestrictedServlet.class, "IpRestrictedServlet.service( HttpServletRequest, HttpServletResponse ) Requestor IP Address = "  + ipAddress );

			if ( !ALLOWED_IP_LIST.contains( ipAddress ) )
			{
				// Servlet can only be accessed by approved IP addresses
				httpServletResponse.sendError( 403 );
			}
			else
			{
				// TODO Write method call to business logic.
			}

		}
		catch ( Exception exception )
		{
			Logger.error( this, "IpRestrictedServlet.service( HttpServletRequest, HttpServletResponse ) ", exception );
		}

	}
}
