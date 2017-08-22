package org.example.nkeiter.web.form.spam.filter.key;

public class WebFormKey
{
	/** Form Field Documentation
	 * 
	 * -- Form Configuration --
	 * formType/formName
	 * 		Unique identifier of this form.
	 * 		String
	 * return/returnUrl
	 * 		Success destination.
	 * 		String
	 * 		(Defaults to "thankYouPage" struts forward configuration.)
	 * errorUrl
	 * 		Error destination.
	 * 		String
	 * 		(Defaults to referer.)
	 * dispatch
	 * 		Reserved for struts forms.
	 * 		(Input with this name will be ignored.)
	 * 
	 * -- Administrative Email Configuration --
	 * from
	 * 		Email addresses of who administrative email should be FROM.
	 * 		String (Comma separated.)
	 * fromName
	 * 		Name of whom administrative email should be FROM.
	 *		String
	 * to
	 * 		Email addresses of who administrative email should be TO.
	 * 		String (Comma separated.)
	 * toName
	 * 		Name of whom administrative email should be TO.
	 * 		String
	 * cc
	 * 		Email addresses of who administrative email should be CC.
	 * 		String (Comma separated.)
	 * bcc
	 * 		Email addresses of who administrative email should be BCC.
	 * 		String (Comma separated.)
	 * subject
	 * 		Subject of administrative email.
	 * 		String
	 * html
	 * 		Should administrative email be HTML?
	 * 		Boolean
	 * attachFiles
	 * 		Input names of uploaded files to attach to the administrative email.
	 * 		String (Comma separated.)
	 * emailTemplate
	 * 		The path to the velocity email template to be used to generate the administrative email.
	 * 		Can be a path in the dotCMS virtual tree or can be a real path in the server file system under /liferay folder
	 * 		dotCMS paths take precedence over file system paths
	 * 		String
	 * emailFolder
	 * 		Optional sub-folder to save administrative email backups to.
	 * 		Otherwise all administrative email backups are saved to the default location.
	 * 
	 * -- Thank You Email Configuration --
	 * autoReplyFrom
	 * 		Email address of who auto reply email should be FROM.
	 * 		String
	 * autoReplyTo
	 * 		Email address of who auto reply email should be TO.
	 * 		String
	 * 		(Typically this value is captured from a visible input field via JavaScript.)
	 * 		Example: 
	 * 
	 * 		var $jq = jQuery.noConflict();
	 * 
	 * 		//Initialize
	 * 		$jq( document ).ready( function()
	 * 		{
	 * 			var getReplyToEmailInterval = setInterval( "getAutoReplyToEmail()", 250 );
	 * 		});
	 * 
	 * 		function getAutoReplyToEmail() 
	 * 		{ 
	 * 			$jq( '#autoReplyTo' ).val( $jq( '#email' ).val() ); 
	 * 		}
	 *
	 * autoReplySubject
	 * 		Subject of auto reply email.
	 * 		String
	 * autoReplyHtml
	 * 		Should auto reply email be HTML?
	 * 		Boolean
	 * autoReplyText
	 * 		Text of auto reply email.
	 * 		String
	 * autoReplyTemplate
	 * 		The path to the velocity email template to be used to generate the auto reply email.
	 * 		Can be a path in the dotCMS virtual tree or can be a real path in the server file system under /liferay folder
	 * 		dotCMS paths take precedence over file system paths
	 * 		String
	 * 
	 * -- Data Configuration --
	 * order
	 * 		Input names in the order they should appear in the administrative email.
	 * 		String (Comma separated.)
	 * 		(Ignored if emailTemplate is provided.)
	 * prettyOrder
	 * 		Input labels as they should appear in the administrative email.
	 * 		String (Comma separated.)
	 * 		Listed in the same order as the order list provided.
	 * 		(Ignored if emailTemplate is provided.)
	 * ignore
	 * 		Input names to exclude from the administrative email.
	 * 		String (Comma separated.)
	 * 		(Ignored if emailTemplate is provided.)
	 * 
	 * -- Captcha Configuration --
	 * useCaptcha
	 * 		Is captcha used on this form?
	 * 		Boolean
	 * 		(System configuration FORCE_CAPTCHA=true will override this input.)
	 * captcha
	 * 		Captcha value.
	 * 		String
	 * invalidCaptchaReturnUrl
	 * 		Captcha error destination.
	 * 		String
	 * 		(Defaults to errorUrl.)
	 */

	/**
	 * Form submission end points to filter.
	 * 
	 * Regular expressions for uri search.
	 * 
	 * Only matches will be filtered.
	 */
	public static final String[] FORM_SUBMISSION_END_POINTS = {
		".+?submitWebForm.*",
		".+?sendEmail.*"
	};

	/**
	 * Fields that must be present in the request.
	 * 
	 * Each entry is one item.
	 * 
	 * Use commas to allow for field aliases.
	 * 
	 * For example, formType is required, but could be aliased as formName.
	 * So then "formType,formName" would represent the allowed aliases.
	 * 
	 * For stricter filtering without legacy support use "formType".
	 * 
	 * Requiring fields that are normally optional can further enhance filtering.
	 * Making "prettyOrder" required for example.
	 * 
	 * Creating a custom field requirement can further enhance filtering.
	 * Making "abc123xyz" required for example.
	 * 
	 * !!!Warning!!! 
	 * Non-compliant web forms will fail, even if otherwise "valid" by dotCMS specifications.
	 * 
	 * !!!Warning!!! 
	 * Field names are case-sensitive.
	 */
	public static final String[] REQUIRED_FIELDS = {
		"formType,formName",
		"returnUrl,return",
		"to",
		"from",
		"subject"
	};

	/**
	 * Fields that must be present as a group in the request.
	 * 
	 * Each entry is a group.
	 * 
	 * If one field exists, the entire group must exist.
	 * 
	 * Use commas to define each group.
	 * 
	 * !!!Warning!!! 
	 * Non-compliant web forms will fail, even if otherwise "valid" by dotCMS specifications.
	 * 
	 * !!!Warning!!! 
	 * Field names are case-sensitive.
	 */
	public static final String[] REQUIRED_FIELD_GROUPS = {
		"autoReplyFrom,autoReplyTo,autoReplySubject"
	};

	/**
	 * Logging configuration.
	 */
	public static final boolean LOG_WRITE_ON_FAILURE = true;
	public static final String INFO = "info";
	public static final String DEBUG = "debug";
	public static final String WARN = "warn";
	public static final String ERROR = "error";
	public static final String LOG_WRITE_LEVEL = WARN;

}
