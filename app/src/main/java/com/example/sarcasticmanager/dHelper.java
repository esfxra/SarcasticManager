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
    static final String DATABASE_NAME = "task";
    static final String TABLE_NAME = "sarcManager";

    // database column names
    public static final String COL_1 = "EMAIL";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "TASKS";
    public static final String COL_5 = "COMPLETED_TASKS";

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
        db.execSQL("create table " + TABLE_NAME + "(EMAIL, USERNAME, PASSWORD, TASKS, COMPLETED_TASKS) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create a new user and add them to the database
    public boolean newUser(String email, String username, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, email);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, pass);
        result = db.insert(TABLE_NAME, null, contentValues);
        if( result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    // once user is logged in, allow them to create tasks
    public boolean insertTask(String task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, task);
        result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    // adds task to task completed section.
    public boolean taskComp()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, COL_4);
        contentValues.put(COL_4, "You actually did something!?");
        result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }





}
