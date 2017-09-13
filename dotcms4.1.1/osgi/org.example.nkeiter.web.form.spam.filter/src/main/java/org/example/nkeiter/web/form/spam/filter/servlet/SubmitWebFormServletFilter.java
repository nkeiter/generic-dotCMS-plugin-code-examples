package org.example.nkeiter.web.form.spam.filter.servlet;

public class SubmitWebFormServletFilter extends WebFormServletFilter
{
	public SubmitWebFormServletFilter()
	{
		//Logger.info( this, "Got to SubmitWebFormServletFilter()" );
		super.setOriginalEndpoint( "/dotCMS/submitWebForm" );
	}
}
