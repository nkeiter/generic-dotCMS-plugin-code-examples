package org.example.nkeiter.generic.database;

import com.dotmarketing.common.db.DotConnect;
import com.dotmarketing.db.DbConnectionFactory;
import com.dotmarketing.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.nkeiter.generic.beans.SelfParsingBean;

import org.json.JSONArray;

public class SelfParsingBeanStorage
{
	@SuppressWarnings( "unchecked" )
	public static List<SelfParsingBean> getMyDbTableItemByStringDbField( String stringField, int maxResults )
	{
		List<SelfParsingBean> list = new ArrayList<SelfParsingBean>();

		try
		{
			//Logger.info( SelfParsingBeanStorage.class, "Got to SelfParsingBeanStorage.getMyDbTableItemByStringDbField( String, int )" );

			SelfParsingBean selfParsingBean = new SelfParsingBean();
			DotConnect dotConnect = new DotConnect();

			dotConnect.setSQL( SelfParsingBeanSQL.GET_MY_DB_TABLE_ITEM_BY_STRING_DB_FIELD_SQL );

			dotConnect.addParam( stringField );

			List<Map<String,Object>> results = (List<Map<String,Object>>) dotConnect.loadResults();

			if ( results != null && !results.isEmpty() )
			{
				list = selfParsingBean.createList( results, maxResults );
			}

		}
		catch ( Exception exception )
		{
			Logger.error( SelfParsingBeanStorage.class, "SelfParsingBeanStorage.getMyDbTableItemByStringDbField( String, int ) ", exception );
		}
		finally
		{
			DbConnectionFactory.closeConnection();
		}

		return list;
	}

	@SuppressWarnings( "unchecked" )
	public static JSONArray getMyDbTableItemByStringDbFieldJSON( String stringField, int maxResults )
	{
		JSONArray searchResults = new JSONArray();

		try
		{
			//Logger.info( SelfParsingBeanStorage.class, "Got to SelfParsingBeanStorage.getMyDbTableItemByStringDbFieldJSON( String, int )" );

			SelfParsingBean selfParsingBean = new SelfParsingBean();
			DotConnect dotConnect = new DotConnect();

			dotConnect.setSQL( SelfParsingBeanSQL.GET_MY_DB_TABLE_ITEM_BY_STRING_DB_FIELD_SQL );

			dotConnect.addParam( stringField );

			List<Map<String,Object>> results = (List<Map<String,Object>>) dotConnect.loadResults();

			if ( results != null && !results.isEmpty() )
			{
				searchResults = selfParsingBean.createJSONArray( results, maxResults );
			}

		}
		catch ( Exception exception )
		{
			Logger.error( SelfParsingBeanStorage.class, "SelfParsingBeanStorage.getMyDbTableItemByStringDbFieldJSON( String, int ) ", exception );
		}
		finally
		{
			DbConnectionFactory.closeConnection();
		}

		return searchResults;
	}
}
