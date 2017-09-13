package org.example.nkeiter.template.viewtool.osgi;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator
{
	private LoggerContext pluginLoggerContext;

	private static final String PLUGIN_NAME = "Template ViewTool ($templateViewTool)";

	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		// Initializing log4j...
		LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();

		// Initialing the log4j context of this plugin based on the dotCMS logger context
		this.pluginLoggerContext = (LoggerContext) LogManager.getContext( this.getClass().getClassLoader(), false, dotcmsLoggerContext, dotcmsLoggerContext.getConfigLocation() );

		Logger.info( this, "Got to start( BundleContext ) " + PLUGIN_NAME );

		//Initializing services...
		initializeServices( bundleContext );

		//Registering the ViewTool service
		registerViewToolService( bundleContext, new TemplateViewToolInfo() );
	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		unregisterViewToolServices();

		// Shutting down log4j in order to avoid memory leaks
		Log4jUtil.shutdown( this.pluginLoggerContext );
	}
}
