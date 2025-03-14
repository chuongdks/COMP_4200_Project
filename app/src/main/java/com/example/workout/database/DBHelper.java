package com.example.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.context = context;
    }

    // method is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE exercise (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image INTEGER, description TEXT, muscle_group TEXT)";
        db.execSQL(query);
    }

    // method is called when the database needs to be upgrade to a new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing "course" table if it exists
        String query = "DROP TABLE IF EXISTS exercise";
        db.execSQL(query);
        // recreate the db
        onCreate(db);
    }
    // method to Insert a new row into the "course" table with a given title and desc
    public long addData(String titleInput, String description, String muscle) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();                      // help insert into db using key value pair

        contentValues.put("name", titleInput);                                 // !!! The KEY MUST MATCH the attribute name
        contentValues.put("description", description);
        contentValues.put("muscle_group", muscle);

        return db.insert("exercise", null, contentValues);    // return as long becuz it will return table info when it does the operation
    }

    // method to Insert a new row into the table with given info
    public long addExercise(ExerciseDataSet exercise) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", exercise.getName());
        contentValues.put("image", exercise.getImage());
        contentValues.put("description", exercise.getDescription());
        contentValues.put("muscle_group", exercise.getMuscleGroup());

        return db.insert("exercise", null, contentValues);
    }

    // Retrieve all exercises and return by List
    public List<ExerciseDataSet> getAllExercises() {
        List<ExerciseDataSet> exercises = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercise", null);

        while (cursor.moveToNext()) {
            ExerciseDataSet exercise = new ExerciseDataSet(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            exercises.add(exercise);
        }
        cursor.close();
        return exercises;
    }

    // method to Fetch all rows from table and return a cursor pointing to the result set
    public Cursor displayData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercise", null);
        return cursor;  // return cursor pointing to the result set (If cursor 0, nothing in db)
    }

    // method to Delete a row from the table using given title
    public Cursor deleteData(String muscleInput) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercise WHERE muscle_group=?", new String[]{muscleInput} );

        // WHy need this part along with query?
        if (cursor.getCount() > 0) {
            db.delete("exercise", "muscle_group=?", new String[]{muscleInput});
        }

        return cursor;  // return cursor pointing to the result set (If cursor 0, nothing in db)
    }

    // Nuke the database
    public void deleteAllExercises() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM exercise");
        db.execSQL("DELETE FROM sqlite_sequence WHERE name='exercise'"); // Reset ID count
    }

    // method to Update the "description" column using given muscle group
    public long updateData(String muscleInput, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();                      // help insert into db using key value pair
        contentValues.put("description", description);                          // !!! The KEY MUST MATCH the attribute name

        // Update the description column given the title and return row # that is affected (Why query is not needed now?)
        return db.update("exercise", contentValues, "title=?", new String[]{muscleInput});    // return as long becuz it will return table info when it does the operation
    }
}
