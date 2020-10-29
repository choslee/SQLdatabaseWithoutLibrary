package com.smartdev.sqlwithoutlibrary.database;

import android.content.Context;
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
        final String SQL_CREATE_BUY_LIST_TABLE = "CREATE TABLE " +
                DBContract.BuyListTable.TABLE_NAME + " (" +
                DBContract.BuyListTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.BuyListTable.COLUMN_NAME + " TEXT NOT NULL, " +
                DBContract.BuyListTable.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                DBContract.BuyListTable.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_BUY_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* Uništava tabelu */
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.BuyListTable.TABLE_NAME);

        /* Ponovno kreiranje tabele */
        onCreate(db);
    }
}
