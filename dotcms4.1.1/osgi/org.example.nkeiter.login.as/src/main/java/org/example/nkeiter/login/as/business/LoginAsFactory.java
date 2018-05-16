package org.example.nkeiter.login.as.business;

import com.dotmarketing.util.WebKeys;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.ejb.UserLocalManagerUtil;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.nkeiter.login.as.key.LoginAsKey;
import org.example.nkeiter.login.as.log.Logger;

public class LoginAsFactory extends ServletDispatcherFactory
{
	public static Class<LoginAsFactory> clazz = LoginAsFactory.class;

	public static String loginAs( String username, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		String forward = null;

		try
		{
			User user = UserLocalManagerUtil.getUserById( username );
			HttpSession httpSession = httpServletRequest.getSession();

			httpSession.setAttribute( WebKeys.CMS_USER, user );

			forward = "/";
		}
		catch ( NoSuchUserException noSuchUserException )
		{
			String message = "User [" + username + "] does not exist.";

			httpServletRequest = setActionMessage( message, httpServletRequest );

			Logger.info( clazz, "LoginAsFactory.loginAs( String, HttpServletRequest, HttpServletResponse ) " + message );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsFactory.loginAs( String, HttpServletRequest, HttpServletResponse )", exception );
		}

		return forward;
	}

	public static void saveOriginalUserToSession( User user, HttpServletRequest httpServletRequest )
	{
		try
		{
			HttpSession httpSession = httpServletRequest.getSession();

			if ( httpSession.getAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER ) == null )
			{
				httpSession.setAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER, user );
			}
			else if ( httpSession.getAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER ) != null )
			{
				Object object = httpSession.getAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER );

				if ( !( object instanceof User ) )
				{
					httpSession.setAttribute( LoginAsKey.SESSION_ATTRIBUTE_ORIGINAL_USER, user );
				}

			}

		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsFactory.saveOriginalUserToSession( User, HttpServletRequest )", exception );
		}
	}
}
