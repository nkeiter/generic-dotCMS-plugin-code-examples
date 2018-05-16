package org.example.nkeiter.login.as.business;

import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.nkeiter.login.as.key.LoginAsKey;
import org.example.nkeiter.login.as.log.Logger;
import org.example.nkeiter.login.as.security.LoginAsSecurity;

public class LoginAsDispatchFactory extends ServletDispatcherFactory
{
	public static Class<LoginAsDispatchFactory> clazz = LoginAsDispatchFactory.class;

	public static void loginAs( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( userLoggedIn( LoginAsKey.REDIRECTS.get( LoginAsKey.LOGIN_LOGIN_AS ), httpServletRequest, httpServletResponse ) )
			{
				HttpSession httpSession = httpServletRequest.getSession();
				User user = (User) httpSession.getAttribute( com.dotmarketing.util.WebKeys.CMS_USER );
				String usernameToLoginAs = httpServletRequest.getParameter( LoginAsKey.USERNAME_FIELD );
				usernameToLoginAs = usernameToLoginAs.trim();

				loginAs( user, usernameToLoginAs, httpServletRequest, httpServletResponse );
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsDispatchFactory.loginAs( HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	private static void loginAs( User user, String usernameToLoginAs, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( userLoggedIn( LoginAsKey.REDIRECTS.get( LoginAsKey.LOGIN_LOGIN_AS ), httpServletRequest, httpServletResponse ) )
			{
				String forward = LoginAsKey.FORWARDS.get( LoginAsKey.LOGIN_AS_FAIL );
				String message = "[" + user.getUserId() + "] is trying to login as [" + usernameToLoginAs + "]";

				Logger.info( clazz, "LoginAsDispatchFactory.loginAs( HttpServletRequest, HttpServletResponse ) " + message );

				boolean userHasLoginAsPermission = LoginAsSecurity.userHasLoginAsPermission( user, usernameToLoginAs, httpServletRequest );

				if ( userHasLoginAsPermission )
				{
					LoginAsFactory.saveOriginalUserToSession( user, httpServletRequest );

					String forwardPage = LoginAsFactory.loginAs( usernameToLoginAs, httpServletRequest, httpServletResponse );

					if ( forwardPage != null )
					{
						forward = forwardPage;
					}

				}
				else
				{
					message = "You are not allowed to login as [" + usernameToLoginAs + "]";

					httpServletRequest = setActionMessage( message, httpServletRequest );

					Logger.info( clazz, "LoginAsDispatchFactory.loginAs( HttpServletRequest, HttpServletResponse ) " + message );
				}

				Logger.info( clazz, "LoginAsDispatchFactory.loginAs( HttpServletRequest, HttpServletResponse ) Forwarding to [" + forward + "]" );

				// Forward for continued processing
				forward( forward, httpServletRequest, httpServletResponse );
			}
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsDispatchFactory.loginAs( HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	public static void logoutAs( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			HttpSession httpSession = httpServletRequest.getSession();

			if ( userLoggedInAndOriginalUserNotForgotten( LoginAsKey.REDIRECTS.get( LoginAsKey.LOGIN_LOGIN_AS ), httpServletRequest, httpServletResponse ) )
			{
				User user = (User) httpSession.getAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER );
				String username = user.getUserId();

				loginAs( user, username, httpServletRequest, httpServletResponse );
			}

		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsDispatchFactory.logoutAs( HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	public static void unspecified( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			forward( LoginAsKey.FORWARDS.get( LoginAsKey.LOGIN_AS_FAIL ), httpServletRequest, httpServletResponse );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsDispatchFactory.unspecified( HttpServletRequest, HttpServletResponse )", exception );
		}
	}

	protected static boolean userLoggedInAndOriginalUserNotForgotten( String redirect, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		try
		{
			HttpSession httpSession = httpServletRequest.getSession();

			return userLoggedInAndOriginalUserNotForgotten( redirect, httpSession, httpServletResponse );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsDispatchFactory.userLoggedInAndOriginalUserNotForgotten( String, HttpServletRequest, HttpServletResponse )", exception );
		}

		return false;
	}

	protected static boolean userLoggedInAndOriginalUserNotForgotten( String redirect, HttpSession httpSession, HttpServletResponse httpServletResponse )
	{
		try
		{
			if ( !userLoggedIn( httpSession ) || httpSession.getAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER ) == null )
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
			Logger.error( clazz, "LoginAsDispatchFactory.userLoggedInAndOriginalUserNotForgotten( String, HttpSession, HttpServletResponse )", exception );
		}

		return false;
	}
}
