package com.vladzur.mysyncnotes.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vladzur.mysyncnotes.Database.DatabaseHelper;

/**
 * Created by vladzur on 07-11-14.
 */
public class BaseModel {

    public DatabaseHelper Dbh;
    public SQLiteDatabase Db;
    public Context context;

    public BaseModel() {
    }

    public BaseModel(Context c) {
        context = c;
        Dbh = new DatabaseHelper(c);
    }
}
