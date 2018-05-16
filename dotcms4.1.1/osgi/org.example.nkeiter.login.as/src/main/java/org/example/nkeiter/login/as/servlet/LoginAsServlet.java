package org.example.nkeiter.login.as.servlet;

import com.dotmarketing.business.CacheLocator;
import com.dotmarketing.util.UtilMethods;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.nkeiter.login.as.business.LoginAsDispatchFactory;
import org.example.nkeiter.login.as.key.LoginAsKey;
import org.example.nkeiter.login.as.log.Logger;

public class LoginAsServlet extends HttpServlet 
{
	public static Class<LoginAsServlet> clazz = LoginAsServlet.class;

	private static final long serialVersionUID = 1L;

	public LoginAsServlet()
	{
		Logger.info( this, "Got to LoginAsServlet()" );
	}

	@Override
	public void destroy()
	{
		Logger.info( this, "Got to LoginAsServlet.destroy()" );

		super.destroy();
	}

	@Override
	public void init( ServletConfig servletConfig ) throws ServletException
	{
		Logger.info( this, "Got to LoginAsServlet.init( ServletConfig )" );

		super.init( servletConfig );  
	}

	@Override
	public void service( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			Logger.info( this, "Got to LoginAsServlet.service( HttpServletRequest, HttpServletResponse )" );

			String dispatch = httpServletRequest.getParameter( LoginAsKey.DISPATCH_FIELD );

			if ( UtilMethods.isSet( dispatch ) && dispatch.equals( LoginAsKey.LOGIN_AS_DISPATCH ) )
			{
				LoginAsDispatchFactory.loginAs( httpServletRequest, httpServletResponse );
			}
			else
			{
				LoginAsDispatchFactory.unspecified( httpServletRequest, httpServletResponse );

				CacheLocator.getRoleCache().clearCache();
			}

		}
		catch ( Exception exception )
		{
			Logger.error( this, "LoginAsServlet.service( HttpServletRequest, HttpServletResponse )", exception );
		}
	}
}
