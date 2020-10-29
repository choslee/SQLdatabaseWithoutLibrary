package com.smartdev.sqlwithoutlibrary.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartdev.sqlwithoutlibrary.R;
import com.smartdev.sqlwithoutlibrary.database.AppDBHelper;
import com.smartdev.sqlwithoutlibrary.database.DBContract;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private BuyListAdapter mAdapter;
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDBHelper dbHelper = AppDBHelper.getInstance(this);
        mDatabase = dbHelper.getReadableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BuyListAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);
        mEditTextName = findViewById(R.id.edittext_name);
        mTextViewAmount = findViewById(R.id.textview_amount);
        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }
    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }
    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }
    private void addItem() {
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DBContract.BuyListTable.COLUMN_NAME, name);
        cv.put(DBContract.BuyListTable.COLUMN_AMOUNT, mAmount);
        mDatabase.insert(DBContract.BuyListTable.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
        mEditTextName.getText().clear();
    }


    private Cursor getAllItems() {
        return mDatabase.query(
                DBContract.BuyListTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DBContract.BuyListTable.COLUMN_TIMESTAMP + " DESC"
        );
    }
    private void removeItem(long id) {
        mDatabase.delete(DBContract.BuyListTable.TABLE_NAME,
                DBContract.BuyListTable._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }
}