package com.example.cw2;

import android.provider.BaseColumns;

public final class MenuContract {
    private MenuContract() {}

    public static class MenuItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "menu_items";
        public static final String COLUMN_NAME_ITEM_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
