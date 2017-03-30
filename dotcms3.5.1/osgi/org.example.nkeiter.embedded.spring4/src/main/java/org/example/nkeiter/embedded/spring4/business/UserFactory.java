package org.example.nkeiter.embedded.spring4.business;

import com.dotmarketing.util.Logger;
import com.dotmarketing.viewtools.CMSUsersWebAPI;

import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;

public class UserFactory
{
	public static User getUser( HttpServletRequest httpServletRequest )
	{
		try
		{
			return new CMSUsersWebAPI().getLoggedInUser( httpServletRequest );
		}
		catch( Exception exception )
		{
			Logger.error( UserFactory.class , "User Not Logged in" );
			return null;
		}
	}
}
