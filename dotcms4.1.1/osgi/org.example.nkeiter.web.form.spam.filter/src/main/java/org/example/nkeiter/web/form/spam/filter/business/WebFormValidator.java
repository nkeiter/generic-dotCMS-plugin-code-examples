package org.example.nkeiter.web.form.spam.filter.business;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.example.nkeiter.web.form.spam.filter.beans.WebFormValidatorBean;
import org.example.nkeiter.web.form.spam.filter.key.WebFormKey;
import org.example.nkeiter.web.form.spam.filter.log.Logger;

public class WebFormValidator
{
	public static final Class<WebFormValidator> clazz = WebFormValidator.class;

	public static WebFormValidatorBean isValidWebForm( HttpServletRequest httpServletRequest )
	{
		WebFormValidatorBean webFormValidatorBean = new WebFormValidatorBean();

		try
		{
			// Get form request parameters
			HashMap<String, Object> parameters = new HashMap<String, Object> ( httpServletRequest.getParameterMap() );

			// Check required fields
			for ( String requiredField : WebFormKey.REQUIRED_FIELDS )
			{
				if ( !checkField( parameters, requiredField ) )
				{
					// Requirements have not been satisfied.
					webFormValidatorBean.setValid( false );
					webFormValidatorBean.setReason( "Missing Field [" + requiredField + "]" );

					return webFormValidatorBean;
				}
			}

			// Check required field groups
			for ( String requiredFieldGroup : WebFormKey.REQUIRED_FIELD_GROUPS )
			{
				if ( !checkFieldGroup( parameters, requiredFieldGroup ) )
				{
					// Requirements have not been satisfied.
					webFormValidatorBean.setValid( false );
					webFormValidatorBean.setReason( "Missing Field Group [" + requiredFieldGroup + "]" );

					return webFormValidatorBean;
				}
			}

			// Requirements have been satisfied.
			webFormValidatorBean.setValid( true );

			return webFormValidatorBean;
		}
		catch ( Exception exception )
		{
			// Something went wrong.
			Logger.error( clazz, "WebFormValidator.isValidWebForm( HttpServletRequest )", exception );

			webFormValidatorBean.setValid( false );
			webFormValidatorBean.setReason( "Exception: " + exception + "." );

			return webFormValidatorBean;
		}

	}

	public static boolean isWebForm( HttpServletRequest httpServletRequest )
	{
		try
		{
			// Get the request URI
			String requestURI = httpServletRequest.getRequestURI();

			// Check if the URI is a form submission end point
			for ( String regex : WebFormKey.FORM_SUBMISSION_END_POINTS )
			{
				if ( requestURI.matches( regex ) )
				{
					Logger.info(clazz, "isWebForm = true for URI " + requestURI );

					return true;
				}
			}

			// Requirements have not been satisfied.
			Logger.info(clazz, "isWebForm = false for URI " + requestURI );

			return false;
		}
		catch ( Exception exception )
		{
			// Something went wrong.
			Logger.error( clazz, "WebFormValidator.checkField( HashMap<String, Object>, String )", exception );

			return false;
		}
	}

	private static boolean checkField( HashMap<String, Object> parameters, String requiredField )
	{
		try
		{
			// Separate field aliases
			String [] requiredFieldOptions = requiredField.split( "," );

			// Check if the field exists under any alias
			for ( String requiredFieldOption : requiredFieldOptions )
			{
				if ( parameters.containsKey( requiredFieldOption ) )
				{
					return true;
				}
			}

			// Requirements have not been satisfied.
			return false;

		}
		catch ( Exception exception )
		{
			// Something went wrong.
			Logger.error( clazz, "WebFormValidator.checkField( HashMap<String, Object>, String )", exception );

			return false;
		}
	}

	private static boolean checkFieldGroup( HashMap<String, Object> parameters, String requiredFieldGroup )
	{
		try
		{
			// Check if any of the field group members exist
			if ( checkField( parameters, requiredFieldGroup ) )
			{
				boolean returnValue = true;

				// Separate field names
				String [] requiredFields = requiredFieldGroup.split( "," );

				// Ensure all field group members exist
				for ( String requiredField : requiredFields )
				{
					// boolean AND operation ensures "all true" or else "false" state
					returnValue = returnValue && parameters.containsKey( requiredField );
				}

				// Return findings
				return returnValue;
			}
			// No field group members exist
			else
			{
				// Requirements have been satisfied.
				return true;
			}
		}
		catch ( Exception exception )
		{
			// Something went wrong.
			Logger.error( clazz, "WebFormValidator.checkFieldGroup( HashMap<String, Object>, String )", exception );

			return false;
		}
	}
}
