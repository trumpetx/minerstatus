package me.davidgreene.minerstatus.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "minerstatus.db";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL("CREATE TABLE miners (miner TEXT PRIMARY KEY, pool TEXT, errors INTEGER)");
    	db.execSQL("CREATE TABLE config (key TEXT PRIMARY KEY, value TEXT)");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('theme', 'dark')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('show.mtgox', 'true')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('show.tradehill', 'true')");
    	db.execSQL("CREATE TABLE miner_data (miner TEXT, date_long INTEGER, json TEXT, pool_index INTEGER DEFAULT 0)");
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion == 2){
			db.execSQL("ALTER TABLE miners RENAME TO miners_old");
			db.execSQL("CREATE TABLE miners (miner TEXT PRIMARY KEY, pool TEXT, errors INTEGER)");
			db.execSQL("CREATE TABLE config (key TEXT PRIMARY KEY, value TEXT)");
			ContentValues configValues = new ContentValues();
			configValues.put("key", "theme");
			configValues.put("value", "dark");
			db.insert("config", "", configValues);
			Cursor cursor = db.rawQuery("SELECT miner, pool FROM miners_old", new String[]{});
			while(cursor.moveToNext()){
				ContentValues values = new ContentValues();
				values.put("miner", cursor.getString(0));
				values.put("pool", cursor.getString(1));
				values.put("errors", new Integer(0));
				db.insert("miners", "", values);
			}
			cursor.close();
			db.execSQL("DROP TABLE IF EXISTS miners_old");
			oldVersion = 3;
		}
		if(oldVersion == 3){
			db.execSQL("INSERT INTO config (key, value) VALUES ('show.mtgox', 'true')");
			oldVersion = 4;
		}
		if (oldVersion == 4){
			db.execSQL("CREATE TABLE miner_data (miner TEXT, date_long INTEGER, json TEXT)");
			oldVersion = 5;
		}
		if (oldVersion == 5){
			db.execSQL("ALTER TABLE miner_data ADD COLUMN pool_index INTEGER DEFAULT 0");
			oldVersion = 6;
		}		
	}
}