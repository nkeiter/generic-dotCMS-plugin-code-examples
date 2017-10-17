package org.example.nkeiter.template.viewtool.osgi;

import org.apache.velocity.tools.view.context.ViewContext;
import org.apache.velocity.tools.view.servlet.ServletToolInfo;

import org.example.nkeiter.template.viewtool.log.Logger;
import org.example.nkeiter.template.viewtool.viewtool.TemplateViewTool;

public class TemplateViewToolInfo extends ServletToolInfo
{
	@Override
	public String getClassname()
	{
		Logger.info( this, "Got to getClassname()" );

		return TemplateViewTool.class.getName();
	}

	@Override
	public Object getInstance( Object object )
	{
		//Logger.info( this, "Got to getInstance( Object )" );

		TemplateViewTool templateViewTool = new TemplateViewTool();
		templateViewTool.init( object );

		setScope( ViewContext.REQUEST );

		return templateViewTool;
	}

	@Override
	public String getKey()
	{
		//Logger.info( this, "Got to getKey()" );

		return "templateViewTool";
	}

	@Override
	public String getScope()
	{
		Logger.info( this, "Got to getScope()" );

		return ViewContext.REQUEST;
	}
}
