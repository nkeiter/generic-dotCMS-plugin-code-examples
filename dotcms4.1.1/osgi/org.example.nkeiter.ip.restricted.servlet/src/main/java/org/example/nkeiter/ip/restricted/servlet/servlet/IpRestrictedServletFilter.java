package org.example.nkeiter.ip.restricted.servlet.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.example.nkeiter.ip.restricted.servlet.log.Logger;

public class IpRestrictedServletFilter implements Filter
{
	public static Class<IpRestrictedServletFilter> clazz = IpRestrictedServletFilter.class;

	public IpRestrictedServletFilter()
	{
		Logger.info( this, "Got to IpRestrictedServletFilter()" );
	}

	@Override
	public void destroy()
	{
		Logger.info( this, "Got to IpRestrictedServletFilter.destroy()" );
	}

	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException
	{
		Logger.info( this, "Got to IpRestrictedServletFilter.doFilter( ServletRequest, ServletResponse, FilterChain )" );

		filterChain.doFilter( servletRequest, servletResponse );
	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException
	{
		Logger.info( this, "Got to IpRestrictedServletFilter.init( FilterConfig )" );
	}
}
