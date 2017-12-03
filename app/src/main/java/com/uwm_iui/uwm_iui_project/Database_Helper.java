package com.uwm_iui.uwm_iui_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joshua Torres on 11/19/2017.
 */

public class Database_Helper extends SQLiteOpenHelper {


    //naming the database and table (contacts) with its columns
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MPM_IUI.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

  //table creation for DB
    private static final String TABLE_CREATE = "create table contacts(id integer primary key not null , " +
            "name text not null, email text not null, uname text not null, pass text not null);";


    public Database_Helper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    //inserting the values into the database from signup.java
    public void insertContact(Contact c)
    {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //to get the number of already existing contacts in the DB...passing the count as the ID
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS,  c.getPass());

        //will insert the contact object into the Database
        db.insert(TABLE_NAME, null, values);
        db.close();

    }


    //verifying that the password and usernames match while logging in
    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String un, pw;
        pw = "not found";

        if(cursor.moveToFirst())
        {
            do {
                un = cursor.getString(0);


                if(un.equals(uname))
                {
                    pw = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

            return pw;

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
