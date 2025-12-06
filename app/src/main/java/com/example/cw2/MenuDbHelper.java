package com.example.cw2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cw2.MenuContract.MenuItemEntry;

public class MenuDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RestaurantMenu.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MenuItemEntry.TABLE_NAME + " (" +
                    MenuItemEntry._ID + " INTEGER PRIMARY KEY," + //key
                    MenuItemEntry.COLUMN_NAME_ITEM_NAME + " TEXT," + //text
                    MenuItemEntry.COLUMN_NAME_DESCRIPTION + " TEXT," + //text
                    MenuItemEntry.COLUMN_NAME_CATEGORY + " TEXT," + //text
                    MenuItemEntry.COLUMN_NAME_PRICE + " REAL)"; //decimal numbers

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MenuItemEntry.TABLE_NAME; //delte if it exists

    public MenuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES); //creates the table when started
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
