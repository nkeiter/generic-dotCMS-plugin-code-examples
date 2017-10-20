package org.example.nkeiter.generic.database;

public class SelfParsingBeanSQL
{
	public static Class<SelfParsingBeanSQL> clazz = SelfParsingBeanSQL.class;

	public static String GET_MY_DB_TABLE_ITEM_BY_STRING_DB_FIELD_SQL = ""
			+ "select "
			+ "  * "
			+ "from "
			+ "  MY_DB_TABLE "
			+ "where "
			+ "  STRING_DB_FIELD = ? "
			+ "order by "
			+ "  STRING_DB_FIELD ASC ";
}
