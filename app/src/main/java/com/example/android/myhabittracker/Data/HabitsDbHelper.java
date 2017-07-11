package com.example.android.myhabittracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import name.habittracker.data.HabitContract.HabitEntry;

import static android.R.attr.name;

/**
 * Created by Bianka Matyas on 11/07/2017.
 */

    public class HabitsDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "habits.db";
        private static final int DATABASE_VERSION = 1;

        public HabitsDbHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitsEntry.TABLE_NAME + "("
                    + HabitsContract.HabitsEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + HabitsContract.HabitsEntry.COLUMN_HABIT_DESCRIPTION + " TEXT NOT NULL, "
                    + HabitsContract.HabitsEntry.COLUMN_MONTH + " TEXT NOT NULL DEFAULT Jan, "
                    + HabitsContract.HabitsEntry.COLUMN_DAY+"INTEGER NOT NULL DEFAULT 1);";

            db.execSQL(SQL_CREATE_MY_HABITS_TABLE),
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

            db.execSQL(SQL_DELETE_TABLE);

            onCreate(db);
        }

        public void insertHabit(String habitName, int yearOfDate, String monthOfDate, int dayOfDate) {
            ContentValues values = new ContentValues();
            values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);
            values.put(HabitEntry.COLUMN_DATE_YEAR, yearOfDate);
            values.put(HabitEntry.COLUMN_DATE_MONTH, monthOfDate);
            values.put(HabitEntry.COLUMN_DATE_DAY, dayOfDate);

            SQLiteDatabase database = getWritableDatabase();
            database.insert(HabitEntry.TABLE_NAME, null, values);
        }

        public Cursor readAllHabits() {

            String[] projection = {
                    HabitEntry._ID,
                    HabitEntry.COLUMN_HABIT_NAME,
                    HabitEntry.COLUMN_DATE_YEAR,
                    HabitEntry.COLUMN_DATE_MONTH,
                    HabitEntry.COLUMN_DATE_DAY
            };

            SQLiteDatabase database = getReadableDatabase();

            return database.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        }
    }


}
