package com.example.stefani.inventariotienda.main;

import android.provider.BaseColumns;

/**
 * Created by Stefani on 21/11/2017.
 */

public final class UserContract {

    private UserContract() {}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_USERTYPE = "userType";
    }

}