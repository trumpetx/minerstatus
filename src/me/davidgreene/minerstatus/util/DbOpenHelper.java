package me.davidgreene.minerstatus.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 14;
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
    	db.execSQL("INSERT INTO config (key, value) VALUES ('connection.timeout', '4000')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('max.errors', '10')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('widget.apiKey', 'none')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('low.hashrate.notification', 'off')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('vibrate.on.notification', 'false')");
    	db.execSQL("INSERT INTO config (key, value) VALUES ('show.ads', 'true')");
    	
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
		if (oldVersion == 6){
			db.execSQL("INSERT INTO config (key, value) VALUES ('connection.timeout', '4000')");
			db.execSQL("INSERT INTO config (key, value) VALUES ('max.errors', '10')");
			oldVersion = 7;
		}	
		if (oldVersion == 7){
			db.execSQL("INSERT INTO config (key, value) VALUES ('show.exchange_bitcoins', 'true')");
			oldVersion = 8;
		}			
		if (oldVersion == 8){
			db.execSQL("INSERT INTO config (key, value) VALUES ('widget.apiKey', 'none')");
			oldVersion = 9;
		}
		if (oldVersion == 9){
			db.execSQL("INSERT INTO config (key, value) VALUES ('low.hashrate.notification', 'off')");
			oldVersion = 10;
		}
		if (oldVersion == 10){
			db.execSQL("DELETE FROM config WHERE key='show.exchange_bitcoins'");
			oldVersion = 11;
		}
		if (oldVersion == 11){
			db.execSQL("DELETE FROM config WHERE key='vibrate.on.notification'");
			db.execSQL("INSERT INTO config (key, value) VALUES ('vibrate.on.notification', 'true')");
			oldVersion = 12;
		}
		if (oldVersion == 12){
			db.execSQL("DELETE FROM config WHERE key='show.ads'");
			db.execSQL("INSERT INTO config (key, value) VALUES ('show.ads', 'true')");
			db.execSQL("UPDATE config SET value='off' WHERE key='low.hashrate.notification' AND value='-1'");
			oldVersion = 13;
		}
		if (oldVersion == 13){
			db.execSQL("DELETE FROM config WHERE key='show.tradehill'");
			oldVersion = 14;
		}
		
		
		
		
    	
		
	}
}