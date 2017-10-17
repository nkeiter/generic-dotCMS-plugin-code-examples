package org.example.nkeiter.web.form.spam.filter.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.nkeiter.web.form.spam.filter.beans.WebFormValidatorBean;
import org.example.nkeiter.web.form.spam.filter.business.WebFormValidator;
import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class WebFormServletFilter implements Filter
{
	private String originalEndpoint = "";
	private String originalReferer = "";

	public WebFormServletFilter()
	{
		Logger.info( this, "Got to WebFormServletFilter()" );
	}

	@Override
	public void destroy()
	{
		Logger.info( this, "Got to destroy()" );
	}

	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException
	{
		Logger.info( this, "Got to doFilter( ServletRequest, ServletResponse, FilterChain ) originalEndpoint [" + this.originalEndpoint + "]" );

		try
		{
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

			this.setOriginalReferer( httpServletRequest.getHeader( "referer" ) );

			if ( WebFormValidator.isWebForm( httpServletRequest ) )
			{
				WebFormValidatorBean webFormValidatorBean = WebFormValidator.isValidWebForm( httpServletRequest );

				// Success
				if ( webFormValidatorBean.isValid() )
				{
					Logger.info( this, "Valid web form, passing UP the filter chain." );

					// Simulate Struts Action
					new WebFormServlet().service( httpServletRequest, httpServletResponse );
				}
				// Fail
				else
				{
					Logger.info( this, "Invalid web form, throwing 400 error." );

					// Throw Error
					httpServletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST );
				}

			}
			// Ignore
			else
			{
				Logger.info( this, "Not a web form, passing DOWN the filter chain." );

				// Pass Through
				filterChain.doFilter( httpServletRequest, httpServletResponse );
			}

		}
		catch ( Exception exception )
		{
			Logger.error( this, "WebFormServletFilter.doFilter( ServletRequest, ServletResponse, FilterChain ) originalEndpoint [" + this.originalEndpoint + "] originalReferer [" + this.originalReferer + "]", exception );
		}

	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException
	{
		Logger.info( this, "Got to init( FilterConfig )" );
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
