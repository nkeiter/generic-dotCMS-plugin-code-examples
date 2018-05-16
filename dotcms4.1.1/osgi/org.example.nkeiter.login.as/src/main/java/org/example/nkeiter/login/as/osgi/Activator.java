package org.example.nkeiter.login.as.osgi;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.apache.felix.http.api.ExtHttpService;

import org.example.nkeiter.login.as.servlet.LoginAsServlet;
import org.example.nkeiter.login.as.servlet.LoginAsServletFilter;
import org.example.nkeiter.login.as.servlet.LogoutAsServlet;
import org.example.nkeiter.login.as.servlet.LogoutAsServletFilter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends GenericBundleActivator
{
	public static Class<Activator> clazz = Activator.class;

	private LoggerContext pluginLoggerContext;

	private static final String PLUGIN_NAME = "Login As Dispatch Action (/loginAs /logoutAs)";

	private static final String EXCLUDE_URL_1 = "/app/loginAs";
	private static final String URL_1 = "/loginAs";
	private static final String URL_FILTER_1 = "/loginAs/*";
	private static final String URL_REGEX_1 = "^/loginAs/?$";

	private static final String EXCLUDE_URL_2 = "/app/logoutAs";
	private static final String URL_2 = "/logoutAs";
	private static final String URL_FILTER_2 = "/logoutAs/*";
	private static final String URL_REGEX_2 = "^/logoutAs/?$";

	private LoginAsServletFilter filter_1 = new LoginAsServletFilter();
	private LogoutAsServletFilter filter_2 = new LogoutAsServletFilter();
	@SuppressWarnings( "rawtypes" )
	private ServiceTracker serviceTracker;
	private LoginAsServlet servlet_1 = new LoginAsServlet();
	private LogoutAsServlet servlet_2 = new LogoutAsServlet();

	private void serviceAdded( ExtHttpService extHttpService )
	{
		Logger.info( this, "Got to serviceAdded( ExtHttpService ) " + PLUGIN_NAME );

		try
		{
			extHttpService.registerServlet( URL_1, this.servlet_1, null, null );
			extHttpService.registerFilter( this.filter_1, URL_FILTER_1, null, 0, null );

			extHttpService.registerServlet( URL_2, this.servlet_2, null, null );
			extHttpService.registerFilter( this.filter_2, URL_FILTER_2, null, 0, null );
		}
		catch ( Exception exception )
		{
			exception.printStackTrace();
		}

	}

	private void serviceRemoved( ExtHttpService extHttpService )
	{
		Logger.info( this, "Got to serviceRemoved( ExtHttpService ) " + PLUGIN_NAME );

		extHttpService.unregisterServlet( this.servlet_1 );
		extHttpService.unregisterFilter( this.filter_1 );

		extHttpService.unregisterServlet( this.servlet_2 );
		extHttpService.unregisterFilter( this.filter_2 );
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		// Initializing log4j...
		LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();

		// Initialing the log4j context of this plugin based on the dotCMS logger context
		this.pluginLoggerContext = (LoggerContext) LogManager.getContext( clazz.getClassLoader(), false, dotcmsLoggerContext, dotcmsLoggerContext.getConfigLocation() );

		Logger.info( this, "Got to start( BundleContext ) " + PLUGIN_NAME );

		// Initializing services...
		initializeServices( bundleContext );

		this.serviceTracker = new ServiceTracker( bundleContext, ExtHttpService.class.getName(), null )
		{
			@Override
			public Object addingService( ServiceReference serviceReference )
			{
				Logger.info( this, "Got to addingService( ServiceReference ) " + PLUGIN_NAME );

				Object serviceObject =  super.addingService( serviceReference );
				serviceAdded( (ExtHttpService) serviceObject );
				return serviceObject;
			}

			@Override
			public void removedService( ServiceReference serviceReference, Object serviceObject )
			{
				Logger.info( this, "Got to removedService( ServiceReference, Object ) " + PLUGIN_NAME );

				serviceRemoved( (ExtHttpService) serviceObject );
				super.removedService( serviceReference, serviceObject );
			}
		};

		this.serviceTracker.open();

		// Add Tuckey Filter rules
		Logger.info( this, "Adding Tuckey Filter regex [" + URL_REGEX_1 + "] -> forward [" + EXCLUDE_URL_1 + "]" );
		addRewriteRule( URL_REGEX_1, EXCLUDE_URL_1, "forward", "LoginAsServletRedirect" );

		Logger.info( this, "Adding Tuckey Filter regex [" + URL_REGEX_2 + "] -> forward [" + EXCLUDE_URL_2 + "]" );
		addRewriteRule( URL_REGEX_2, EXCLUDE_URL_2, "forward", "LogoutAsServletRedirect" );
	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		// Unregister the RewriteRules
		unregisterRewriteRule();

		// Close the service tracker
		this.serviceTracker.close();

		// Shutting down log4j in order to avoid memory leaks
		Log4jUtil.shutdown( this.pluginLoggerContext );
	}
}
