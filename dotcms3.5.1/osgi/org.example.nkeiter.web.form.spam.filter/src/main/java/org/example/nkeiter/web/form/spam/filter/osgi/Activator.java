package org.example.nkeiter.web.form.spam.filter.osgi;

import com.dotcms.repackage.org.apache.felix.http.api.ExtHttpService;

import com.dotcms.repackage.org.osgi.framework.BundleContext;
import com.dotcms.repackage.org.osgi.framework.ServiceReference;

import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import org.example.nkeiter.web.form.spam.filter.servlet.SendEmailServlet;
import org.example.nkeiter.web.form.spam.filter.servlet.SubmitWebFormServlet;
import org.example.nkeiter.web.form.spam.filter.servlet.SendEmailServletFilter;
import org.example.nkeiter.web.form.spam.filter.servlet.SubmitWebFormServletFilter;

public class Activator extends GenericBundleActivator
{
	private static final String PLUGIN_NAME = "Web Form Spam Filter Servlet (/dotCMS/submitWebForm /dotCMS/sendEmail)";

	private static final String EXCLUDE_URL_1 = "/app/submitWebForm";
	private static final String EXCLUDE_URL_2 = "/app/sendEmail";
	private static final String URL_1 = "/submitWebForm";
	private static final String URL_2 = "/sendEmail";
	private static final String URL_FILTER_1 = ".+?submitWebForm.*";
	private static final String URL_FILTER_2 = ".+?sendEmail.*";
	private static final String URL_REGEX_1 = "/dotCMS/submitWebForm";
	private static final String URL_REGEX_2 = "/dotCMS/sendEmail";

	private SubmitWebFormServletFilter filter_1 = new SubmitWebFormServletFilter();
	private SendEmailServletFilter filter_2 = new SendEmailServletFilter();
	private SubmitWebFormServlet servlet_1 = new SubmitWebFormServlet();
	private SendEmailServlet servlet_2 = new SendEmailServlet();

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to start( BundleContext ) " + PLUGIN_NAME );

		// Initializing services...
		initializeServices( bundleContext );

		ServiceReference serviceReference = bundleContext.getServiceReference( ExtHttpService.class.getName() );

		if ( serviceReference != null )
		{

			ExtHttpService extHttpService = (ExtHttpService) bundleContext.getService( serviceReference );

			extHttpService.registerServlet( URL_1, this.servlet_1, null, null );
			extHttpService.registerFilter( this.filter_1, URL_FILTER_1, null, 0, null );

			extHttpService.registerServlet( URL_2, this.servlet_2, null, null );
			extHttpService.registerFilter( this.filter_2, URL_FILTER_2, null, 0, null );

			// Add Tuckey Filter rules
			// Redirect legacy URLs
			Logger.info( this, "Adding Tuckey Filter regex [" + URL_REGEX_1 + "] -> forward [" + EXCLUDE_URL_1 + "]" );
			addRewriteRule( URL_REGEX_1, EXCLUDE_URL_1, "forward", "WebFormServletForward1" );

			Logger.info( this, "Adding Tuckey Filter regex [" + URL_REGEX_2 + "] -> forward [" + EXCLUDE_URL_2 + "]" );
			addRewriteRule( URL_REGEX_2, EXCLUDE_URL_2, "forward", "WebFormServletForward2" );
		}
		else
		{
			Logger.error( this, "Failure, service reference is null!" );
		}
	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		// Unregister the RewriteRules
		unregisterRewriteRule();

		// Unregister the services
		unregisterServices( bundleContext );
	}
}
