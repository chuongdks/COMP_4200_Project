package com.example.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    // method is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE course (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        db.execSQL(query);
    }

    // method is called when the database needs to be upgrade to a new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing "course" table if it exists
        String query = "DROP TABLE IF EXISTS course";
        db.execSQL(query);
        // recreate the db
        onCreate(db);
    }

    // method to Insert a new row into the "course" table with a given title and desc
    public long addData(String titleInput, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();                      // help insert into db using key value pair
        contentValues.put("title", titleInput);                                 // !!! The KEY MUST MATCH the attribute name
        contentValues.put("description", description);
        return db.insert("course", null, contentValues);    // return as long becuz it will return table info when it does the operation
    }

    // method to Fetch all rows from table and return a cursor pointing to the result set
    public Cursor displayData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM course", null);
        return cursor;  // return cursor pointing to the result set (If cursor 0, nothing in db)
    }

    // method to Delete a row from the table using given title
    public Cursor deleteData(String titleInput) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM course WHERE title=?", new String[]{titleInput} );

        // WHy need this part along with query?
        if (cursor.getCount() > 0) {
            db.delete("course", "title=?", new String[]{titleInput});
        }
        return cursor;  // return cursor pointing to the result set (If cursor 0, nothing in db)
    }

    // method to Update the "description" column using given title
    public long updateData(String titleInput, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();                      // help insert into db using key value pair
        contentValues.put("description", description);                          // !!! The KEY MUST MATCH the attribute name

        // Update the description column given the title and return row # that is affected (Why query is not needed now?)
        return db.update("course", contentValues, "title=?", new String[]{titleInput});    // return as long becuz it will return table info when it does the operation
    }

    // Query by the user
    public Cursor userQuery(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("" + query, null);
        return cursor;  // return cursor pointing to the result set (If cursor 0, nothing in db)
    }


}
