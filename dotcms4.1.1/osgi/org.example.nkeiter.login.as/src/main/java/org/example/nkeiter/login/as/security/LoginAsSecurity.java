package org.example.nkeiter.login.as.security;

import com.dotcms.repackage.org.apache.struts.Globals;
import com.dotcms.repackage.org.apache.struts.action.ActionMessage;
import com.dotcms.repackage.org.apache.struts.action.ActionMessages;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.NoSuchUserException;
import com.dotmarketing.business.RoleAPI;
import com.dotmarketing.business.UserAPI;

import com.liferay.portal.model.User;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.example.nkeiter.login.as.key.LoginAsKey;
import org.example.nkeiter.login.as.log.Logger;

public class LoginAsSecurity
{
	public static Class<LoginAsSecurity> clazz = LoginAsSecurity.class;

	public static boolean userHasLoginAsPermission( User user, String usernameToLoginAs, HttpServletRequest httpServletRequest )
	{
		try
		{
			// User should always be allowed to loginAs themselves.
			// Happens when user is trying to logoutAs someone else and loginAs themselves.
			if ( user.getUserId().equals( usernameToLoginAs ) )
			{
				Logger.info( clazz, "User is SELF return TRUE" );

				// Allow
				return true;
			}

			// Getting the API references
			RoleAPI roleAPI = APILocator.getRoleAPI();
			UserAPI userAPI = APILocator.getUserAPI();
			User systemUser = userAPI.getSystemUser();

			// Loop through all known loginAs roles
			for ( String roleId : LoginAsKey.LOGIN_AS_ROLES )
			{
				Logger.info( clazz, "Checking for roleId [" + roleId + "]" );

				// Check if user has the loginAs role
				if ( roleAPI.doesUserHaveRole( user, roleId ) )
				{
					// Check if this loginAs role has global permissions
					if ( LoginAsKey.GLOBAL_LOGIN_AS_ROLES.contains( roleId ) )
					{
						Logger.info( clazz, "Global access for roleId [" + roleId + "] return TRUE" );

						// Allow
						return true;
					}
					else
					{
						// Get the userToLoginAs
						User userToLoginAs = userAPI.loadUserById( usernameToLoginAs, systemUser, false );

						// Check if this loginAs role has granular permission denials
						if ( LoginAsKey.LOGIN_AS_ROLE_PERMISSION_DENIALS.containsKey( roleId ) )
						{							
							// Get the granular permission denials
							ArrayList<String> loginAsRolePermissions = LoginAsKey.LOGIN_AS_ROLE_PERMISSION_DENIALS.get( roleId );

							// Loop through all the granular role permission denials
							for ( String userRoleId : loginAsRolePermissions )
							{
								// Check if the userToLoginAs has the role denying the user to loginAs them.
								if ( roleAPI.doesUserHaveRole( userToLoginAs, userRoleId ) )
								{
									Logger.info( clazz, "Explicit denial for roleId [" + roleId + "] return FALSE" );

									// Deny
									return false;
								}
							}

						}

						// Check if this loginAs role has granular permission grants
						if ( LoginAsKey.LOGIN_AS_ROLE_PERMISSION_GRANTS.containsKey( roleId ) )
						{							
							// Get the granular permission grants
							ArrayList<String> loginAsRolePermissions = LoginAsKey.LOGIN_AS_ROLE_PERMISSION_GRANTS.get( roleId );

							// Loop through all the granular role permission grants
							for ( String userRoleId : loginAsRolePermissions )
							{
								// Check if the userToLoginAs has the role allowing the user to loginAs them.
								if ( roleAPI.doesUserHaveRole( userToLoginAs, userRoleId ) )
								{
									Logger.info( clazz, "Explicit grant for roleId [" + roleId + "] return TRUE" );

									// Allow
									return true;
								}
							}

						}

					}

				}
				else
				{
					Logger.info( clazz, "User does not have roleId [" + roleId + "]" );
				}

			}

		}
		catch ( NoSuchUserException noSuchUserException )
		{
			ActionMessages actionMessages = new ActionMessages();
			String message = "User [" + usernameToLoginAs + "] does not exist.";

			actionMessages.add( Globals.ERROR_KEY, new ActionMessage( message ) );
			httpServletRequest.setAttribute( Globals.ERROR_KEY, actionMessages );

			Logger.info( clazz, "LoginAsSecurity.userHasLoginAsPermission( User, String, HttpServletRequest ) " + message );
		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "LoginAsSecurity.userHasLoginAsPermission( User, String, HttpServletRequest )", exception );
		}

		// Deny
		return false;
	}
}
