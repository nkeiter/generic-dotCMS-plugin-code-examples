package org.example.nkeiter.generic.viewtool;

import com.dotmarketing.util.Logger;
import com.dotmarketing.util.UtilMethods;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.tools.view.tools.ViewTool;

import org.example.nkeiter.generic.beans.SelfParsingBean;
import org.example.nkeiter.generic.database.SelfParsingBeanStorage;

public class SelfParsingBeanViewTool implements ViewTool 
{
	/**
	 * Example to pull one record.
	 * Usually by primary key field(s)
	 * First result is returned, so sort order is important 
	 * if not using primary key.
	 * 
	 * @param stringField
	 * @return SelfParsingBean
	 */
	public static SelfParsingBean getMyDbTableItemByStringDbField( String stringField )
	{
		List<SelfParsingBean> list = new ArrayList<SelfParsingBean>();
		SelfParsingBean selfParsingBean = null;

		try
		{
			list = SelfParsingBeanStorage.getMyDbTableItemByStringDbField( UtilMethods.sqlify( stringField ), 1 );

			if ( list != null && !list.isEmpty() )
			{
				selfParsingBean = list.get( 0 );
			}
		}
		catch ( Exception exception )
		{
			Logger.error( SelfParsingBeanViewTool.class, "SelfParsingBeanViewTool.getMyDbTableItemByStringDbField( String ) ", exception );
		}

		return selfParsingBean;
	}

	/**
	 * Example which defaults the max results to 100
	 * 
	 * @param stringField
	 * @return List<SelfParsingBean>
	 * @see getMyDbTableItemListByStringDbField( String stringField, int maxResults )
	 */
	public static List<SelfParsingBean> getMyDbTableItemListByStringDbField( String stringField )
	{
		return getMyDbTableItemListByStringDbField( stringField, 100 );
	}

	/**
	 * Example to pull a list of records.
	 * Could be no condition, a single condition, or multiple conditions.
	 * (Single condition shown.)
	 * 
	 * @param stringField
	 * @return List<SelfParsingBean>
	 */
	public static List<SelfParsingBean> getMyDbTableItemListByStringDbField( String stringField, int maxResults )
	{
		List<SelfParsingBean> list = new ArrayList<SelfParsingBean>();

		try
		{
			list = SelfParsingBeanStorage.getMyDbTableItemByStringDbField( UtilMethods.sqlify( stringField ), maxResults );
		}
		catch ( Exception exception )
		{
			Logger.error( SelfParsingBeanViewTool.class, "SelfParsingBeanViewTool.getMyDbTableItemListByStringDbField( String, int ) ", exception );
		}

		return list;
	}

	@Override
	public void init( Object object ) 
	{

	}
}
