package com.example.cw2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditMenuActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, descriptionEditText, categoryEditText, priceEditText;
    private Button saveButton, cancelButton;
    private ImageButton backButton;
    private MenuDbHelper dbHelper;
    private long itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the XML layout you just created
        setContentView(R.layout.activity_edit_menu);

        dbHelper = new MenuDbHelper(this);

        nameEditText = findViewById(R.id.edit_text_item_name);
        descriptionEditText = findViewById(R.id.edit_text_item_description);
        categoryEditText = findViewById(R.id.edit_text_item_category);
        priceEditText = findViewById(R.id.edit_text_item_price);
        saveButton = findViewById(R.id.button_save);
        cancelButton = findViewById(R.id.button_cancel);
        backButton = findViewById(R.id.back_button);

        itemId = getIntent().getLongExtra("ITEM_ID", -1);

        if (itemId == -1) {
            Toast.makeText(this, "Error: Invalid item ID.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadItemData();

        saveButton.setOnClickListener(v -> saveChanges());
        cancelButton.setOnClickListener(v -> finish()); // "Cancel" just closes the activity
        backButton.setOnClickListener(v -> finish());
    }

    private void loadItemData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                MenuContract.MenuItemEntry.COLUMN_NAME_ITEM_NAME,
                MenuContract.MenuItemEntry.COLUMN_NAME_DESCRIPTION,
                MenuContract.MenuItemEntry.COLUMN_NAME_CATEGORY,
                MenuContract.MenuItemEntry.COLUMN_NAME_PRICE
        };

        String selection = MenuContract.MenuItemEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        Cursor cursor = db.query(
                MenuContract.MenuItemEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null, null, null
        );

        if (cursor.moveToFirst()) {
            nameEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(MenuContract.MenuItemEntry.COLUMN_NAME_ITEM_NAME)));
            descriptionEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(MenuContract.MenuItemEntry.COLUMN_NAME_DESCRIPTION)));
            categoryEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(MenuContract.MenuItemEntry.COLUMN_NAME_CATEGORY)));
            priceEditText.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow(MenuContract.MenuItemEntry.COLUMN_NAME_PRICE))));
        }
        cursor.close();
    }

    private void saveChanges() {
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String priceStr = priceEditText.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Item Name and Price cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MenuContract.MenuItemEntry.COLUMN_NAME_ITEM_NAME, name);
        values.put(MenuContract.MenuItemEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(MenuContract.MenuItemEntry.COLUMN_NAME_CATEGORY, category);
        values.put(MenuContract.MenuItemEntry.COLUMN_NAME_PRICE, Double.parseDouble(priceStr));

        String selection = MenuContract.MenuItemEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        int count = db.update(
                MenuContract.MenuItemEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if (count > 0) {
            Toast.makeText(this, "Item updated successfully!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Error: Could not update item.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
