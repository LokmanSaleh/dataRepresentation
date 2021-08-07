package com.automl.datarepresentation.bd;

public class SQLrequest {
	
	public static String GET_RELATIONAL_DB_STUCTURE = 	
			"	\r\n" + 
			"	SELECT \r\n" + 
			"		TABLE_NAME as tableName,\r\n" + 
			"		COLUMN_NAME as columnName, \r\n" + 
			"		(CASE \r\n" + 
			"				WHEN ( EXISTS (\r\n" + 
			"\r\n" + 
			"					select * \r\n" + 
			"					From INFORMATION_SCHEMA.KEY_COLUMN_USAGE as B\r\n" + 
			"					WHERE \r\n" + 
			"					REFERENCED_TABLE_SCHEMA = ? and \r\n" + 
			"					columnName = B.COLUMN_NAME and \r\n" + 
			"					tableName = B.TABLE_NAME\r\n" + 
			"\r\n" + 
			"				)\r\n" + 
			"					 ) THEN true\r\n" + 
			"				ELSE false\r\n" + 
			"		END) as isforeignkey,\r\n" + 
			"		\r\n" + 
			"      	 (select REFERENCED_TABLE_NAME \r\n" + 
			"						From INFORMATION_SCHEMA.KEY_COLUMN_USAGE as B\r\n" + 
			"						WHERE \r\n" + 
			"						REFERENCED_TABLE_SCHEMA = ? and \r\n" + 
			"						columnName = B.COLUMN_NAME and \r\n" + 
			"						tableName = B.TABLE_NAME\r\n" + 
			"			) as parentTable, \r\n" + 
			"            \r\n" + 
			"          (select REFERENCED_COLUMN_NAME\r\n" + 
			"						From INFORMATION_SCHEMA.KEY_COLUMN_USAGE as B\r\n" + 
			"						WHERE \r\n" + 
			"						REFERENCED_TABLE_SCHEMA = ? and \r\n" + 
			"						columnName = B.COLUMN_NAME and \r\n" + 
			"						tableName = B.TABLE_NAME\r\n" + 
			"			) as parentColumn\r\n" + 
			" \r\n" + 
			" \r\n" + 
			"	FROM\r\n" + 
			"    \r\n" + 
			"		INFORMATION_SCHEMA.columns\r\n" + 
			"\r\n" + 
			"	WHERE \r\n" + 
			"\r\n" + 
			"		TABLE_SCHEMA = ? ";
		}
