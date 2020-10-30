package com.smartdev.sqlwithoutlibrary.database;

import android.content.Context;
import android.database.Cursor;

import com.smartdev.sqlwithoutlibrary.view.MainActivity;

public class BuyItemsRepository {
    private AppDBHelper mHandler;
    public MainActivity activity;


    public BuyItemsRepository(Context context){
        mHandler = new AppDBHelper(context);
    }

    /* Static instance*/
    private static BuyItemsRepository mInstance = null;
    public static BuyItemsRepository getInstance(Context context){
        if(mInstance == null){
            mInstance = new BuyItemsRepository(context);
        }
        return mInstance;
    }

    public Cursor getAllItems () {
        return mHandler.getAllItems();
    }

    public void insertItem (String name, int amount) {
        mHandler.insertItem(name, amount);
    }

    public void removeItem(long id) {
       mHandler.removeItem(id);
    }








}
