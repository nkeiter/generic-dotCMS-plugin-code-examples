package org.example.nkeiter.embedded.spring4.osgi;

import com.dotcms.repackage.org.apache.felix.http.api.ExtHttpService;
import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;

import com.dotcms.repackage.org.osgi.framework.BundleContext;
import com.dotcms.repackage.org.osgi.framework.ServiceReference;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.springframework.web.servlet.DispatcherServlet;

public class Activator extends GenericBundleActivator
{
	private LoggerContext pluginLoggerContext;

	private static final String PLUGIN_NAME = "Example Spring Endpoint (/app/spring/example)";

	private static final String CONFIGURATION_FILE = "spring/example-spring-servlet.xml";
	private static final String URL = "/spring/example";

	private DispatcherServlet dispatcherServlet;
	private ExtHttpService extHttpService;

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

		// Service reference to ExtHttpService that will allow us to register servlets and filters
		ServiceReference serviceReference = bundleContext.getServiceReference( ExtHttpService.class.getName() );

		if ( serviceReference != null )
		{
			// Publish bundle services
			publishBundleServices( bundleContext );

			extHttpService = (ExtHttpService) bundleContext.getService( serviceReference );

			try
			{
				dispatcherServlet = new DispatcherServlet();
				dispatcherServlet.setContextConfigLocation( CONFIGURATION_FILE );
				extHttpService.registerServlet( URL, dispatcherServlet, null, null );
			}
			catch ( Exception exception )
			{
				Logger.error( this, "Error registering spring servlet!", exception );
			}

		}
		else
		{
			Logger.error( this, "ServiceReference is null!" );
		}

	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		// Unregister the spring servlet
		if ( extHttpService != null && dispatcherServlet != null )
		{
			extHttpService.unregisterServlet( dispatcherServlet );
		}

		// Unpublish bundle services
		unpublishBundleServices();

		// Shutting down log4j in order to avoid memory leaks
		Log4jUtil.shutdown( this.pluginLoggerContext );
	}
}
