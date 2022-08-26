
package com.apps.todoro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class notedbhelper extends SQLiteOpenHelper {
    private static String database_Name = "noteDatabase";
    SQLiteDatabase noteDatabase;

    public notedbhelper(Context context) {
        super(context, database_Name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase dbn) {
        dbn.execSQL("create table note (nid integer primary key," +
                " nname text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbn, int oldVersion, int newVersion) {
        dbn.execSQL("drop table if exist note");
    }

    public void createNewnote(String nname) {
        ContentValues rows = new ContentValues();
        rows.put("nname", nname);

        noteDatabase = getWritableDatabase();
        noteDatabase.insert("note", null, rows);
        noteDatabase.close();
    }

    public Cursor fetchAllnotes() {
        noteDatabase = getReadableDatabase();
        String[] rowDetails = {"nname", "nid"};
        Cursor ncursor = noteDatabase.query("note", rowDetails, null, null, null, null, null);
        if (ncursor != null) {
            ncursor.moveToFirst();
            noteDatabase.close();
            return ncursor;
        }
        return ncursor;


    }

    public void delete(String nname) {

        noteDatabase = getReadableDatabase();
        noteDatabase.delete("note", "nname='" + nname + "'", null);
        noteDatabase.close();

    }


}




