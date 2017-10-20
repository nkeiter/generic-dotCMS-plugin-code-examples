package org.example.nkeiter.web.form.spam.filter.servlet;

import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class SendEmailServletFilter extends WebFormServletFilter
{
	public static Class<SendEmailServletFilter> clazz = SendEmailServletFilter.class;

	public SendEmailServletFilter()
	{
		Logger.info( this, "Got to SendEmailServletFilter()" );

		super.setOriginalEndpoint( "/dotCMS/sendEmail" );
	}
}
