package com.example.android.capstone.data;

import android.provider.BaseColumns;

public class DetaillistContract {

    public static final class DetaillistEntry implements BaseColumns {
        public static final String TABLE_NAME = "detaillist";
        public static final String COLUMN_DETAIL_NAME = "detailName";
        public static final String COLUMN_DETAIL_SIZE = "detailSize";
        public static final String COLUMN_TIMESTAMPP = "timestampp";
    }
}
