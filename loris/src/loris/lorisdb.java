package loris;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class lorisdb {
	
	public static final String DB_DBNAME="lorisscore";
	public static final String DB_TABLENAME="score";
	public static final int VERSION = 4;
	public static SQLiteDatabase dbInstance;
	private MyDBHelper myDBHelper;
	private StringBuffer tableCreate;
	private Context context;
	
	public lorisdb(Context context) {
		this.context = context;
	}
	
	public void openDatabase() {
		if(dbInstance == null) {
			myDBHelper = new MyDBHelper(context,DB_DBNAME,VERSION);
			dbInstance = myDBHelper.getWritableDatabase();
		}
	}
	
	
	public long insert( rankitem user) {
		ContentValues values = new ContentValues();
		values.put("name", user.name);
		values.put("score", user.score);
		values.put("date", user.date);
		return dbInstance.insert(DB_TABLENAME, null, values);
	}
	
	public ArrayList<rankitem> getUsers() {
		ArrayList<rankitem> mlist = new ArrayList<rankitem>();

		String sql = "select * from " + DB_TABLENAME +" order by score desc";
		
		Cursor cursor = dbInstance.rawQuery(sql, null);
		while(cursor.moveToNext()) {
			//HashMap<String, Comparable> item = new HashMap<String, Comparable>();
			rankitem tmp = new rankitem();
			tmp.name = cursor.getString(cursor.getColumnIndex("name"));
			tmp.score = Integer.parseInt(cursor.getString(cursor.getColumnIndex("score")));
			tmp.date = cursor.getString(cursor.getColumnIndex("date"));
			mlist.add(tmp);
		}
		return mlist;
	}
	
	public void deleteAll() {
		dbInstance.delete(DB_TABLENAME,null,null);
	}
	
	public int getTotalCount() {
		Cursor number= dbInstance.query(DB_TABLENAME, new String[]{"count(*)"}, null, null, null, null, null);
		number.moveToNext();
		return number.getInt(0);
	}
	

	class MyDBHelper extends SQLiteOpenHelper {

		public MyDBHelper(Context context, String name,
				int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			tableCreate = new StringBuffer();
			tableCreate.append("create table ")
					   .append(DB_TABLENAME)
					   .append(" (")
					   .append("_id integer primary key autoincrement,")
					   .append("name text,")
					   .append("score int,")
					   .append("date text")
					   .append(")");
			System.out.println(tableCreate.toString());
			db.execSQL(tableCreate.toString());
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "drop table if exists " + DB_TABLENAME;
			db.execSQL(sql);
			myDBHelper.onCreate(db);
		}
	}
}
