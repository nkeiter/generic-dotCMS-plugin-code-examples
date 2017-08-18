package org.example.nkeiter.contentlet.rest.light.osgi;

import com.dotcms.rest.config.RestServiceUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.example.nkeiter.contentlet.rest.light.service.ContentletRestServiceLight;

import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator
{
	private static final String PLUGIN_NAME = "Contentlet Rest Service Light (/api/contentletLight)";

	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to start( BundleContext ) " + PLUGIN_NAME );

		// Initializing services...
		initializeServices( bundleContext );

		Logger.info( this.getClass(), "Adding RESTful Service: " + ContentletRestServiceLight.class.getSimpleName() );
		RestServiceUtil.addResource( ContentletRestServiceLight.class );

	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		Logger.info( this.getClass(), "Removing RESTful Service: " + ContentletRestServiceLight.class.getSimpleName() );
		RestServiceUtil.removeResource( ContentletRestServiceLight.class );
	}
}
