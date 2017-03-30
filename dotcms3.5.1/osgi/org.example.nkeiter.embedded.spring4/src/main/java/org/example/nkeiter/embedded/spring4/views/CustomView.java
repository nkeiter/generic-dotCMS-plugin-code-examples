package org.example.nkeiter.embedded.spring4.views;

import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.util.VelocityUtil;
import com.dotmarketing.velocity.VelocityServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.springframework.web.servlet.View;

/**
 * Copy of the dotCMS View {@link com.dotcms.spring.web.DotView} in order
 * to use our custom spring version and not the one used by dotCMS.
 */
public class CustomView implements View
{
	String pagePath;

	public CustomView ( String pagePath )
	{
		super();
		this.pagePath = pagePath;
	}


	public String getContentType ()
	{
		return null;
	}

	public void render ( Map<String, ?> map, HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		// get the VelocityContext
		VelocityContext ctx = VelocityUtil.getWebContext( request, response );

		if ( !pagePath.startsWith( "redirect:" ) )
		{
			// add the Spring map to the context
			for ( String x : map.keySet() )
			{
				ctx.put( x, map.get( x ) );
			}

			// add the context to the request.attr where it will be picked up and used by the VelocityServlet
			request.setAttribute( VelocityServlet.VELOCITY_CONTEXT, ctx );
			// override the page path
			request.setAttribute( CMSFilter.CMS_FILTER_URI_OVERRIDE, pagePath );

			request.getRequestDispatcher( "/servlets/VelocityServlet" ).forward( request, response );
		}
		else
		{
			pagePath = pagePath.replaceFirst( "redirect:", "" );
			response.sendRedirect( pagePath );
		}
	}
}
