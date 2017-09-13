package org.example.nkeiter.web.form.spam.filter.servlet;

public class SubmitWebFormServlet extends WebFormServlet
{
	private static final long serialVersionUID = 1L;

	public SubmitWebFormServlet()
	{
		//Logger.info( this, "Got to SubmitWebFormServlet()" );
		super.setOriginalEndpoint( "/dotCMS/submitWebForm" );
	}
}
