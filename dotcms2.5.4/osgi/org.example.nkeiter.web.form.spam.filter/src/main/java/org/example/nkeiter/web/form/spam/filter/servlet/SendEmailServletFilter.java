package org.example.nkeiter.web.form.spam.filter.servlet;

public class SendEmailServletFilter extends WebFormServletFilter
{
	public SendEmailServletFilter()
	{
		//Logger.info( this, "Got to SendEmailServletFilter()" );
		super.setOriginalEndpoint( "/dotCMS/sendEmail" );
	}
}
