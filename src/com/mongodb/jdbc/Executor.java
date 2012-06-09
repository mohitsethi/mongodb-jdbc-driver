 package com.mongodb.jdbc;
 
 import com.mongodb.BasicDBObject;
 import com.mongodb.DB;
 import com.mongodb.DBCollection;
 import com.mongodb.DBCursor;
 import com.mongodb.DBObject;
 import java.io.StringReader;
 import java.util.Iterator;
 import java.util.List;
 import net.sf.jsqlparser.expression.DoubleValue;
 import net.sf.jsqlparser.expression.Expression;
 import net.sf.jsqlparser.expression.JdbcParameter;
 import net.sf.jsqlparser.expression.LongValue;
 import net.sf.jsqlparser.expression.NullValue;
 import net.sf.jsqlparser.expression.StringValue;
 import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
 import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
 import net.sf.jsqlparser.parser.CCJSqlParserManager;
 import net.sf.jsqlparser.schema.Column;
 import net.sf.jsqlparser.schema.Table;
 import net.sf.jsqlparser.statement.Statement;
 import net.sf.jsqlparser.statement.drop.Drop;
 import net.sf.jsqlparser.statement.insert.Insert;
 import net.sf.jsqlparser.statement.select.AllColumns;
 import net.sf.jsqlparser.statement.select.OrderByElement;
 import net.sf.jsqlparser.statement.select.PlainSelect;
 import net.sf.jsqlparser.statement.select.Select;
 import net.sf.jsqlparser.statement.select.SelectExpressionItem;
 import net.sf.jsqlparser.statement.select.SelectItem;
 import net.sf.jsqlparser.statement.update.Update;
 
 public class Executor
 {
   static final boolean D = false;
   final DB _db;
   final String _sql;
   final Statement _statement;
   List _params;
   int _pos;
 
   Executor(DB db, String sql)
     throws MongoSQLException
   {
/*  58 */     this._db = db;
/*  59 */     this._sql = sql;
/*  60 */     this._statement = parse(sql);
   }
 
   void setParams(List params)
   {
/*  68 */     this._pos = 1;
/*  69 */     this._params = params;
   }
 
   DBCursor query() throws MongoSQLException
   {
/*  74 */     if (!(this._statement instanceof Select)) {
/*  75 */       throw new IllegalArgumentException("not a query sql statement");
     }
/*  77 */     Select select = (Select)this._statement;
/*  78 */     if (!(select.getSelectBody() instanceof PlainSelect)) {
/*  79 */       throw new UnsupportedOperationException("can only handle PlainSelect so far");
     }
/*  81 */     PlainSelect ps = (PlainSelect)select.getSelectBody();
/*  82 */     if (!(ps.getFromItem() instanceof Table)) {
/*  83 */       throw new UnsupportedOperationException("can only handle regular tables");
     }
/*  85 */     DBCollection coll = getCollection((Table)ps.getFromItem());
 
/*  87 */     BasicDBObject fields = new BasicDBObject();
/*  88 */     for (Iterator localIterator = ps.getSelectItems().iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
 
/*  90 */       SelectItem si = (SelectItem)o;
/*  91 */       if ((si instanceof AllColumns))
       {
/*  93 */         if (fields.size() <= 0) break;
/*  94 */         throw new UnsupportedOperationException("can't have * and fields");
       }
 
/*  97 */       if ((si instanceof SelectExpressionItem))
       {
/*  99 */         SelectExpressionItem sei = (SelectExpressionItem)si;
/* 100 */         fields.put(toFieldName(sei.getExpression()), Integer.valueOf(1));
       }
       else
       {
/* 104 */         throw new UnsupportedOperationException("unknown select item: " + si.getClass());
       }
 
     }
 
/* 109 */     DBObject query = parseWhere(ps.getWhere());
 
/* 118 */     DBCursor c = coll.find(query, fields);
 
/* 121 */     List orderBylist = ps.getOrderByElements();
/* 122 */     if ((orderBylist != null) && (orderBylist.size() > 0))
     {
/* 124 */       BasicDBObject order = new BasicDBObject();
/* 125 */       for (int i = 0; i < orderBylist.size(); i++)
       {
/* 127 */         OrderByElement o = (OrderByElement)orderBylist.get(i);
/* 128 */         order.put(o.getColumnReference().toString(), Integer.valueOf(o.isAsc() ? 1 : -1));
       }
/* 130 */       c.sort(order);
     }
 
/* 134 */     return c;
   }
 
   int writeop()
     throws MongoSQLException
   {
/* 140 */     if ((this._statement instanceof Insert))
/* 141 */       return insert((Insert)this._statement);
/* 142 */     if ((this._statement instanceof Update))
/* 143 */       return update((Update)this._statement);
/* 144 */     if ((this._statement instanceof Drop)) {
/* 145 */       return drop((Drop)this._statement);
     }
/* 147 */     throw new RuntimeException("unknown write: " + this._statement.getClass());
   }
 
   int insert(Insert in)
     throws MongoSQLException
   {
/* 153 */     if (in.getColumns() == null) {
/* 154 */       throw new MongoSQLException.BadSQL("have to give column names to insert");
     }
/* 156 */     DBCollection coll = getCollection(in.getTable());
 
/* 160 */     if (!(in.getItemsList() instanceof ExpressionList)) {
/* 161 */       throw new UnsupportedOperationException("need ExpressionList");
     }
/* 163 */     BasicDBObject o = new BasicDBObject();
 
/* 165 */     List valueList = ((ExpressionList)in.getItemsList()).getExpressions();
/* 166 */     if (in.getColumns().size() != valueList.size()) {
/* 167 */       throw new MongoSQLException.BadSQL("number of values and columns have to match");
     }
/* 169 */     for (int i = 0; i < valueList.size(); i++)
     {
/* 171 */       o.put(in.getColumns().get(i).toString(), toConstant((Expression)valueList.get(i)));
     }
 
/* 175 */     coll.insert(new DBObject[] { o });
/* 176 */     return 1;
   }
 
   int update(Update up)
     throws MongoSQLException
   {
/* 182 */     DBObject query = parseWhere(up.getWhere());
 
/* 184 */     BasicDBObject set = new BasicDBObject();
 
/* 186 */     for (int i = 0; i < up.getColumns().size(); i++)
     {
/* 188 */       String k = up.getColumns().get(i).toString();
/* 189 */       Expression v = (Expression)up.getExpressions().get(i);
/* 190 */       set.put(k.toString(), toConstant(v));
     }
 
/* 193 */     DBObject mod = new BasicDBObject("$set", set);
 
/* 195 */     DBCollection coll = getCollection(up.getTable());
/* 196 */     coll.update(query, mod);
/* 197 */     return 1;
   }
 
   int drop(Drop d)
   {
/* 202 */     DBCollection c = this._db.getCollection(d.getName());
/* 203 */     c.drop();
/* 204 */     return 1;
   }
 
   String toFieldName(Expression e)
   {
/* 211 */     if ((e instanceof StringValue))
/* 212 */       return e.toString();
/* 213 */     if ((e instanceof Column))
/* 214 */       return e.toString();
/* 215 */     throw new UnsupportedOperationException("can't turn [" + e + "] " + e.getClass() + " into field name");
   }
 
   Object toConstant(Expression e)
   {
/* 220 */     if ((e instanceof StringValue))
/* 221 */       return ((StringValue)e).getValue();
/* 222 */     if ((e instanceof DoubleValue))
/* 223 */       return Double.valueOf(((DoubleValue)e).getValue());
/* 224 */     if ((e instanceof LongValue))
/* 225 */       return Long.valueOf(((LongValue)e).getValue());
/* 226 */     if ((e instanceof NullValue))
/* 227 */       return null;
/* 228 */     if ((e instanceof JdbcParameter)) {
/* 229 */       return this._params.get(this._pos++);
     }
/* 231 */     throw new UnsupportedOperationException("can't turn [" + e + "] " + e.getClass().getName() + 
/* 232 */       " into constant ");
   }
 
   DBObject parseWhere(Expression e)
   {
/* 237 */     BasicDBObject o = new BasicDBObject();
/* 238 */     if (e == null) {
/* 239 */       return o;
     }
/* 241 */     if ((e instanceof EqualsTo))
     {
/* 243 */       EqualsTo eq = (EqualsTo)e;
/* 244 */       o.put(toFieldName(eq.getLeftExpression()), toConstant(eq.getRightExpression()));
     }
     else
     {
/* 248 */       throw new UnsupportedOperationException("can't handle: " + e.getClass() + " yet");
     }
 
/* 251 */     return o;
   }
 
   Statement parse(String s) throws MongoSQLException
   {
/* 256 */     s = s.trim();
     try
     {
/* 260 */       return new CCJSqlParserManager().parse(new StringReader(s));
     }
     catch (Exception e)
     {
/* 264 */       e.printStackTrace();
/* 265 */     }throw new MongoSQLException.BadSQL(s);
   }
 
   DBCollection getCollection(Table t)
   {
return this._db.getCollection(t.toString());
   }
 }
