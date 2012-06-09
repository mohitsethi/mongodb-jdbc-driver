/*     */ package com.mongodb.jdbc;
/*     */ 
/*     */ import com.mongodb.DBCursor;
/*     */ import com.mongodb.DBObject;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.Date;
/*     */ import java.sql.NClob;
/*     */ import java.sql.Ref;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Statement;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MongoResultSet
/*     */   implements ResultSet
/*     */ {
/*     */   final DBCursor _cursor;
/* 650 */   final FieldLookup _fields = new FieldLookup();
/*     */   DBObject _cur;
/* 652 */   int _row = 0;
/* 653 */   boolean _closed = false;
/*     */ 
/*     */   MongoResultSet(DBCursor cursor)
/*     */   {
/*  33 */     this._cursor = cursor;
/*  34 */     this._fields.init(cursor.getKeysWanted());
/*     */   }
/*     */ 
/*     */   public void clearWarnings()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void close() {
/*  42 */     this._closed = true;
/*     */   }
/*     */ 
/*     */   public boolean isClosed() {
/*  46 */     return this._closed;
/*     */   }
/*     */ 
/*     */   public int getConcurrency()
/*     */   {
/*  52 */     return 1007;
/*     */   }
/*     */ 
/*     */   public int getType() {
/*  56 */     return 1003;
/*     */   }
/*     */ 
/*     */   public void setFetchDirection(int direction) {
/*  60 */     if (direction == getFetchDirection())
/*  61 */       return;
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int getFetchDirection() {
/*  66 */     return 1;
/*     */   }
/*     */ 
/*     */   public String getCursorName() {
/*  70 */     return "MongoResultSet: " + this._cursor.toString();
/*     */   }
/*     */ 
/*     */   public ResultSetMetaData getMetaData() {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public SQLWarning getWarnings() {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setFetchSize(int rows) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int getFetchSize() {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Statement getStatement()
/*     */   {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int getHoldability() {
/*  95 */     return 1;
/*     */   }
/*     */ 
/*     */   public boolean absolute(int row)
/*     */   {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void afterLast() {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void beforeFirst() {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean first() {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int getRow() {
/* 117 */     return this._row;
/*     */   }
/*     */ 
/*     */   public boolean isAfterLast() {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean isBeforeFirst() {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean isFirst() {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean isLast() {
/* 131 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean last() {
/* 134 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void moveToCurrentRow() {
/* 137 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void moveToInsertRow() {
/* 140 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean previous() {
/* 143 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void refreshRow() {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean relative(int rows) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean rowDeleted() {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean rowInserted() {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean rowUpdated() {
/* 158 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void insertRow()
/*     */   {
/* 164 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void cancelRowUpdates() {
/* 168 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void deleteRow() {
/* 172 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void updateRow() {
/* 176 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void updateArray(int columnIndex, Array x)
/*     */   {
/* 182 */     throw new UnsupportedOperationException();
/*     */   }
/* 184 */   public void updateArray(String columnName, Array x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateAsciiStream(int columnIndex, InputStream x, int length) {
/* 187 */     throw new UnsupportedOperationException();
/*     */   }
/* 189 */   public void updateAsciiStream(String columnName, InputStream x, int length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateAsciiStream(int columnIndex, InputStream x, long length) {
/* 191 */     throw new UnsupportedOperationException();
/*     */   }
/* 193 */   public void updateAsciiStream(String columnName, InputStream x, long length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateAsciiStream(int columnIndex, InputStream x) {
/* 195 */     throw new UnsupportedOperationException();
/*     */   }
/* 197 */   public void updateAsciiStream(String columnName, InputStream x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateBigDecimal(int columnIndex, BigDecimal x) {
/* 200 */     throw new UnsupportedOperationException();
/*     */   }
/* 202 */   public void updateBigDecimal(String columnName, BigDecimal x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateBinaryStream(int columnIndex, InputStream x, int length) {
/* 205 */     throw new UnsupportedOperationException();
/*     */   }
/* 207 */   public void updateBinaryStream(String columnName, InputStream x, int length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateBinaryStream(int columnIndex, InputStream x, long length) {
/* 209 */     throw new UnsupportedOperationException();
/*     */   }
/* 211 */   public void updateBinaryStream(String columnName, InputStream x, long length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateBinaryStream(int columnIndex, InputStream x) {
/* 213 */     throw new UnsupportedOperationException();
/*     */   }
/* 215 */   public void updateBinaryStream(String columnName, InputStream x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateBlob(int columnIndex, Blob x) {
/* 218 */     throw new UnsupportedOperationException();
/*     */   }
/* 220 */   public void updateBlob(String columnName, Blob x) { throw new UnsupportedOperationException(); } 
/*     */   public void updateBlob(int columnIndex, InputStream x) {
/* 222 */     throw new UnsupportedOperationException();
/*     */   }
/* 224 */   public void updateBlob(String columnName, InputStream x) { throw new UnsupportedOperationException(); } 
/*     */   public void updateBlob(int columnIndex, InputStream x, long l) {
/* 226 */     throw new UnsupportedOperationException();
/*     */   }
/* 228 */   public void updateBlob(String columnName, InputStream x, long l) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateBoolean(int columnIndex, boolean x) {
/* 231 */     throw new UnsupportedOperationException();
/*     */   }
/* 233 */   public void updateBoolean(String columnName, boolean x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateByte(int columnIndex, byte x) {
/* 236 */     throw new UnsupportedOperationException();
/*     */   }
/* 238 */   public void updateByte(String columnName, byte x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateBytes(int columnIndex, byte[] x) {
/* 241 */     throw new UnsupportedOperationException();
/*     */   }
/* 243 */   public void updateBytes(String columnName, byte[] x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateCharacterStream(int columnIndex, Reader x, int length) {
/* 246 */     throw new UnsupportedOperationException();
/*     */   }
/* 248 */   public void updateCharacterStream(String columnName, Reader reader, int length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateCharacterStream(int columnIndex, Reader x, long length) {
/* 250 */     throw new UnsupportedOperationException();
/*     */   }
/* 252 */   public void updateCharacterStream(String columnName, Reader reader, long length) { throw new UnsupportedOperationException(); } 
/*     */   public void updateCharacterStream(int columnIndex, Reader x) {
/* 254 */     throw new UnsupportedOperationException();
/*     */   }
/* 256 */   public void updateCharacterStream(String columnName, Reader reader) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateClob(int columnIndex, Clob x) {
/* 259 */     throw new UnsupportedOperationException();
/*     */   }
/* 261 */   public void updateClob(String columnName, Clob x) { throw new UnsupportedOperationException(); } 
/*     */   public void updateClob(int columnIndex, Reader x) {
/* 263 */     throw new UnsupportedOperationException();
/*     */   }
/* 265 */   public void updateClob(String columnName, Reader x) { throw new UnsupportedOperationException(); } 
/*     */   public void updateClob(int columnIndex, Reader x, long l) {
/* 267 */     throw new UnsupportedOperationException();
/*     */   }
/* 269 */   public void updateClob(String columnName, Reader x, long l) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateDate(int columnIndex, Date x) {
/* 272 */     throw new UnsupportedOperationException();
/*     */   }
/* 274 */   public void updateDate(String columnName, Date x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateDouble(int columnIndex, double x) {
/* 277 */     throw new UnsupportedOperationException();
/*     */   }
/* 279 */   public void updateDouble(String columnName, double x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateFloat(int columnIndex, float x) {
/* 282 */     throw new UnsupportedOperationException();
/*     */   }
/* 284 */   public void updateFloat(String columnName, float x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateInt(int columnIndex, int x) {
/* 287 */     throw new UnsupportedOperationException();
/*     */   }
/* 289 */   public void updateInt(String columnName, int x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateLong(int columnIndex, long x) {
/* 292 */     throw new UnsupportedOperationException();
/*     */   }
/* 294 */   public void updateLong(String columnName, long x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateNull(int columnIndex) {
/* 297 */     throw new UnsupportedOperationException();
/*     */   }
/* 299 */   public void updateNull(String columnName) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateObject(int columnIndex, Object x) {
/* 302 */     throw new UnsupportedOperationException();
/*     */   }
/* 304 */   public void updateObject(int columnIndex, Object x, int scale) { throw new UnsupportedOperationException(); } 
/*     */   public void updateObject(String columnName, Object x) {
/* 306 */     throw new UnsupportedOperationException();
/*     */   }
/* 308 */   public void updateObject(String columnName, Object x, int scale) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateRef(int columnIndex, Ref x) {
/* 311 */     throw new UnsupportedOperationException();
/*     */   }
/* 313 */   public void updateRef(String columnName, Ref x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateRowId(int columnIndex, RowId x) {
/* 316 */     throw new UnsupportedOperationException();
/*     */   }
/* 318 */   public void updateRowId(String columnName, RowId x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateShort(int columnIndex, short x) {
/* 321 */     throw new UnsupportedOperationException();
/*     */   }
/* 323 */   public void updateShort(String columnName, short x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
/* 326 */     throw new UnsupportedOperationException();
/*     */   }
/* 328 */   public void updateSQLXML(String columnName, SQLXML xmlObject) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateString(int columnIndex, String x) {
/* 331 */     throw new UnsupportedOperationException();
/*     */   }
/* 333 */   public void updateString(String columnName, String x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateTime(int columnIndex, Time x) {
/* 336 */     throw new UnsupportedOperationException();
/*     */   }
/* 338 */   public void updateTime(String columnName, Time x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public void updateTimestamp(int columnIndex, Timestamp x) {
/* 341 */     throw new UnsupportedOperationException();
/*     */   }
/* 343 */   public void updateTimestamp(String columnName, Timestamp x) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public Array getArray(int i)
/*     */   {
/* 347 */     return getArray(_find(i));
/*     */   }
/*     */   public Array getArray(String colName) {
/* 350 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public InputStream getAsciiStream(int columnIndex) {
/* 354 */     return getAsciiStream(_find(columnIndex));
/*     */   }
/*     */   public InputStream getAsciiStream(String columnName) {
/* 357 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public BigDecimal getBigDecimal(int columnIndex) {
/* 361 */     return getBigDecimal(_find(columnIndex));
/*     */   }
/*     */   public BigDecimal getBigDecimal(int columnIndex, int scale) {
/* 364 */     return getBigDecimal(_find(columnIndex), scale);
/*     */   }
/*     */   public BigDecimal getBigDecimal(String columnName) {
/* 367 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public BigDecimal getBigDecimal(String columnName, int scale) {
/* 370 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public InputStream getBinaryStream(int columnIndex) {
/* 374 */     return getBinaryStream(_find(columnIndex));
/*     */   }
/*     */   public InputStream getBinaryStream(String columnName) {
/* 377 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Blob getBlob(int i) {
/* 381 */     return getBlob(_find(i));
/*     */   }
/*     */   public Blob getBlob(String colName) {
/* 384 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean getBoolean(int columnIndex) {
/* 388 */     return getBoolean(_find(columnIndex));
/*     */   }
/*     */   public boolean getBoolean(String columnName) {
/* 391 */     Object x = this._cur.get(columnName);
/* 392 */     if (x == null)
/* 393 */       return false;
/* 394 */     return ((Boolean)x).booleanValue();
/*     */   }
/*     */ 
/*     */   public byte getByte(int columnIndex) {
/* 398 */     return getByte(_find(columnIndex));
/*     */   }
/*     */   public byte getByte(String columnName) {
/* 401 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public byte[] getBytes(int columnIndex) {
/* 405 */     return getBytes(_find(columnIndex));
/*     */   }
/*     */   public byte[] getBytes(String columnName) {
/* 408 */     return (byte[])this._cur.get(columnName);
/*     */   }
/*     */ 
/*     */   public Reader getCharacterStream(int columnIndex) {
/* 412 */     return getCharacterStream(_find(columnIndex));
/*     */   }
/*     */   public Reader getCharacterStream(String columnName) {
/* 415 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Clob getClob(int i) {
/* 419 */     return getClob(_find(i));
/*     */   }
/*     */   public Clob getClob(String colName) {
/* 422 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Date getDate(int columnIndex) {
/* 426 */     return getDate(_find(columnIndex));
/*     */   }
/*     */   public Date getDate(int columnIndex, Calendar cal) {
/* 429 */     return getDate(_find(columnIndex), cal);
/*     */   }
/*     */   public Date getDate(String columnName) {
/* 432 */     return (Date)this._cur.get(columnName);
/*     */   }
/*     */   public Date getDate(String columnName, Calendar cal) {
/* 435 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public double getDouble(int columnIndex) {
/* 439 */     return getDouble(_find(columnIndex));
/*     */   }
/*     */   public double getDouble(String columnName) {
/* 442 */     return _getNumber(columnName).doubleValue();
/*     */   }
/*     */ 
/*     */   public float getFloat(int columnIndex) {
/* 446 */     return getFloat(_find(columnIndex));
/*     */   }
/*     */   public float getFloat(String columnName) {
/* 449 */     return _getNumber(columnName).floatValue();
/*     */   }
/*     */ 
/*     */   public int getInt(int columnIndex) {
/* 453 */     return getInt(_find(columnIndex));
/*     */   }
/*     */   public int getInt(String columnName) {
/* 456 */     return _getNumber(columnName).intValue();
/*     */   }
/*     */ 
/*     */   public long getLong(int columnIndex) {
/* 460 */     return getLong(_find(columnIndex));
/*     */   }
/*     */   public long getLong(String columnName) {
/* 463 */     return _getNumber(columnName).longValue();
/*     */   }
/*     */ 
/*     */   public short getShort(int columnIndex) {
/* 467 */     return getShort(_find(columnIndex));
/*     */   }
/*     */   public short getShort(String columnName) {
/* 470 */     return _getNumber(columnName).shortValue();
/*     */   }
/*     */ 
/*     */   Number _getNumber(String n)
/*     */   {
/* 475 */     Number x = (Number)this._cur.get(n);
/* 476 */     if (x == null)
/* 477 */       return Integer.valueOf(0);
/* 478 */     return x;
/*     */   }
/*     */ 
/*     */   public Object getObject(int columnIndex)
/*     */   {
/* 483 */     if (columnIndex == 0)
/* 484 */       return this._cur;
/* 485 */     return getObject(_find(columnIndex));
/*     */   }
/*     */   public Object getObject(int i, Map map) {
/* 488 */     if (i == 0)
/* 489 */       return this._cur;
/* 490 */     return getObject(_find(i), map);
/*     */   }
/*     */   public Object getObject(String columnName) {
/* 493 */     return this._cur.get(columnName);
/*     */   }
/*     */   public Object getObject(String colName, Map map) {
/* 496 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Ref getRef(int i) {
/* 500 */     return getRef(_find(i));
/*     */   }
/*     */   public Ref getRef(String colName) {
/* 503 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public RowId getRowId(int i) {
/* 507 */     return getRowId(_find(i));
/*     */   }
/*     */   public RowId getRowId(String name) {
/* 510 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public SQLXML getSQLXML(int columnIndex)
/*     */   {
/* 515 */     return getSQLXML(_find(columnIndex));
/*     */   }
/*     */   public SQLXML getSQLXML(String columnName) {
/* 518 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public String getString(int columnIndex) {
/* 522 */     return getString(_find(columnIndex));
/*     */   }
/*     */   public String getString(String columnName) {
/* 525 */     Object x = this._cur.get(columnName);
/* 526 */     if (x == null)
/* 527 */       return null;
/* 528 */     return x.toString();
/*     */   }
/*     */ 
/*     */   public Time getTime(int columnIndex) {
/* 532 */     return getTime(_find(columnIndex));
/*     */   }
/*     */   public Time getTime(int columnIndex, Calendar cal) {
/* 535 */     return getTime(_find(columnIndex), cal);
/*     */   }
/*     */   public Time getTime(String columnName) {
/* 538 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public Time getTime(String columnName, Calendar cal) {
/* 541 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Timestamp getTimestamp(int columnIndex) {
/* 545 */     return getTimestamp(_find(columnIndex));
/*     */   }
/*     */   public Timestamp getTimestamp(int columnIndex, Calendar cal) {
/* 548 */     return getTimestamp(_find(columnIndex), cal);
/*     */   }
/*     */   public Timestamp getTimestamp(String columnName) {
/* 551 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public Timestamp getTimestamp(String columnName, Calendar cal) {
/* 554 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public InputStream getUnicodeStream(int columnIndex) {
/* 558 */     return getUnicodeStream(_find(columnIndex));
/*     */   }
/*     */   public InputStream getUnicodeStream(String columnName) {
/* 561 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public URL getURL(int columnIndex) throws SQLException
/*     */   {
/* 566 */     return getURL(_find(columnIndex));
/*     */   }
/*     */ 
/*     */   public URL getURL(String columnName) throws SQLException {
/*     */     try {
/* 571 */       return new URL(getString(columnName));
/*     */     } catch (MalformedURLException m) {
/*     */     }
/* 574 */     throw new SQLException("bad url [" + getString(columnName) + "]");
/*     */   }
/*     */ 
/*     */   public String getNString(int columnIndex)
/*     */   {
/* 581 */     return getNString(_find(columnIndex));
/*     */   }
/*     */   public String getNString(String columnName) {
/* 584 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public NClob getNClob(int columnIndex) {
/* 587 */     return getNClob(_find(columnIndex));
/*     */   }
/*     */   public NClob getNClob(String columnName) {
/* 590 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public Reader getNCharacterStream(int columnIndex) {
/* 593 */     return getNCharacterStream(_find(columnIndex));
/*     */   }
/*     */   public Reader getNCharacterStream(String columnName) {
/* 596 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void updateNCharacterStream(int columnIndex, Reader x) {
/* 599 */     throw new UnsupportedOperationException(); } 
/* 600 */   public void updateNCharacterStream(int columnIndex, Reader x, long length) { throw new UnsupportedOperationException(); } 
/* 601 */   public void updateNCharacterStream(String columnLabel, Reader reader) { throw new UnsupportedOperationException(); } 
/* 602 */   public void updateNCharacterStream(String columnLabel, Reader reader, long length) { throw new UnsupportedOperationException(); } 
/* 603 */   public void updateNClob(int columnIndex, NClob nClob) { throw new UnsupportedOperationException(); } 
/* 604 */   public void updateNClob(int columnIndex, Reader reader) { throw new UnsupportedOperationException(); } 
/* 605 */   public void updateNClob(int columnIndex, Reader reader, long length) { throw new UnsupportedOperationException(); } 
/* 606 */   public void updateNClob(String columnLabel, NClob nClob) { throw new UnsupportedOperationException(); } 
/* 607 */   public void updateNClob(String columnLabel, Reader reader) { throw new UnsupportedOperationException(); } 
/* 608 */   public void updateNClob(String columnLabel, Reader reader, long length) { throw new UnsupportedOperationException(); } 
/* 609 */   public void updateNString(int columnIndex, String nString) { throw new UnsupportedOperationException(); } 
/* 610 */   public void updateNString(String columnLabel, String nString) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean wasNull() {
/* 613 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int findColumn(String columnName)
/*     */   {
/* 619 */     return this._fields.get(columnName);
/*     */   }
/*     */   public String _find(int i) {
/* 622 */     return this._fields.get(i);
/*     */   }
/*     */ 
/*     */   public <T> T unwrap(Class<T> iface)
/*     */     throws SQLException
/*     */   {
/* 629 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException
/*     */   {
/* 634 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean next()
/*     */   {
/* 640 */     if (!this._cursor.hasNext()) {
/* 641 */       return false;
/*     */     }
/* 643 */     this._cur = this._cursor.next();
/* 644 */     return true;
/*     */   }
/*     */ 
/*     */   class FieldLookup
/*     */   {
/* 691 */     final Map<Integer, String> _ids = new HashMap();
/* 692 */     final Map<String, Integer> _strings = new HashMap();
/*     */ 
/*     */     FieldLookup()
/*     */     {
/*     */     }
/*     */ 
/*     */     void init(DBObject o)
/*     */     {
/* 658 */       if (o == null)
/* 659 */         return;
/* 660 */       for (String key : o.keySet())
/* 661 */         get(key);
/*     */     }
/*     */ 
/*     */     int get(String s) {
/* 665 */       Integer i = (Integer)this._strings.get(s);
/* 666 */       if (i == null) {
/* 667 */         i = Integer.valueOf(this._strings.size() + 1);
/* 668 */         _store(i, s);
/*     */       }
/* 670 */       return i.intValue();
/*     */     }
/*     */ 
/*     */     String get(int i) {
/* 674 */       String s = (String)this._ids.get(Integer.valueOf(i));
/* 675 */       if (s != null) {
/* 676 */         return s;
/*     */       }
/* 678 */       init(MongoResultSet.this._cur);
/*     */ 
/* 680 */       s = (String)this._ids.get(Integer.valueOf(i));
/* 681 */       if (s != null)
/* 682 */         return s;
/* 683 */       throw new IllegalArgumentException(i + " is not a valid column id");
/*     */     }
/*     */ 
/*     */     void _store(Integer i, String s) {
/* 687 */       this._ids.put(i, s);
/* 688 */       this._strings.put(s, i);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/mohit/projects/mongodb/mongo-jdbc-driver.jar
 * Qualified Name:     com.mongodb.jdbc.MongoResultSet
 * JD-Core Version:    0.6.0
 */