package com.smartdev.sqlwithoutlibrary.database;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.smartdev.sqlwithoutlibrary.model.BuyItem;

import java.util.List;

public class BuyItemsRepository {
    private AppDBHelper mHandler;

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

    /* get list directly from DB */
    public List<BuyItem> getAllItems () {
        List<BuyItem> listFromDB = mHandler.getAllItemsFromDB();
        return listFromDB;
    }

    /* New LiveData, setValue and Emit changes (which value acquired list "listFromDB")  */
    MutableLiveData <List<BuyItem>> allDataItems = new MutableLiveData<>();
    /* Getter and setValue (value "listFromDB") to emitting changes all in one */
    public MutableLiveData<List<BuyItem>> getAllItemsFromRepo() {
        allDataItems.setValue(getAllItems());
        return allDataItems;
    }

    /* Call DB method from repo*/
    public void insertItem (BuyItem buyItem) {
        mHandler.insertItemToDB(buyItem);
    }

    /* Call DB method from repo*/
    public void removeItem(long id) {
       mHandler.removeItemFromDB(id);
    }
}
