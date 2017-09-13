package org.example.nkeiter.ip.restricted.servlet.osgi;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.example.nkeiter.ip.restricted.servlet.servlet.IpRestrictedServlet;
import org.example.nkeiter.ip.restricted.servlet.servlet.IpRestrictedServletFilter;

import org.apache.felix.http.api.ExtHttpService;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends GenericBundleActivator
{
	private LoggerContext pluginLoggerContext;

	private static final String PLUGIN_NAME = "Ip Restricted Servlet (/servlets/ipRestrictedServlet)";

	private static final String EXCLUDE_URL = "/app/servlets/ipRestrictedServlet";
	private static final String URL = "/servlets/ipRestrictedServlet";
	private static final String URL_FILTER = "/servlets/ipRestrictedServlet/*";

	private IpRestrictedServletFilter filter = new IpRestrictedServletFilter();
	@SuppressWarnings( "rawtypes" )
	private ServiceTracker serviceTracker;
	private IpRestrictedServlet servlet = new  IpRestrictedServlet();

	private void serviceAdded( ExtHttpService extHttpService )
	{
		Logger.info( this, "Got to serviceAdded( ExtHttpService ) " + PLUGIN_NAME );

		try
		{
			extHttpService.registerServlet( URL, this.servlet, null, null );
			extHttpService.registerFilter( this.filter, URL_FILTER, null, 0, null );
		}
		catch ( Exception exception )
		{
			exception.printStackTrace();
		}

	}

	private void serviceRemoved( ExtHttpService extHttpService )
	{
		Logger.info( this, "Got to serviceRemoved( ExtHttpService ) " + PLUGIN_NAME );

		extHttpService.unregisterServlet( this.servlet );
		extHttpService.unregisterFilter( this.filter );
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		// Initializing log4j...
		LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();

		// Initialing the log4j context of this plugin based on the dotCMS logger context
		this.pluginLoggerContext = (LoggerContext) LogManager.getContext( this.getClass().getClassLoader(), false, dotcmsLoggerContext, dotcmsLoggerContext.getConfigLocation() );

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

		// Add Tuckey Filter rule
		Logger.info( this, "Adding Tuckey Filter regex [" + URL + "] -> forward [" + EXCLUDE_URL + "]" );
		addRewriteRule( URL, EXCLUDE_URL, "forward", "IpRestrictedServletRedirect" );
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
