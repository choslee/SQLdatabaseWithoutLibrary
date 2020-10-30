package com.smartdev.sqlwithoutlibrary.model;

public class BuyItem {
    public String mName;
    public String mAmount;
    public String mTimestamp;
    private long mId;


    public long getId(){
        return mId;
    }
    public void setId(long id){
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        this.mAmount = amount;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
