package org.example.nkeiter.contentlet.rest.light.beans;

import com.dotmarketing.util.UtilMethods;

import java.util.ArrayList;
import java.util.List;

import org.example.nkeiter.contentlet.rest.light.log.Logger;

/**
 * include fields filter
 * /include-fields/fi%20fie%20fo%20fum
 */
public class FieldFilter
{
	private String field = "";

	public FieldFilter ( String field )
	{
		this.field = field;
	}

	public static List<FieldFilter> getFieldFilters( String fields )
	{
		return getFieldFilters( fields, true );
	}

	public static List<FieldFilter> getFieldFilters( String fields, boolean include )
	{
		List<FieldFilter> list = new ArrayList<FieldFilter>();

		try
		{
			if ( UtilMethods.isSet( fields ) )
			{
				String[] spaceSplit = fields.split( " " );

				for ( String field : spaceSplit )
				{
					FieldFilter fieldFilter = new FieldFilter( field );

					if ( fieldFilter.isSet() )
					{
						list.add( fieldFilter );
					}

				}

			}

		}
		catch ( Exception exception )
		{
			Logger.error( FieldFilter.class, "Error parsing field filters", exception );
		}

		return list;
	}

	public static boolean contains( String field, List<FieldFilter> list )
	{
		for ( FieldFilter fieldFilter : list )
		{
			if ( fieldFilter.getField().equals( field ) )
			{
				return true;
			}
		}

		return false;
	}

	public boolean isSet()
	{
		return UtilMethods.isSet( this.field );
	}

	public String getField()
	{
		return this.field;
	}
}
