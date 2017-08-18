package org.example.nkeiter.contentlet.rest.light.service;

import com.dotmarketing.util.json.JSONArray;
import com.dotmarketing.util.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.example.nkeiter.contentlet.rest.light.rest.WebResource;

@Path( "/contentletLight" )
public class BasicRestService
{
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
