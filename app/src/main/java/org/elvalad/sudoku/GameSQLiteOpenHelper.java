package org.elvalad.sudoku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2015/4/9.
 */
public class GameSQLiteOpenHelper extends SQLiteOpenHelper {
    public final static int VERSION = 1;
    public final static String TABLE_NAME = "sudoku_table";
    public final static String ID = "id";
    public final static String DIFFICULTY = "diff";
    public final static String TIME = "time";
    public final static String SCORE = "score";
    public final static String DATABASE_NAME = "sudoku.db";

    public GameSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String str_sql = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DIFFICULTY + " INTEGER, " + SCORE + " INTEGER, " + TIME + " text );";
        db.execSQL(str_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("Sudoku", "onUpgrade");
    }
}
