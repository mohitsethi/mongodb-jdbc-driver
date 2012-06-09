package com.mongodb.jdbc;

import java.sql.SQLException;

public class MongoSQLException extends SQLException
{
    public MongoSQLException(String msg)
    {
        super(msg);
    }

    public static class BadSQL extends MongoSQLException
    {
        BadSQL(String sql)
        {
            super(sql);
        }
    }
}
