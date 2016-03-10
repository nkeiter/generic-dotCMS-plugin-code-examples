package org.example.nkeiter.template.viewtool.viewtool;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.UserAPI;
import com.dotmarketing.portlets.templates.business.TemplateAPI;
import com.dotmarketing.portlets.templates.model.Template;
import com.dotmarketing.util.Logger;

import com.liferay.portal.model.User;

import org.apache.velocity.tools.view.tools.ViewTool;

public class TemplateViewTool implements ViewTool 
{
	/**
	 * Returns a template object.
	 * 
	 * @param inode
	 * @return Template
	 */
	public static Template getTemplate( String inode )
	{
		Template template = null;

		try
		{
			TemplateAPI templateAPI = APILocator.getTemplateAPI();
			UserAPI userAPI = APILocator.getUserAPI();
			User defaultUser = userAPI.getDefaultUser();

			template = templateAPI.find( inode, defaultUser, true );
		}
		catch ( Exception exception )
		{
			Logger.error( TemplateViewTool.class, "TemplateViewTool.getTemplate( String ) ", exception );
		}

		return template;
	}

	@Override
	public void init( Object object ) 
	{

	}
}
