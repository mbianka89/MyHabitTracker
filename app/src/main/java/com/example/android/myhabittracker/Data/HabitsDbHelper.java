package com.example.android.myhabittracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.myhabittracker.Data.HabitsContract.HabitsEntry;


/**
 * Created by Bianka Matyas on 11/07/2017.
 */

    public class HabitsDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "habits.db";
        private static final int DATABASE_VERSION = 1;

        public HabitsDbHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String SQL_CREATE_MY_HABITS_TABLE = "CREATE TABLE " + HabitsEntry.TABLE_NAME + "("
                    + HabitsContract.HabitsEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + HabitsContract.HabitsEntry.COLUMN_HABIT_DESCRIPTION + " TEXT NOT NULL, "
                    + HabitsContract.HabitsEntry.COLUMN_MONTH + " TEXT NOT NULL DEFAULT Jan, "
                    + HabitsContract.HabitsEntry.COLUMN_DAY+"INTEGER NOT NULL DEFAULT 1);";

            db.execSQL(SQL_CREATE_MY_HABITS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String SQL_DELETE_TABLE = "DELETE THE TABLE IN CASE ALREADY CREATED " + HabitsEntry.TABLE_NAME;

            db.execSQL(SQL_DELETE_TABLE);

            onCreate(db);
        }

        public void insertHabit(String habitDescription, int habitsMonth, int habitsDate) {
            ContentValues values = new ContentValues();
            values.put(HabitsContract.HabitsEntry.COLUMN_HABIT_DESCRIPTION, habitDescription);
            values.put(HabitsEntry.COLUMN_MONTH, habitsMonth);
            values.put(HabitsEntry.COLUMN_DAY, habitsDate);

            SQLiteDatabase database = getWritableDatabase();
            database.insert(HabitsEntry.TABLE_NAME, null, values);
        }

        public Cursor readAllHabits() {

            String[] projection = {
                    HabitsEntry._ID,
                    HabitsEntry.COLUMN_HABIT_DESCRIPTION,
                    HabitsEntry.COLUMN_MONTH,
                    HabitsEntry.COLUMN_DAY
            };

            SQLiteDatabase database = getReadableDatabase();

            return database.query(HabitsEntry.TABLE_NAME, projection, null, null, null, null, null);
        }
    }

