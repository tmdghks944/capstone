package com.example.android.capstone.data;

import android.provider.BaseColumns;

public class RecommendlistContract {

    public static final class RecommendlistEntry implements BaseColumns {
        public static final String TABLE_NAME = "recommendlist";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_PARTY_SIZE = "partySize";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
