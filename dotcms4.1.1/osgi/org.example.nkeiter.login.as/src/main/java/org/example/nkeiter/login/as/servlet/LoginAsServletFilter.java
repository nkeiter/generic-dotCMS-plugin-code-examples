package org.example.nkeiter.login.as.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.example.nkeiter.login.as.log.Logger;

public class LoginAsServletFilter implements Filter
{
	public static Class<LoginAsServletFilter> clazz = LoginAsServletFilter.class;

	public LoginAsServletFilter()
	{
		Logger.info( this, "Got to LoginAsServletFilter()" );
	}

	@Override
	public void destroy()
	{
		Logger.info( this, "Got to LoginAsServletFilter.destroy()" );
	}

	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException
	{
		Logger.info( this, "Got to LoginAsServletFilter.doFilter( ServletRequest, ServletResponse, FilterChain )" );

		filterChain.doFilter( servletRequest, servletResponse );
	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException
	{
		Logger.info( this, "Got to LoginAsServletFilter.init( FilterConfig )" );
	}
}
