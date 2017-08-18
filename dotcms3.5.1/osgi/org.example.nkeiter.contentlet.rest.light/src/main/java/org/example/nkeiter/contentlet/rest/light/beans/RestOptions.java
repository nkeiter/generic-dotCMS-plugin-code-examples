package org.example.nkeiter.contentlet.rest.light.beans;

import com.dotcms.rest.InitDataObject;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.languagesmanager.business.LanguageAPI;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.UtilMethods;

import com.liferay.portal.model.User;

import org.example.nkeiter.contentlet.rest.light.beans.RESTParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestOptions
{
	User user = null;
	String query = "";
	String id = "";
	String orderBy = "";
	String inode = "";
	long languageId = 0;
	int limit = 10;
	int offset = 0;
	boolean live= true;
	boolean idPassed = false;
	boolean inodePassed = false;
	boolean queryPassed = false;
	List<FieldFilter> fieldFilters = new ArrayList<FieldFilter>();

	public RestOptions( InitDataObject initDataObject )
	{
		Map<String, String> parameterMap = initDataObject.getParamsMap();

		debug( parameterMap );

		this.user = initDataObject.getUser();
		this.query = parameterMap.get( RESTParameters.QUERY.getValue() );
		this.id = parameterMap.get( RESTParameters.ID.getValue() );
		this.inode = parameterMap.get( RESTParameters.INODE.getValue() );

		this.setOrderBy( parameterMap.get( RESTParameters.ORDERBY.getValue() ) );
		this.setLanguageId( parameterMap.get( RESTParameters.LANGUAGE.getValue() ) );
		this.setLimit( parameterMap.get( RESTParameters.LIMIT.getValue() ) );
		this.setOffset( parameterMap.get( RESTParameters.OFFSET.getValue() ) );
		this.setLive( parameterMap.get( RESTParameters.LIVE.getValue() ) );
		this.idPassed();
		this.inodePassed();
		this.queryPassed();
		this.addFieldFilters( parameterMap.get( RESTParameters.INCLUDE_FIELDS.getValue() ) );
	}

	private void debug( Map<String, String> parameterMap )
	{
		Logger.info( this, RESTParameters.QUERY.getValue() + " = " + parameterMap.get( RESTParameters.QUERY.getValue() ) );
		Logger.info( this, RESTParameters.ID.getValue() + " = " + parameterMap.get( RESTParameters.ID.getValue() ) );
		Logger.info( this, RESTParameters.INODE.getValue() + " = " + parameterMap.get( RESTParameters.INODE.getValue() ) );
		Logger.info( this, RESTParameters.ORDERBY.getValue() + " = " + parameterMap.get( RESTParameters.ORDERBY.getValue() ) );
		Logger.info( this, RESTParameters.LANGUAGE.getValue() + " = " + parameterMap.get( RESTParameters.LANGUAGE.getValue() ) );
		Logger.info( this, RESTParameters.LIMIT.getValue() + " = " + parameterMap.get( RESTParameters.LIMIT.getValue() ) );
		Logger.info( this, RESTParameters.OFFSET.getValue() + " = " + parameterMap.get( RESTParameters.OFFSET.getValue() ) );
		Logger.info( this, RESTParameters.LIVE.getValue() + " = " + parameterMap.get( RESTParameters.LIVE.getValue() ) );
		Logger.info( this, RESTParameters.INCLUDE_FIELDS.getValue() + " = " + parameterMap.get( RESTParameters.INCLUDE_FIELDS.getValue() ) );
	}

	private void addFieldFilters( String fields )
	{
		this.fieldFilters.addAll( FieldFilter.getFieldFilters( fields ) );
	}

	private void idPassed()
	{
		this.idPassed = UtilMethods.isSet( this.id );
	}

	private void inodePassed()
	{
		this.inodePassed = UtilMethods.isSet( this.inode );
	}

	private void queryPassed()
	{
		this.queryPassed = UtilMethods.isSet( this.query );
	}

	private void setOrderBy( String orderBy )
	{
		this.orderBy = UtilMethods.isSet( orderBy ) ? orderBy : "modDate desc";
	}

	private void setLive( String live )
	{
		this.live = ( live == null || !"false".equals( live ) );
	}
	
	private void setLanguageId( String languageId )
	{
		LanguageAPI languageAPI = APILocator.getLanguageAPI();

		this.languageId = languageAPI.getDefaultLanguage().getId();

		/* Language Parameter Handling, if not passed, use default */
		try
		{
			if ( UtilMethods.isSet( languageId ) )
			{
				this.languageId = Long.parseLong( languageId );
			}
		}
		catch ( NumberFormatException numberFormatException )
		{
			Logger.warn( this, "Invald language id, using default.", numberFormatException );
		}
	}

	private void setLimit( String limit )
	{
		/* Limit Parameter Handling, if not passed, use default */
		try
		{
			if ( UtilMethods.isSet( limit ) )
			{
				this.limit = Integer.parseInt( limit );
			}
		}
		catch ( NumberFormatException numberFormatException )
		{
			Logger.warn( this, "Invald limit, using default.", numberFormatException );
		}
	}

	private void setOffset( String offset )
	{
		/* Offset Parameter Handling, if not passed, use default */
		try
		{
			if ( UtilMethods.isSet( offset ) )
			{
				this.offset = Integer.parseInt( offset );
			}
		}
		catch ( NumberFormatException numberFormatException )
		{
			Logger.warn( this, "Invald offset, using default.", numberFormatException );
		}
	}

	public User getUser()
	{
		return this.user;
	}

	public String getQuery()
	{
		return this.query;
	}

	public String getId()
	{
		return this.id;
	}

	public String getOrderBy()
	{
		return this.orderBy;
	}

	public String getInode()
	{
		return this.inode;
	}

	public long getLanguageId()
	{
		return this.languageId;
	}

	public int getLimit()
	{
		return this.limit;
	}

	public int getOffset()
	{
		return this.offset;
	}

	public boolean isLive()
	{
		return this.live;
	}

	public boolean isIdPassed()
	{
		return this.idPassed;
	}

	public boolean isInodePassed()
	{
		return this.inodePassed;
	}

	public boolean isQueryPassed()
	{
		return this.queryPassed;
	}

	public List<FieldFilter> getFieldFilters()
	{
		return this.fieldFilters;
	}
}
