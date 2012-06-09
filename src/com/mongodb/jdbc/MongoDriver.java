package com.mongodb.jdbc;

import com.mongodb.DBAddress;

import com.mongodb.Mongo;

import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

import java.sql.Connection;

import java.sql.Driver;

import java.sql.DriverManager;

import java.sql.DriverPropertyInfo;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import java.util.Properties;

public class MongoDriver implements Driver

{
    static final String PREFIX = "mongodb://";

    static
    {
        try
        {
            DriverManager.registerDriver(new MongoDriver());
        }
        catch (SQLException e)

        {
            throw new RuntimeException(e);
        }
    }

    public boolean acceptsURL(String url)

    {
        return url.startsWith("mongodb://");
    }

    public Connection connect(String url, Properties info) throws SQLException
    {
        if ((info != null) && (info.size() > 0))
        {
            throw new UnsupportedOperationException("properties not supported yet");
        }
        if (url.startsWith("mongodb://"))
        {
            url = url.substring("mongodb://".length());
        }
        if (url.indexOf("/") < 0)
        {
            throw new MongoSQLException("bad url: " + url);
        }

        try
        {
            if (url.contains(","))
            {
                List addrs = new ArrayList();
                for (String str : url.split("/")[0].split(","))
                {
                    addrs.add(new ServerAddress(str.split(":")[0], Integer.parseInt(str.split(":")[1])));
                }
                Mongo mongo = new Mongo(addrs);
                return new MongoConnection(mongo.getDB(url.split("/")[1]));
            }

            DBAddress addr = new DBAddress(url);
            return new MongoConnection(Mongo.connect(addr));
        }
        catch (UnknownHostException uh)
        {
            throw new MongoSQLException("bad url: " + uh);
        }
    }

    public int getMajorVersion()

    {
        return 0;
    }

    public int getMinorVersion()

    {
        /* 105 */return 1;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)

    {
        throw new UnsupportedOperationException("getPropertyInfo doesn't work yet");
    }

    public boolean jdbcCompliant()

    {
        return false;
    }

    public static void install()

    {
    }

}
