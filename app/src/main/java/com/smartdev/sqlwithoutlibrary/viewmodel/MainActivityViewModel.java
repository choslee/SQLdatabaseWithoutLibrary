package com.smartdev.sqlwithoutlibrary.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.smartdev.sqlwithoutlibrary.database.BuyItemsRepository;
import com.smartdev.sqlwithoutlibrary.view.BuyListAdapter;

public class MainActivityViewModel extends AndroidViewModel {
    private BuyListAdapter mAdapter;
    private BuyItemsRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = BuyItemsRepository.getInstance(application);
        mAdapter = new BuyListAdapter(application, repository.getAllItems());
    }

    public BuyListAdapter getAdapter(){
        return mAdapter;
    }

    /*Notify adapter that something changed*/
    public void updateAdapter() {
        mAdapter.swapCursor(repository.getAllItems());
    }

    /*Insert item to DB*/
    public void insertItemDB(String name, int amount) {
        repository.insertItem(name,amount);
        updateAdapter();
    }

    /* Remove item from DB*/
    public void removeItemFromDB(long id) {
        repository.removeItem(id);
        updateAdapter();
    }




}
