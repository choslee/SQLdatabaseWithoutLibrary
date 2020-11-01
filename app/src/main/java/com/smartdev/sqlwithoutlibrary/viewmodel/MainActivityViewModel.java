package com.smartdev.sqlwithoutlibrary.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
        mAdapter = new BuyListAdapter(application, repository.getAllItems() );

        /* This is MutableLiveData from repository (not new MutableLiveData<>()) */
        allDataItems = repository.getAllItemsFromRepo();
    }


    private MutableLiveData<List<BuyItem>> allDataItems;
    /* LiveData Getter from ViewModel*/
    public LiveData<List<BuyItem>> getAllItemsFromViewModel() {
        return allDataItems;
    }

    /*Insert item to DB*/
    public void insertItemDB(BuyItem buyItem) {
        repository.insertItem(buyItem);
        /*Set new value end emit that*/
        allDataItems.postValue(repository.getAllItems());
    }

    /* Remove item from DB*/
    public void removeItemFromDB(long id) {
        repository.removeItem(id);
        /*Set new value end emit that*/
        allDataItems.postValue(repository.getAllItems());
    }

    public BuyListAdapter getAdapter(){
        return mAdapter;
    }

}
