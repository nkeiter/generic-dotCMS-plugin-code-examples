package org.example.nkeiter.contentlet.rest.light.service;

import com.dotcms.contenttype.business.ContentTypeAPI;
import com.dotcms.contenttype.model.type.ContentType;
import com.dotcms.repackage.javax.ws.rs.GET;
import com.dotcms.repackage.javax.ws.rs.Path;
import com.dotcms.repackage.javax.ws.rs.PathParam;
import com.dotcms.repackage.javax.ws.rs.core.Context;
import com.dotcms.repackage.javax.ws.rs.core.Response;

import com.dotcms.rest.InitDataObject;
import com.dotcms.rest.annotation.NoCache;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.UserAPI;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
//import com.dotmarketing.portlets.structure.model.Structure;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.json.JSONArray;
import com.dotmarketing.util.json.JSONException;
import com.dotmarketing.util.json.JSONObject;
import com.dotmarketing.viewtools.content.util.ContentUtils;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.nkeiter.contentlet.rest.light.beans.FieldFilter;
import org.example.nkeiter.contentlet.rest.light.beans.RestOptions;
import org.example.nkeiter.contentlet.rest.light.log.Logger;

/**
 * Pass a query
 * http://localhost:8080/api/contentletLight/query/+contentType:FooTest%20+deleted:false%20+working:true/limit/3/orderby/FooTest.name%20asc
 * 
 * or an identifier
 * http://localhost:8080/api/contentletLight/id/2943b5eb-9105-4dcf-a1c7-87a9d4dc92a6
 * 
 * or an inode
 * http://localhost:8080/api/contentletLight/inode/aaee9776-8fb7-4501-8048-844912a20405
 * 
 * with an include filter
 * http://localhost:8080/api/contentletLight/query/+contentType:FooTest%20+deleted:false%20+working:true/limit/3/orderby/FooTest.name%20asc/include-fields/fi%20fie%20fo%20fum
 * 
 * with an include filter
 * http://localhost:8080/api/contentletLight/id/2943b5eb-9105-4dcf-a1c7-87a9d4dc92a6/include-fields/fi%20fie%20fo%20fum
 * 
 * with an include filter
 * http://localhost:8080/api/contentletLight/inode/aaee9776-8fb7-4501-8048-844912a20405/include-fields/fi%20fie%20fo%20fum
 */

@Path( "/contentletLight" )
public class ContentletRestServiceLight extends BasicRestService
{
	private static JSONObject contentletToJson( Contentlet contentlet, RestOptions restOptions ) throws JSONException
	{
		JSONObject jsonObject = new JSONObject();

		try
		{
			// dotCMS API's
			// 4.x code
			UserAPI userAPI = APILocator.getUserAPI();
			User systemUser = userAPI.getSystemUser();
			ContentTypeAPI contentTypeAPI = APILocator.getContentTypeAPI( systemUser );

			// 4.x code
			ContentType contentType = contentTypeAPI.find( contentlet.getContentTypeId() );
			String contentTypeVariable = contentType.variable();

			// 3.x code
			//Structure contentType = contentlet.getStructure();

			// 3.x code
			//String contentTypeVariable = contentType.getVelocityVarName();

			jsonObject.put( "structure", contentTypeVariable );

			for ( String key : contentlet.getMap().keySet() )
			{
				if ( FieldFilter.contains( key, restOptions.getFieldFilters() ) )
				{
					jsonObject.put( key, contentlet.getMap().get( key ) );
				}
			}
		}
		catch ( Exception exception )
		{
			Logger.error( ContentletRestServiceLight.class, "Error converting contentlet to JSON.", exception );
		}

		return jsonObject;
	}

	private static JSONArray contentletsToJsoArray( List<Contentlet> contentlets, RestOptions restOptions )
	{
		JSONArray jsonArray = new JSONArray();

		try
		{
			for ( Contentlet contentlet : contentlets )
			{
				JSONObject jsonObject = contentletToJson( contentlet, restOptions );

				if ( UtilMethods.isSet( jsonObject ) )
				{
					jsonArray.add( jsonObject );
				}
			}
		}
		catch ( Exception exception )
		{
			Logger.error( ContentletRestServiceLight.class, "Error converting contentlets to JSONArray.", exception );
		}

		return jsonArray;
	}

	private static List<Contentlet> search( RestOptions restOptions, HttpServletRequest httpServletRequest )
	{
		List<Contentlet> contentlets = new ArrayList<Contentlet>();

		try
		{
			if ( restOptions.isIdPassed() )
			{
				contentlets.add( APILocator.getContentletAPI().findContentletByIdentifier( restOptions.getId(), restOptions.isLive(), restOptions.getLanguageId(), restOptions.getUser(), true ) );
			}
			else if ( restOptions.isInodePassed() )
			{
				contentlets.add( APILocator.getContentletAPI().find( restOptions.getInode(), restOptions.getUser(), true ) );
			}
			else if ( restOptions.isQueryPassed() )
			{
				String tmDate = (String) httpServletRequest.getSession().getAttribute( "tm_date" );
				contentlets = ContentUtils.pull( restOptions.getQuery(), restOptions.getOffset(), restOptions.getLimit(), restOptions.getOrderBy(), restOptions.getUser(), tmDate );
			}
		}
		catch ( Exception exception )
		{
			if ( restOptions.isIdPassed() )
			{
				Logger.warn( ContentletRestServiceLight.class, "Can't find Content with Identifier: " + restOptions.getId(), exception );
			}
			else if ( restOptions.isInodePassed() )
			{
				Logger.warn( ContentletRestServiceLight.class, "Can't find Content with Inode: " + restOptions.getInode(), exception );
			}
			else if ( restOptions.isQueryPassed() )
			{
				Logger.warn( ContentletRestServiceLight.class, "Error searching Content : " + exception.getMessage(), exception );
			}
		}

		return contentlets;
	}

	@NoCache
	@GET
	@Path( "/{parameters: .*}" )
	public Response getContent( @Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse, @PathParam( "parameters" ) String parameters )
	{
		JSONObject jsonObject = new JSONObject();

		try
		{
			InitDataObject initDataObject = webResource.init( parameters, true, httpServletRequest, false, null );
			RestOptions restOptions = new RestOptions( initDataObject );
			List<Contentlet> contentlets = search( restOptions, httpServletRequest );
			JSONArray jsonArray = contentletsToJsoArray( contentlets, restOptions );
			jsonObject.put( "contentlets", jsonArray );

			return super.respond( jsonObject );
		}
		catch ( Exception exception )
		{
			try
			{
				jsonObject.put( "error", exception );
			}
			catch ( JSONException jsonException )
			{
				Logger.error( this, "Error getting error.", jsonException );
			}

			Logger.error( this, "Error searching contentlets.", exception );

			return super.respond( jsonObject );
		}

	}
}
