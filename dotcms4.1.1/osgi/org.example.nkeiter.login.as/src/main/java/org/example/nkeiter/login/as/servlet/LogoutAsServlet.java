package org.example.nkeiter.login.as.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.nkeiter.login.as.business.LoginAsDispatchFactory;
import org.example.nkeiter.login.as.log.Logger;

public class LogoutAsServlet extends HttpServlet 
{
	public static Class<LogoutAsServlet> clazz = LogoutAsServlet.class;

	private static final long serialVersionUID = 1L;

	public LogoutAsServlet()
	{
		Logger.info( this, "Got to LogoutAsServlet()" );
	}

	@Override
	public void destroy()
	{
		Logger.info( this, "Got to LogoutAsServlet.destroy()" );

		super.destroy();
	}

	@Override
	public void init( ServletConfig servletConfig ) throws ServletException
	{
		Logger.info( this, "Got to LogoutAsServlet.init( ServletConfig )" );

		super.init( servletConfig );  
	}

	@Override
	public void service( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			Logger.info( this, "Got to LogoutAsServlet.service( HttpServletRequest, HttpServletResponse )" );

			LoginAsDispatchFactory.logoutAs( httpServletRequest, httpServletResponse );
		}
		catch ( Exception exception )
		{
			Logger.error( this, "LogoutAsServlet.service( HttpServletRequest, HttpServletResponse )", exception );
		}
	}
}
