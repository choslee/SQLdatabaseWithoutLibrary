package com.smartdev.sqlwithoutlibrary.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.smartdev.sqlwithoutlibrary.database.BuyItemsRepository;
import com.smartdev.sqlwithoutlibrary.model.BuyItem;
import com.smartdev.sqlwithoutlibrary.view.BuyListAdapter;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private BuyListAdapter mAdapter;
    private BuyItemsRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = BuyItemsRepository.getInstance(application);
        mAdapter = new BuyListAdapter(application, getBuyItemsList() );

    }

    public BuyListAdapter getAdapter(){
        return mAdapter;
    }

    /*Insert item to DB*/
    public void insertItemDB(BuyItem buyItem) {
        repository.insertItem(buyItem);
        updateAdapter();
    }

    /* Remove item from DB*/
    public void removeItemFromDB(long id) {
        repository.removeItem(id);
        updateAdapter();
    }

    public List<BuyItem> getBuyItemsList() {
        return repository.getAllItems();
    }

    /*Notify adapter that something changed*/
    public void updateAdapter() {
        getAdapter().swapData(repository.getAllItems());
    }

}
