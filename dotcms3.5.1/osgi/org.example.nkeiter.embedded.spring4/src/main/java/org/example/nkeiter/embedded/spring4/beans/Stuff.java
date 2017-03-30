package org.example.nkeiter.embedded.spring4.beans;

public class Stuff
{
	private String stuffId;

	public String getStuffId()
	{
		return stuffId;
	}

	public void setStuffId( String stuffId )
	{
		this.stuffId = stuffId;
	}

	public Stuff( String stuffId )
	{
		this.stuffId = stuffId;
	}
}
