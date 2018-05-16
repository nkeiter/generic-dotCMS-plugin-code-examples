package org.example.nkeiter.login.as.key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAsKey
{
	public static Class<LoginAsKey> clazz = LoginAsKey.class;

	// Form Fields
	public static final String DISPATCH_FIELD = "dispatch";
	public static final String USERNAME_FIELD = "username";

	// Login As Dispatches
	public static final String LOGIN_AS_DISPATCH = "loginAs";

	// Role Key
	public static final String ROLE_1 = "x";
	public static final String ROLE_2 = "y";
	public static final String ROLE_3 = "z";

	// Parent Role Key
	public static final String PARENT_ROLE_1 = "a";
	public static final String PARENT_ROLE_2 = "b";

	// Gobal login as roles
	public static final List<String> GLOBAL_LOGIN_AS_ROLES;
	static
	{
		List<String> globalLoginAsRoles = new ArrayList<String>();

		globalLoginAsRoles.add( ROLE_1 );

		GLOBAL_LOGIN_AS_ROLES = Collections.unmodifiableList( globalLoginAsRoles );
	}

	// Granular login as roles
	public static final List<String> LOGIN_AS_ROLES;
	static
	{
		List<String> loginAsRoles = new ArrayList<String>();

		loginAsRoles.add( ROLE_3 );
		loginAsRoles.add( ROLE_2 );
		loginAsRoles.add( ROLE_1 );

		LOGIN_AS_ROLES = Collections.unmodifiableList( loginAsRoles );
	}

	// Granular login as role permission grants.
	public static final Map<String, ArrayList<String>> LOGIN_AS_ROLE_PERMISSION_GRANTS;
	static
	{
		Map<String, ArrayList<String>> loginAsRolePermissionGrants = new HashMap<String, ArrayList<String>>();

		ArrayList<String> xRolePermissionGrants = new ArrayList<String>();
		xRolePermissionGrants.add( PARENT_ROLE_1 );

		loginAsRolePermissionGrants.put( ROLE_3, xRolePermissionGrants );

		ArrayList<String> yRolePermissionGrants = new ArrayList<String>();
		yRolePermissionGrants.add( PARENT_ROLE_2 );

		loginAsRolePermissionGrants.put( ROLE_2, yRolePermissionGrants );

		LOGIN_AS_ROLE_PERMISSION_GRANTS = Collections.unmodifiableMap( loginAsRolePermissionGrants );
	}

	// Granular login as role permission denials.
	public static final Map<String, ArrayList<String>> LOGIN_AS_ROLE_PERMISSION_DENIALS;
	static
	{
		Map<String, ArrayList<String>> loginAsRolePermissionDenials = new HashMap<String, ArrayList<String>>();

		ArrayList<String> admissionsRolePermissionDenials = new ArrayList<String>();
		admissionsRolePermissionDenials.add( ROLE_1 );

		loginAsRolePermissionDenials.put( ROLE_3, admissionsRolePermissionDenials );

		ArrayList<String> daprRolePermissionDenials = new ArrayList<String>();
		daprRolePermissionDenials.add( ROLE_1 );

		loginAsRolePermissionDenials.put( ROLE_2, daprRolePermissionDenials );

		LOGIN_AS_ROLE_PERMISSION_DENIALS = Collections.unmodifiableMap( loginAsRolePermissionDenials );
	}

	// Session Attributes
	public static final String SESSION_ATTRIBUTE_ORIGINAL_USER = "originalUser";

	// Forward Keys
	public static final String LOGIN_AS_FAIL = "loginAsFail";
	public static final String LOGIN_AS_SUCCESS = "loginAsSuccess";
	public static final String DEFAULT = "home";

	// Forward Pages
	public static final String DEFAULT_PAGE = "/";
	public static final String LOGIN_AS_PAGE = "/application/login/login-as/login-as.dot";

	// Forwards
	public static final Map<String, String> FORWARDS;
	static
	{
		Map<String, String> forwards = new HashMap<String, String>();

		forwards.put( DEFAULT, DEFAULT_PAGE );
		forwards.put( LOGIN_AS_FAIL, LOGIN_AS_PAGE );

		FORWARDS = Collections.unmodifiableMap( forwards );
	}

	// Redirect Keys
	public static final String LOGIN_LOGIN_AS = "loginLoginAs";

	// Redirect Pages
	public static final String LOGIN_LOGIN_AS_PAGE = "/dotCMS/login?referrer=/application/login/login-as/login-as.dot";

	// Redirects
	public static final Map<String, String> REDIRECTS;
	static
	{
		Map<String, String> redirects = new HashMap<String, String>();

		redirects.put( LOGIN_LOGIN_AS, LOGIN_LOGIN_AS_PAGE );

		REDIRECTS = Collections.unmodifiableMap( redirects );
	}
}
