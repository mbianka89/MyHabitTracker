package com.example.android.myhabittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Bianka Matyas on 11/07/2017.
 */


public final class HabitsContract {

    private HabitsContract() { }

    public final static class HabitsEntry implements BaseColumns {

        public final static String TABLE_NAME ="My_habits";

        public final static String COLUMN_HABIT_DESCRIPTION = "description";
        public final static String COLUMN_MONTH = "month";
        public final static String COLUMN_DAY = "day";
    }
}