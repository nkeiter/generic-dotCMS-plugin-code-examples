package org.example.nkeiter.generic.osgi;

import com.dotmarketing.util.Logger;

import org.apache.velocity.tools.view.context.ViewContext;
import org.apache.velocity.tools.view.servlet.ServletToolInfo;

import org.example.nkeiter.generic.viewtool.SelfParsingBeanViewTool;

public class SelfParsingBeanViewToolInfo extends ServletToolInfo
{
    @Override
    public String getKey()
    {
		//Logger.info( this, "Got to getKey()" );
		
        return "selfParsingBeanViewTool";
    }

    @Override
    public String getScope()
    {
		//Logger.info( this, "Got to getScope()" );
		
        return ViewContext.REQUEST;
    }

    @Override
    public String getClassname()
    {
		//Logger.info( this, "Got to getClassname()" );
		
        return SelfParsingBeanViewTool.class.getName();
    }

    @Override
    public Object getInstance( Object initData )
    {
		//Logger.info( this, "Got to getInstance( Object )" );
		
    	SelfParsingBeanViewTool selfParsingBeanViewTool = new SelfParsingBeanViewTool();
    	selfParsingBeanViewTool.init( initData );

        setScope( ViewContext.REQUEST );

        return selfParsingBeanViewTool;
    }
}