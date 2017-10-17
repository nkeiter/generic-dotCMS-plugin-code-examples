package org.example.nkeiter.web.form.spam.filter.servlet;

import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class SendEmailServlet extends WebFormServlet
{
	private static final long serialVersionUID = 1L;

	public SendEmailServlet()
	{
		Logger.info( this, "Got to SendEmailServlet()" );

		super.setOriginalEndpoint( "/dotCMS/sendEmail" );
	}
}
