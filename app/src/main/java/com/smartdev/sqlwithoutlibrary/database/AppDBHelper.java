package com.smartdev.sqlwithoutlibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buylist.db";
    private static final int DATABASE_VERSION = 1;

    private static AppDBHelper sInstance;

    public static synchronized AppDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public AppDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*Create Table*/
        final String SQL_CREATE_BUY_LIST_TABLE = "CREATE TABLE " +
                DBContract.BuyListTable.TABLE_NAME + " (" +
                DBContract.BuyListTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.BuyListTable.COLUMN_NAME + " TEXT NOT NULL, " +
                DBContract.BuyListTable.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                DBContract.BuyListTable.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_BUY_LIST_TABLE);
    }

    /* Do something if database architecture change*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* Uništava tabelu */
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.BuyListTable.TABLE_NAME);

        /* Ponovno kreiranje tabele */
        onCreate(db);
    }

    /* Get all items from database */
    public Cursor getAllItems() {
        return sInstance.getReadableDatabase().query(
                DBContract.BuyListTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DBContract.BuyListTable.COLUMN_TIMESTAMP + " DESC"
        );
    }

    /* Insert new item to database*/
    public void insertItem (String name, int amount) {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.BuyListTable.COLUMN_NAME, name);
        cv.put(DBContract.BuyListTable.COLUMN_AMOUNT, amount);
        sInstance.getReadableDatabase().insert(DBContract.BuyListTable.TABLE_NAME, null, cv);
    }

    /*Remove item from database*/
    public void removeItem(long id) {
        sInstance.getReadableDatabase().delete(DBContract.BuyListTable.TABLE_NAME,
                DBContract.BuyListTable._ID + "=" + id, null);
    }
}
