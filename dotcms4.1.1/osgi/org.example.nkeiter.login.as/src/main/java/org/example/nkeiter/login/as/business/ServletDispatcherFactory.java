package org.example.nkeiter.login.as.business;

import com.dotcms.repackage.org.apache.struts.Globals;
import com.dotcms.repackage.org.apache.struts.action.ActionMessage;
import com.dotcms.repackage.org.apache.struts.action.ActionMessages;

import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.WebKeys;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.nkeiter.login.as.log.Logger;

public class ServletDispatcherFactory
{
	public static final Class<ServletDispatcherFactory> clazz = ServletDispatcherFactory.class;

	protected static void forward( String forward, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( UtilMethods.isSet( forward ) )
			{
				// Forward for continued processing
				RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher( forward );
				requestDispatcher.forward( httpServletRequest, httpServletResponse );
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.forward( String, HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	protected static void redirect( String redirect, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( UtilMethods.isSet( redirect ) )
			{
				httpServletResponse.sendRedirect( redirect );
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.redirect( String, HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	protected static HttpServletRequest setActionMessage( String message, HttpServletRequest httpServletRequest )
	{
		try
		{
			ActionMessages actionMessages = new ActionMessages();

			actionMessages.add( Globals.ERROR_KEY, new ActionMessage( message ) );
			httpServletRequest.setAttribute( Globals.ERROR_KEY, actionMessages );

			Logger.info( clazz, "ServletDispatcherFactory.setActionMessage( String, HttpServletRequest ) " + message );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.setActionMessage( String, HttpServletRequest )", exception );
		}

		return httpServletRequest;
	}

	protected static boolean userLoggedIn( HttpServletRequest httpServletRequest )
	{
		try
		{
			HttpSession httpSession = httpServletRequest.getSession();

			return userLoggedIn( httpSession );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.userLoggedIn( HttpServletResponse )", exception );
		}

		return false;
	}

	protected static boolean userLoggedIn( HttpSession httpSession )
	{
		try
		{
			if ( httpSession.getAttribute( WebKeys.CMS_USER ) == null )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.userLoggedIn( HttpSession )", exception );
		}

		return false;
	}

	protected static boolean userLoggedIn( String redirect, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			HttpSession httpSession = httpServletRequest.getSession();

			return userLoggedIn( redirect, httpSession, httpServletResponse );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.userLoggedIn( String, HttpServletRequest, HttpServletResponse )", exception );
		}

		return false;
	}

	protected static boolean userLoggedIn( String redirect, HttpSession httpSession, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( httpSession.getAttribute( WebKeys.CMS_USER ) == null )
			{
				redirect( redirect, httpServletResponse );

				return false;
			}
			else
			{
				return true;
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "ServletDispatcherFactory.userLoggedIn( String, HttpSession, HttpServletResponse )", exception );
		}

		return false;
	}
}
