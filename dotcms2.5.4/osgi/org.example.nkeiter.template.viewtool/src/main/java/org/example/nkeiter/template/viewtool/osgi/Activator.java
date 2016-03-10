package org.example.nkeiter.template.viewtool.osgi;

import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator
{
	private static final String PLUGIN_NAME = "Template ViewTool ($templateViewTool)";

	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
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
	}
}
