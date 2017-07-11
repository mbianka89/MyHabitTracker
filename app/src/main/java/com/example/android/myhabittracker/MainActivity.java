package com.example.android.myhabittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//import .myhabittracker.data.HabitContract.HabitsEntry;
//import name..myhabittracker.data.HabitDbHelper;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    private TextView mDbInfoDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbInfoDisplayTextView = (TextView) findViewById(R.id.db_info_display_textview);

        HabitDbHelper databaseHelper = new HabitDbHelper(this);

        addTestHabitsToDatabase(databaseHelper);
        displayDatabaseContent(databaseHelper);
    }

    private void addTestHabitsToDatabase(HabitDbHelper databaseHelper) {
        databaseHelper.insertHabit("Early Morning Run", "July", 11);
        databaseHelper.insertHabit("Exercise at home", "July", 12);
        databaseHelper.insertHabit("Playing with the kids", "July", 13);

    }

    private void displayDatabaseContent(HabitDbHelper databaseHelper) {
        Cursor cursor = databaseHelper.readAllHabits();

        int idColumnIndex = cursor.getColumnIndex(HabitsEntry._ID);
        int descriptionColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABITS_DESCRIPTON);
        int monthColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_MONTH);
        int dayColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_DAY);

        String habitDatabaseContent = "";
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(idColumnIndex);
            String description = cursor.getString(descriptionColumnIndex);
            String month = cursor.getString(monthColumnIndex);
            int day = cursor.getInt(dayColumnIndex);
            habitDatabaseContent = habitDatabaseContent + id + " " + description + " " +
                     " " + month + " " + day + "\n";
        } while (cursor.moveToNext());

        mDbInfoDisplayTextView.setText(habitDatabaseContent);
    }
}
