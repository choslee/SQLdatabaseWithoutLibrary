package com.smartdev.sqlwithoutlibrary.view;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.smartdev.sqlwithoutlibrary.R;
import com.smartdev.sqlwithoutlibrary.database.DBContract;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public BuyListAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;
        public BuyListViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
        }
    }
    @Override
    public BuyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.buy_item, parent, false);
        return new BuyListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(BuyListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(DBContract.BuyListTable.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(DBContract.BuyListTable.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(DBContract.BuyListTable._ID));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
        holder.itemView.setTag(id);
    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
