package org.example.nkeiter.generic.osgi;

import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator
{
    @Override
    public void start( BundleContext bundleContext ) throws Exception
    {
		Logger.info( this, "Got to start( BundleContext )" );
		
        //Initializing services...
        initializeServices( bundleContext );

        //Registering the ViewTool service
        registerViewToolService( bundleContext, new SelfParsingBeanViewToolInfo() );
    }

    @Override
    public void stop( BundleContext bundleContext ) throws Exception
    {
		Logger.info( this, "Got to stop( BundleContext )" );
		
        unregisterViewToolServices();
    }
}