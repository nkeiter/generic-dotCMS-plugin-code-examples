package org.example.nkeiter.web.form.spam.filter.servlet;

import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class SubmitWebFormServlet extends WebFormServlet
{
	private static final long serialVersionUID = 1L;

	public SubmitWebFormServlet()
	{
		Logger.info( this, "Got to SubmitWebFormServlet()" );

		super.setOriginalEndpoint( "/dotCMS/submitWebForm" );
	}
}
