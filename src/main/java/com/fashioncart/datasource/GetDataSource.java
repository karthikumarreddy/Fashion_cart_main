package com.fashioncart.datasource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GetDataSource {
	
	public static DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }
}
