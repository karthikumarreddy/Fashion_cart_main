package com.fashioncart.db;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBConnectionManager {

    private static DBConnectionManager instance;
    private BasicDataSource dataSource;

    private DBConnectionManager() {
        try {
            Properties props = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
            props.load(is);

            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(props.getProperty("db.driver"));
            dataSource.setUrl(props.getProperty("db.url"));
            dataSource.setUsername(props.getProperty("db.username"));
            dataSource.setPassword(props.getProperty("db.password"));
            dataSource.setInitialSize(5);
            dataSource.setMaxTotal(10);

        } catch (Exception e) {
            throw new RuntimeException("DB initialization failed", e);
        }
    }

    public static DBConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DBConnectionManager.class) {
                if (instance == null) {
                    instance = new DBConnectionManager();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
