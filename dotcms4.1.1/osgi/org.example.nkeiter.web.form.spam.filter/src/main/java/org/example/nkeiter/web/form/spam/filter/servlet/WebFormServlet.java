package org.example.nkeiter.web.form.spam.filter.servlet;

import com.dotcms.repackage.org.apache.struts.action.ActionForward;
import com.dotcms.repackage.org.apache.struts.action.ActionMapping;
import com.dotcms.repackage.org.apache.struts.config.ForwardConfig;

import com.dotmarketing.cms.webforms.action.SubmitWebFormAction;
import com.dotmarketing.util.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebFormServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String originalEndpoint = "";
	private String originalReferer = "";

	public WebFormServlet()
	{
		//Logger.info( this, "Got to WebFormServlet()" );
	}

	@Override
	public void destroy()
	{
		//Logger.info( this, "Got to WebFormServlet.destroy()" );

		super.destroy();
	}

	@Override
	public void init( ServletConfig servletConfig ) throws ServletException
	{
		//Logger.info( this, "Got to WebFormServlet.init( ServletConfig ) [" + servletConfig + "]" );

		super.init( servletConfig );
	}

	@Override
	public void service( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
	{
		Logger.info( this, "Got to WebFormServlet.service( HttpServletRequest, HttpServletResponse )" );

		try
		{
			SubmitWebFormAction submitWebFormAction = new SubmitWebFormAction();

			/*
			 * Replicate ActionMapping definition from struts-cms.xml
			 * 
			 * 	<action path="/submitWebForm" type="com.dotmarketing.cms.webforms.action.SubmitWebFormAction" parameter="dispatch" name="webForm">
			 * 		<forward name="thankYouPage" path="/" />
			 * 	</action>
			 */
			ActionMapping actionMapping = new ActionMapping();
			actionMapping.setPath( "/submitWebForm" );
			actionMapping.setType( "com.dotmarketing.cms.webforms.action.SubmitWebFormAction" );
			actionMapping.setParameter( "dispatch" );
			actionMapping.setName( "webForm" );

			/*
			 * Default forward if none given. Avoids null pointer exception on 
			 * line #255 of com.dotmarketing.cms.webforms.action.SubmitWebFormAction
			 * 
			 * 		<forward name="thankYouPage" path="/" />
			 */
			ForwardConfig forwardConfig = new ForwardConfig();
			forwardConfig.setName( "thankYouPage" );
			forwardConfig.setPath( "/" );

			actionMapping.addForwardConfig( forwardConfig );

			Logger.info( WebFormServlet.class, "Referer " + httpServletRequest.getHeader( "referer" ) );

			/*
			 * ActionForm can be null.
			 * It is not accessed by this method.
			 */
			ActionForward actionForward = submitWebFormAction.unspecified( actionMapping, null, httpServletRequest, httpServletResponse );

			// Send the user to the specified location.
			if ( actionForward.getRedirect() )
			{
				Logger.info( WebFormServlet.class, "Redirecting to " + actionForward.getPath() );

				// Redirect for continued processing
				httpServletResponse.sendRedirect( actionForward.getPath() );
			}
			else
			{
				Logger.info( WebFormServlet.class, "Forwarding to " + actionForward.getPath() );

				// Forward for continued processing
				RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher( actionForward.getPath() );
				requestDispatcher.forward( httpServletRequest, httpServletResponse );
			}

		}
		catch ( Exception exception )
		{
			Logger.error( WebFormServlet.class, "WebFormServlet.service( HttpServletRequest, HttpServletResponse ) originalEndpoint [" + this.originalEndpoint + "] originalReferer [" + this.originalReferer + "]", exception );
		}
	}

	protected void setOriginalEndpoint( String originalEndpoint )
	{
		this.originalEndpoint = originalEndpoint;
	}

	protected void setOriginalReferer( String originalReferer )
	{
		this.originalReferer = originalReferer;
	}
}
