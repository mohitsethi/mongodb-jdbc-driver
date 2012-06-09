/*     */ package com.mongodb.jdbc;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URL;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.Date;
/*     */ import java.sql.NClob;
/*     */ import java.sql.ParameterMetaData;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.Ref;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MongoPreparedStatement extends MongoStatement
/*     */   implements PreparedStatement
/*     */ {
/*     */   final String _sql;
/*     */   final Executor _exec;
/* 349 */   List _params = new ArrayList();
/*     */ 
/*     */   MongoPreparedStatement(MongoConnection conn, int type, int concurrency, int holdability, String sql)
/*     */     throws MongoSQLException
/*     */   {
/*  48 */     super(conn, type, concurrency, holdability);
/*  49 */     this._sql = sql;
/*  50 */     this._exec = new Executor(conn._db, sql);
/*     */   }
/*     */ 
/*     */   public void addBatch()
/*     */   {
/*  55 */     throw new UnsupportedOperationException("batch stuff not supported");
/*     */   }
/*     */ 
/*     */   public ResultSetMetaData getMetaData()
/*     */   {
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public ParameterMetaData getParameterMetaData()
/*     */   {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void clearParameters()
/*     */   {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean execute()
/*     */   {
/*  79 */     throw new RuntimeException("execute not done");
/*     */   }
/*     */ 
/*     */   public ResultSet executeQuery()
/*     */   {
/*  84 */     throw new RuntimeException("executeQuery not done");
/*     */   }
/*     */ 
/*     */   public int executeUpdate() throws MongoSQLException
/*     */   {
/*  89 */     this._exec.setParams(this._params);
/*  90 */     return this._exec.writeop();
/*     */   }
/*     */ 
/*     */   public void setArray(int idx, Array x)
/*     */   {
/*  97 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setAsciiStream(int idx, InputStream x)
/*     */   {
/* 102 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setAsciiStream(int idx, InputStream x, int length)
/*     */   {
/* 107 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setAsciiStream(int idx, InputStream x, long length)
/*     */   {
/* 112 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBigDecimal(int idx, BigDecimal x)
/*     */   {
/* 117 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBinaryStream(int idx, InputStream x)
/*     */   {
/* 122 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBinaryStream(int idx, InputStream x, int length)
/*     */   {
/* 127 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBinaryStream(int idx, InputStream x, long length)
/*     */   {
/* 132 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBlob(int idx, Blob x)
/*     */   {
/* 137 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBlob(int idx, InputStream inputStream)
/*     */   {
/* 142 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBlob(int idx, InputStream inputStream, long length)
/*     */   {
/* 147 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBoolean(int idx, boolean x)
/*     */   {
/* 152 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setByte(int idx, byte x)
/*     */   {
/* 157 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setBytes(int idx, byte[] x)
/*     */   {
/* 162 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setCharacterStream(int idx, Reader reader)
/*     */   {
/* 167 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setCharacterStream(int idx, Reader reader, int length)
/*     */   {
/* 172 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setCharacterStream(int idx, Reader reader, long length)
/*     */   {
/* 177 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setClob(int idx, Clob x)
/*     */   {
/* 182 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setClob(int idx, Reader reader)
/*     */   {
/* 187 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setClob(int idx, Reader reader, long length)
/*     */   {
/* 192 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setDate(int idx, Date x)
/*     */   {
/* 197 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setDate(int idx, Date x, Calendar cal)
/*     */   {
/* 202 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setDouble(int idx, double x)
/*     */   {
/* 207 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setFloat(int idx, float x)
/*     */   {
/* 212 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setInt(int idx, int x)
/*     */   {
/* 217 */     _set(idx, Integer.valueOf(x));
/*     */   }
/*     */ 
/*     */   public void setLong(int idx, long x)
/*     */   {
/* 222 */     _set(idx, Long.valueOf(x));
/*     */   }
/*     */ 
/*     */   public void setNCharacterStream(int idx, Reader value)
/*     */   {
/* 227 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNCharacterStream(int idx, Reader value, long length)
/*     */   {
/* 232 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNClob(int idx, NClob value)
/*     */   {
/* 237 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNClob(int idx, Reader reader)
/*     */   {
/* 242 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNClob(int idx, Reader reader, long length)
/*     */   {
/* 247 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNString(int idx, String value)
/*     */   {
/* 252 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNull(int idx, int sqlType)
/*     */   {
/* 257 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setNull(int idx, int sqlType, String typeName)
/*     */   {
/* 262 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setObject(int idx, Object x)
/*     */   {
/* 267 */     _set(idx, x);
/*     */   }
/*     */ 
/*     */   public void setObject(int idx, Object x, int targetSqlType)
/*     */   {
/* 272 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setObject(int idx, Object x, int targetSqlType, int scaleOrLength)
/*     */   {
/* 277 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setRef(int idx, Ref x)
/*     */   {
/* 282 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setRowId(int idx, RowId x)
/*     */   {
/* 287 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setShort(int idx, short x)
/*     */   {
/* 292 */     _set(idx, Short.valueOf(x));
/*     */   }
/*     */ 
/*     */   public void setSQLXML(int idx, SQLXML xmlObject)
/*     */   {
/* 297 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setString(int idx, String x)
/*     */   {
/* 302 */     _set(idx, x);
/*     */   }
/*     */ 
/*     */   public void setTime(int idx, Time x)
/*     */   {
/* 307 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setTime(int idx, Time x, Calendar cal)
/*     */   {
/* 312 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setTimestamp(int idx, Timestamp x)
/*     */   {
/* 317 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setTimestamp(int idx, Timestamp x, Calendar cal)
/*     */   {
/* 322 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setUnicodeStream(int idx, InputStream x, int length)
/*     */   {
/* 327 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   public void setURL(int idx, URL x)
/*     */   {
/* 332 */     _setnotdone();
/*     */   }
/*     */ 
/*     */   void _setnotdone()
/*     */   {
/* 337 */     throw new UnsupportedOperationException("setter not done");
/*     */   }
/*     */ 
/*     */   void _set(int idx, Object o)
/*     */   {
/* 342 */     while (this._params.size() <= idx)
/* 343 */       this._params.add(null);
/* 344 */     this._params.set(idx, o);
/*     */   }
/*     */ }

/* Location:           /home/mohit/projects/mongodb/mongo-jdbc-driver.jar
 * Qualified Name:     com.mongodb.jdbc.MongoPreparedStatement
 * JD-Core Version:    0.6.0
 */