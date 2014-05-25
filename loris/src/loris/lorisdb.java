package loris;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class lorisdb {
	
	public static final String DB_DBNAME=Messages.getString("lorisdb.0"); //$NON-NLS-1$
	public static final String DB_TABLENAME=Messages.getString("lorisdb.1"); //$NON-NLS-1$
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
		values.put(Messages.getString("lorisdb.2"), user.name); //$NON-NLS-1$
		values.put(Messages.getString("lorisdb.3"), user.score); //$NON-NLS-1$
		values.put(Messages.getString("lorisdb.4"), user.date); //$NON-NLS-1$
		return dbInstance.insert(DB_TABLENAME, null, values);
	}
	
	public ArrayList<HashMap<String,Object>> getUsers() {
		ArrayList<HashMap<String, Object>> mlist = new ArrayList<HashMap<String,Object>>(100);

		String sql = Messages.getString("lorisdb.5") + DB_TABLENAME +Messages.getString("lorisdb.6"); //$NON-NLS-1$ //$NON-NLS-2$
		
		Cursor cursor = dbInstance.rawQuery(sql, null);
		while(cursor.moveToNext()) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put(Messages.getString("lorisdb.7"), cursor.getString(cursor.getColumnIndex(Messages.getString("lorisdb.8")))); //$NON-NLS-1$ //$NON-NLS-2$
			item.put(Messages.getString("lorisdb.9"), cursor.getString(cursor.getColumnIndex(Messages.getString("lorisdb.10")))); //$NON-NLS-1$ //$NON-NLS-2$
			item.put(Messages.getString("lorisdb.11"), cursor.getString(cursor.getColumnIndex(Messages.getString("lorisdb.12")))); //$NON-NLS-1$ //$NON-NLS-2$
			mlist.add(item);
		}
		return mlist;
	}
	
	public void deleteAll() {
		dbInstance.delete(DB_TABLENAME,null,null);
	}
	
	public int getTotalCount() {
		Cursor number= dbInstance.query(DB_TABLENAME, new String[]{Messages.getString("lorisdb.13")}, null, null, null, null, null); //$NON-NLS-1$
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
			tableCreate.append(Messages.getString("lorisdb.14")) //$NON-NLS-1$
					   .append(DB_TABLENAME)
					   .append(Messages.getString("lorisdb.15")) //$NON-NLS-1$
					   .append(Messages.getString("lorisdb.16")) //$NON-NLS-1$
					   .append(Messages.getString("lorisdb.17")) //$NON-NLS-1$
					   .append(Messages.getString("lorisdb.18")) //$NON-NLS-1$
					   .append(Messages.getString("lorisdb.19")) //$NON-NLS-1$
					   .append(Messages.getString("lorisdb.20")); //$NON-NLS-1$
			System.out.println(tableCreate.toString());
			db.execSQL(tableCreate.toString());
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = Messages.getString("lorisdb.21") + DB_TABLENAME; //$NON-NLS-1$
			db.execSQL(sql);
			myDBHelper.onCreate(db);
		}
	}
}
