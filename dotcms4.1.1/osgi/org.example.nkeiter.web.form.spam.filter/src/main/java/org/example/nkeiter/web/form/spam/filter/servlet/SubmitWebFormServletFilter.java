package org.example.nkeiter.web.form.spam.filter.servlet;

import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class SubmitWebFormServletFilter extends WebFormServletFilter
{
	public static Class<SubmitWebFormServletFilter> clazz = SubmitWebFormServletFilter.class;

	public SubmitWebFormServletFilter()
	{
		Logger.info( this, "Got to SubmitWebFormServletFilter()" );

		super.setOriginalEndpoint( "/dotCMS/submitWebForm" );
	}
}
