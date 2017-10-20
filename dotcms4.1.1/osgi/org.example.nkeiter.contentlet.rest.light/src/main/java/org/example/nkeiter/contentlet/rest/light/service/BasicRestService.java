package org.example.nkeiter.contentlet.rest.light.service;

import com.dotcms.repackage.javax.ws.rs.Path;
import com.dotcms.repackage.javax.ws.rs.core.MediaType;
import com.dotcms.repackage.javax.ws.rs.core.Response;
import com.dotcms.repackage.javax.ws.rs.core.Response.ResponseBuilder;

import com.dotcms.rest.WebResource;

import com.dotmarketing.util.json.JSONArray;
import com.dotmarketing.util.json.JSONObject;

@Path( "/contentletLight" )
public class BasicRestService
{
	public static Class<BasicRestService> clazz = BasicRestService.class;

	protected final WebResource webResource = new WebResource();

	protected static Response respond( JSONArray jsonArray )
	{
		ResponseBuilder responseBuilder = Response.ok( jsonArray.toString(), MediaType.APPLICATION_JSON );

		responseBuilder.header( "Access-Control-Expose-Headers", "Authorization" );
		responseBuilder.header( "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization" );

		return responseBuilder.build();
	}

	protected static Response respond( JSONObject jsonObject )
	{
		ResponseBuilder responseBuilder = Response.ok( jsonObject.toString(), MediaType.APPLICATION_JSON );

		responseBuilder.header( "Access-Control-Expose-Headers", "Authorization" );
		responseBuilder.header( "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization" );

		return responseBuilder.build();
	}
}
