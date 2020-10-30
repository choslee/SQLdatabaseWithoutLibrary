package com.smartdev.sqlwithoutlibrary.database;

import android.content.Context;

import com.smartdev.sqlwithoutlibrary.model.BuyItem;
import com.smartdev.sqlwithoutlibrary.view.MainActivity;

import java.util.List;

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

    public List<BuyItem> getAllItems () {
        List<BuyItem> listFromDB = mHandler.getAllItemsFromDB();
        return listFromDB;
    }

    public void insertItem (BuyItem buyItem) {
        mHandler.insertItemToDB(buyItem);
    }

    public void removeItem(long id) {
       mHandler.removeItemFromDB(id);
    }










}
