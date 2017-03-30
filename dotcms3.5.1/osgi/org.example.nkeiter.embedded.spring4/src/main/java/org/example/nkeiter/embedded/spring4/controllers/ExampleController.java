package org.example.nkeiter.embedded.spring4.controllers;

import com.dotmarketing.util.Logger;

import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.nkeiter.embedded.spring4.beans.Stuff;
import org.example.nkeiter.embedded.spring4.business.StuffFactory;
import org.example.nkeiter.embedded.spring4.business.UserFactory;
import org.example.nkeiter.embedded.spring4.exceptions.StuffNotFoundException;
import org.example.nkeiter.embedded.spring4.exceptions.StuffNotRightException;
import org.example.nkeiter.embedded.spring4.exceptions.InvalidFormException;
import org.example.nkeiter.embedded.spring4.forms.StuffForm;
import org.example.nkeiter.embedded.spring4.responses.ControllerResponse;
import org.example.nkeiter.embedded.spring4.responses.ErrorResponse;
import org.example.nkeiter.embedded.spring4.responses.SuccessResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/** /app/spring/example/stuff/ **/
@EnableWebMvc
@Configuration
@RequestMapping ("/stuff")
@Controller
public class ExampleController
{
	/** /app/spring/example/stuff/addStuff/ **/
	@RequestMapping( value = "/addStuff", produces = "application/json", method=RequestMethod.POST, consumes="application/json" )
	public @ResponseBody ControllerResponse addDriverToTrip( HttpServletResponse response, HttpServletRequest request, @RequestBody StuffForm stuffForm )
	{
		User user = UserFactory.getUser( request );

		if ( user == null )
		{
			return new ErrorResponse( "You must be logged in." );
		}

		try
		{
			stuffForm.validate();

			Stuff stuff = StuffFactory.addStuff( user, stuffForm );

			return new SuccessResponse( "Stuff added." );
		}
		catch ( InvalidFormException invalidFormException )
		{
			String message = invalidFormException.getReason();
			Logger.error( this, message, invalidFormException );

			return new ErrorResponse( message );
		}
		catch ( StuffNotRightException stuffNotRightException )
		{
			String message = stuffNotRightException.getReason();
			Logger.error( this, message, stuffNotRightException );

			return new ErrorResponse( message );
		}
		catch ( StuffNotFoundException stuffNotFoundException )
		{
			String message = "Stuff not found.";
			Logger.error( this, message, stuffNotFoundException );

			return new ErrorResponse( message );
		}
		catch ( Exception exception )
		{
			String message = "Stuff Failed";
			Logger.error( this, message, exception );

			return new ErrorResponse( message );
		}

	}
}
