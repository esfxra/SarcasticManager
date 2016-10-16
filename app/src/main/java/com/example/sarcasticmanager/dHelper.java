/**
    Author: Chris | Diego | JP
    Date: 10/15/16
    Purpose: database backend
 */


package com.example.sarcasticmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// class to initialize database
class dHelper extends SQLiteOpenHelper {

    // initialize static variables for database
    // will be created and un-configured through-out program life cycle
    static final String DATABASE_NAME = "sarcasmManager";
    static final String TABLE_NAME = "sarcManager";

    // database column names
    public static final String COL_1 = "TASKS";
//    public static final String COL_2 = "DUE_DATE";
//    public static final String COL_3 = "COMPLETED_TASKS";

    //variables
    long result;

    // constructor
    dHelper(Context context)
    {
        // creates database
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    // onCreate creates the table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + "(TASKS, DUE_DATE, COMPLETED_TASKS)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // once user is logged in, allow them to create tasks
    public boolean insertTask(String task)//,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, task);
        //contentValues.put(COL_2, date);
        result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    // adds task to task completed section.
    public boolean taskComp(String comp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_3, comp);
        result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    //obtains data from database
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME , null);
    }

}
