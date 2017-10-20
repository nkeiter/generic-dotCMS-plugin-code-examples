package org.example.nkeiter.contentlet.rest.light.osgi;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;
import com.dotcms.rest.config.RestServiceUtil;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.example.nkeiter.contentlet.rest.light.service.ContentletRestServiceLight;

import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator
{
	public static Class<Activator> clazz = Activator.class;

	private LoggerContext pluginLoggerContext;

	private static final String PLUGIN_NAME = "Contentlet Rest Service Light (/api/contentletLight)";

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

		Logger.info( clazz, "Adding RESTful Service: " + ContentletRestServiceLight.class.getSimpleName() );
		RestServiceUtil.addResource( ContentletRestServiceLight.class );

	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		Logger.info( clazz, "Removing RESTful Service: " + ContentletRestServiceLight.class.getSimpleName() );
		RestServiceUtil.removeResource( ContentletRestServiceLight.class );

		// Shutting down log4j in order to avoid memory leaks
		Log4jUtil.shutdown( this.pluginLoggerContext );
	}
}
