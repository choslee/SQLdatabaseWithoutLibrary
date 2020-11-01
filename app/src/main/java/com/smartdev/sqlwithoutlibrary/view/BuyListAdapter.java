package com.smartdev.sqlwithoutlibrary.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.smartdev.sqlwithoutlibrary.R;
import com.smartdev.sqlwithoutlibrary.model.BuyItem;

import java.util.List;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {
    private Context mContext;
    private List<BuyItem> mList;

    public BuyListAdapter(Context context, List<BuyItem> buyItemList) {
        mContext = context;
        mList = buyItemList;
    }
    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;
        View mView;

        public BuyListViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
            mView = itemView;
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

        final BuyItem buyItem = mList.get(position);

        holder.nameText.setText(buyItem.getName());
        holder.countText.setText(String.valueOf(buyItem.getAmount()));
        holder.itemView.setTag(buyItem.getId());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO: napraviti edit stranu */
                Toast.makeText(mContext, "Kliknut je " + buyItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /* Update list in adapter*/
    public void updateAdapter(List<BuyItem> newList) {
        this.mList = newList;
        notifyDataSetChanged();
    }
}
