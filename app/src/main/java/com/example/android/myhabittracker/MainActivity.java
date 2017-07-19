package com.example.android.myhabittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.myhabittracker.Data.HabitsDbHelper;
import com.example.android.myhabittracker.Data.HabitsContract.HabitsEntry;


public class MainActivity extends AppCompatActivity {

    private TextView mDbInfoDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbInfoDisplayTextView = (TextView) findViewById(R.id.db_info_display_textview);

        HabitsDbHelper databaseHelper = new HabitsDbHelper(this);

        addTestHabitToDatabase(databaseHelper);
        displayDatabaseContent(databaseHelper);
    }

    private void addTestHabitToDatabase(HabitsDbHelper databaseHelper) {
        databaseHelper.insertHabit("Early Morning Run",  07 , 11);
        databaseHelper.insertHabit("Exercise at home", 07 , 12);
        databaseHelper.insertHabit("Playing with the kids", 07, 13);

    }

    private void displayDatabaseContent(HabitsDbHelper databaseHelper) {
        Cursor cursor = databaseHelper.readAllHabits();

        int idColumnIndex = cursor.getColumnIndex(HabitsEntry._ID);
        int descriptionColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_DESCRIPTION);
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
