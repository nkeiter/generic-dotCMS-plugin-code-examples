package org.example.nkeiter.embedded.spring4.business;

import com.liferay.portal.model.User;

import org.example.nkeiter.embedded.spring4.beans.Stuff;
import org.example.nkeiter.embedded.spring4.exceptions.StuffNotFoundException;
import org.example.nkeiter.embedded.spring4.exceptions.StuffNotRightException;
import org.example.nkeiter.embedded.spring4.forms.StuffForm;

public class StuffFactory
{
	public static Stuff addStuff( User user, StuffForm stuffForm ) throws Exception, StuffNotFoundException, StuffNotRightException
	{
		return new Stuff( stuffForm.getStuffId() );
	}
}
