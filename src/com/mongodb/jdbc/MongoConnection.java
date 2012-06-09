/*     */ package com.mongodb.jdbc;
/*     */ 
/*     */ import com.mongodb.DB;
/*     */ import com.mongodb.DBCollection;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Clob;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.NClob;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Savepoint;
/*     */ import java.sql.Statement;
/*     */ import java.sql.Struct;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class MongoConnection
/*     */   implements Connection
/*     */ {
/*     */   DB _db;
/*     */   Properties _clientInfo;
/*     */ 
/*     */   public MongoConnection(DB db)
/*     */   {
/*  46 */     this._db = db;
/*     */   }
/*     */ 
/*     */   public SQLWarning getWarnings()
/*     */   {
/*  51 */     throw new RuntimeException("should do get last error");
/*     */   }
/*     */ 
/*     */   public void clearWarnings()
/*     */   {
/*  56 */     throw new RuntimeException("should reset error");
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*  63 */     this._db = null;
/*     */   }
/*     */ 
/*     */   public boolean isClosed()
/*     */   {
/*  68 */     return this._db == null;
/*     */   }
/*     */ 
/*     */   public void commit()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean getAutoCommit()
/*     */   {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   public void rollback()
/*     */   {
/*  85 */     throw new RuntimeException("can't rollback");
/*     */   }
/*     */ 
/*     */   public void rollback(Savepoint savepoint)
/*     */   {
/*  90 */     throw new RuntimeException("can't rollback");
/*     */   }
/*     */ 
/*     */   public void setAutoCommit(boolean autoCommit)
/*     */   {
/*  95 */     if (!autoCommit)
/*  96 */       throw new RuntimeException("autoCommit has to be on");
/*     */   }
/*     */ 
/*     */   public void releaseSavepoint(Savepoint savepoint)
/*     */   {
/* 101 */     throw new RuntimeException("no savepoints");
/*     */   }
/*     */ 
/*     */   public Savepoint setSavepoint()
/*     */   {
/* 106 */     throw new RuntimeException("no savepoints");
/*     */   }
/*     */ 
/*     */   public Savepoint setSavepoint(String name)
/*     */   {
/* 111 */     throw new RuntimeException("no savepoints");
/*     */   }
/*     */ 
/*     */   public void setTransactionIsolation(int level)
/*     */   {
/* 116 */     throw new RuntimeException("no TransactionIsolation");
/*     */   }
/*     */ 
/*     */   public Array createArrayOf(String typeName, Object[] elements)
/*     */   {
/* 123 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public Struct createStruct(String typeName, Object[] attributes)
/*     */   {
/* 128 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public Blob createBlob()
/*     */   {
/* 133 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public Clob createClob()
/*     */   {
/* 138 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public NClob createNClob()
/*     */   {
/* 143 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public SQLXML createSQLXML()
/*     */   {
/* 148 */     throw new RuntimeException("no create*");
/*     */   }
/*     */ 
/*     */   public String getCatalog()
/*     */   {
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   public void setCatalog(String catalog)
/*     */   {
/* 160 */     throw new RuntimeException("can't set catalog");
/*     */   }
/*     */ 
/*     */   public Properties getClientInfo()
/*     */   {
/* 165 */     return this._clientInfo;
/*     */   }
/*     */ 
/*     */   public String getClientInfo(String name)
/*     */   {
/* 170 */     return (String)this._clientInfo.get(name);
/*     */   }
/*     */ 
/*     */   public void setClientInfo(String name, String value)
/*     */   {
/* 175 */     this._clientInfo.put(name, value);
/*     */   }
/*     */ 
/*     */   public void setClientInfo(Properties properties)
/*     */   {
/* 180 */     this._clientInfo = properties;
/*     */   }
/*     */ 
/*     */   public int getHoldability()
/*     */   {
/* 185 */     return 1;
/*     */   }
/*     */ 
/*     */   public void setHoldability(int holdability)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getTransactionIsolation()
/*     */   {
/* 194 */     return 0;
/*     */   }
/*     */ 
/*     */   public DatabaseMetaData getMetaData()
/*     */   {
/* 199 */     throw new RuntimeException("not dont yet");
/*     */   }
/*     */ 
/*     */   public boolean isValid(int timeout)
/*     */   {
/* 204 */     return this._db != null;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly()
/*     */   {
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   public void setReadOnly(boolean readOnly)
/*     */   {
/* 214 */     if (readOnly)
/* 215 */       throw new RuntimeException("no read only mode");
/*     */   }
/*     */ 
/*     */   public Map<String, Class<?>> getTypeMap()
/*     */   {
/* 220 */     throw new RuntimeException("not done yet");
/*     */   }
/*     */ 
/*     */   public void setTypeMap(Map<String, Class<?>> map)
/*     */   {
/* 225 */     throw new RuntimeException("not done yet");
/*     */   }
/*     */ 
/*     */   public Statement createStatement()
/*     */   {
/* 232 */     return createStatement(0, 0, 0);
/*     */   }
/*     */ 
/*     */   public Statement createStatement(int resultSetType, int resultSetConcurrency)
/*     */   {
/* 237 */     return createStatement(resultSetType, resultSetConcurrency, 0);
/*     */   }
/*     */ 
/*     */   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
/*     */   {
/* 242 */     return new MongoStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
/*     */   }
/*     */ 
/*     */   public CallableStatement prepareCall(String sql)
/*     */   {
/* 249 */     return prepareCall(sql, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
/*     */   {
/* 254 */     return prepareCall(sql, resultSetType, resultSetConcurrency, 0);
/*     */   }
/*     */ 
/*     */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
/*     */   {
/* 260 */     throw new RuntimeException("CallableStatement not supported");
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql)
/*     */     throws SQLException
/*     */   {
/* 266 */     return prepareStatement(sql, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
/*     */   {
/* 271 */     throw new RuntimeException("no PreparedStatement yet");
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
/*     */   {
/* 276 */     throw new RuntimeException("no PreparedStatement yet");
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
/*     */     throws SQLException
/*     */   {
/* 282 */     return prepareStatement(sql, resultSetType, resultSetConcurrency, 0);
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
/*     */     throws SQLException
/*     */   {
/* 288 */     return new MongoPreparedStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability, 
/* 289 */       sql);
/*     */   }
/*     */ 
/*     */   public PreparedStatement prepareStatement(String sql, String[] columnNames)
/*     */   {
/* 294 */     throw new RuntimeException("no PreparedStatement yet");
/*     */   }
/*     */ 
/*     */   public String nativeSQL(String sql)
/*     */   {
/* 301 */     return sql;
/*     */   }
/*     */ 
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException
/*     */   {
/* 306 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException
/*     */   {
/* 311 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public DB getDB()
/*     */   {
/* 316 */     return this._db;
/*     */   }
/*     */ 
/*     */   public DBCollection getCollection(String name)
/*     */   {
/* 321 */     return this._db.getCollection(name);
/*     */   }
/*     */ }

/* Location:           /home/mohit/projects/mongodb/mongo-jdbc-driver.jar
 * Qualified Name:     com.mongodb.jdbc.MongoConnection
 * JD-Core Version:    0.6.0
 */