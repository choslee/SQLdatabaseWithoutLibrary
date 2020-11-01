package com.smartdev.sqlwithoutlibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.smartdev.sqlwithoutlibrary.model.BuyItem;

import java.util.ArrayList;
import java.util.List;

public class AppDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buyList.db";
    private static final int DATABASE_VERSION = 3;
    private static AppDBHelper sInstance;
    private final SQLiteDatabase db = getWritableDatabase();

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
    public List<BuyItem> getAllItemsFromDB() {
        List<BuyItem> buyItemsList = new ArrayList<>();
        Cursor cursor = db.query(
                DBContract.BuyListTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DBContract.BuyListTable.COLUMN_TIMESTAMP + " DESC"
        );

        /*Popunjavnje liste iz Cursora*/
        while (cursor.moveToNext()){
            BuyItem item = new BuyItem();
            setItemFromCurrentCursor(item, cursor);
            buyItemsList.add(item);
        }
        return buyItemsList;
    }

    private void setItemFromCurrentCursor(BuyItem item, Cursor cursor) {
        item.setName(cursor.getString(cursor.getColumnIndex(DBContract.BuyListTable.COLUMN_NAME)));
        item.setId(cursor.getLong(cursor.getColumnIndex(DBContract.BuyListTable._ID)));
        item.setAmount(cursor.getString(cursor.getColumnIndex(DBContract.BuyListTable.COLUMN_AMOUNT)));
        item.setmTimestamp(cursor.getString(cursor.getColumnIndex(DBContract.BuyListTable.COLUMN_TIMESTAMP)));
    }

    /* Insert new item to database*/
    public void insertItemToDB(BuyItem buyItem) {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.BuyListTable.COLUMN_NAME, buyItem.getName());
        cv.put(DBContract.BuyListTable.COLUMN_AMOUNT, buyItem.getAmount());
        db.insert(DBContract.BuyListTable.TABLE_NAME, null, cv);
    }

    /*Remove item from database*/
    public void removeItemFromDB(long id) {
        db.delete(DBContract.BuyListTable.TABLE_NAME,
                DBContract.BuyListTable._ID + "=" + id,
                null);

    }
}
