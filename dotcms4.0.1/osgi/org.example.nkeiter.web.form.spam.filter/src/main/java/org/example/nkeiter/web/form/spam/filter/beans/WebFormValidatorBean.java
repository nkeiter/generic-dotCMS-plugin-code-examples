package org.example.nkeiter.web.form.spam.filter.beans;

public class WebFormValidatorBean
{
	private boolean valid = false;
	private String reason = "";

	public boolean isValid()
	{
		return valid;
	}

	public String getReason()
	{
		return reason;
	}

	public void setValid( boolean valid )
	{
		this.valid = valid;
	}

	public void setReason( String reason )
	{
		this.reason = reason;
	}
}
