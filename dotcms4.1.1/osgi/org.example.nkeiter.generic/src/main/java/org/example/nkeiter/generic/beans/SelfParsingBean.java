package org.example.nkeiter.generic.beans;

import com.dotmarketing.util.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.nkeiter.generic.log.Logger;

public class SelfParsingBean
{
	public static final Class<SelfParsingBean> clazz = SelfParsingBean.class;

	private double doubleField;
	private String stringField;

	public SelfParsingBean()
	{
		super();
		this.stringField = "";
		this.doubleField = 0.0;
	}

	public SelfParsingBean( String stringField, double doubleField )
	{
		super();
		this.stringField = stringField;
		this.doubleField = doubleField;
	}

	public JSONArray createJSONArray( List<Map<String,Object>> objectList, int maxResults )
	{
		return new JSONArray( createList( objectList, maxResults ) );
	}

	public List<SelfParsingBean> createList( List<Map<String,Object>> objectList, int maxResults )
	{
		List<SelfParsingBean> selfParsingBeanList = new ArrayList<SelfParsingBean>();

		try
		{
			String stringField;
			double doubleField;

			if ( objectList.size() > 0 )
			{
				for ( Map<String,Object> map : objectList )
				{
					stringField = map.get( "string_db_field" ).toString();
					doubleField = Double.parseDouble( map.get( "double_db_field" ).toString() );

					selfParsingBeanList.add( new SelfParsingBean( stringField, doubleField ) );

					if ( maxResults > 0 && selfParsingBeanList.size() >= maxResults )
					{
						break;
					}

				}
			}

		}
		catch ( Exception exception )
		{
			Logger.error( clazz, "SelfParsingBean.createList( List<Map<String,Object>>, int )", exception );
		}

		return selfParsingBeanList;
	}

	public double getAmountDue()
	{
		return doubleField;
	}

	public String getMemberId()
	{
		return stringField;
	}

	public void setAmountDue( double doubleField )
	{
		this.doubleField = doubleField;
	}

	public void setMemberId( String stringField )
	{
		this.stringField = stringField;
	}
}
