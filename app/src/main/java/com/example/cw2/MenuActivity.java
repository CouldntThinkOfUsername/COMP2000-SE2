package com.example.cw2;import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cw2.MenuContract.MenuItemEntry;

public class MenuActivity extends AppCompatActivity {
    private MenuDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        dbHelper = new MenuDbHelper(this);
        insertProductData();

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }

    private void insertProductData() {
        //write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //item
        ContentValues values1 = new ContentValues();
        values1.put(MenuItemEntry.COLUMN_NAME_ITEM_NAME, "Margherita Pizza");
        values1.put(MenuItemEntry.COLUMN_NAME_DESCRIPTION, "Classic pizza with tomato, mozzarella, and basil.");
        values1.put(MenuItemEntry.COLUMN_NAME_CATEGORY, "Pizza");
        values1.put(MenuItemEntry.COLUMN_NAME_PRICE, 10.99);

        db.insert(MenuItemEntry.TABLE_NAME, null, values1);

        //item
        ContentValues values2 = new ContentValues();
        values2.put(MenuItemEntry.COLUMN_NAME_ITEM_NAME, "Caesar Salad");
        values2.put(MenuItemEntry.COLUMN_NAME_DESCRIPTION, "Fresh romaine lettuce with Caesar dressing and croutons.");
        values2.put(MenuItemEntry.COLUMN_NAME_CATEGORY, "Salads");
        values2.put(MenuItemEntry.COLUMN_NAME_PRICE, 8.50);

        db.insert(MenuItemEntry.TABLE_NAME, null, values2);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();//close to save memory
        super.onDestroy();
    }
}