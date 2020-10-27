package com.smartdev.sqlwithoutlibrary.database;

import android.provider.BaseColumns;

public class DBContract {

    public DBContract() {

    }

    public static final class BuyListTable implements BaseColumns {
        public static final String TABLE_NAME = "buyList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "time";
    }
}
