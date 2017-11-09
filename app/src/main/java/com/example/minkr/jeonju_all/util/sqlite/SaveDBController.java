package com.example.minkr.jeonju_all.util.sqlite;

import android.database.sqlite.SQLiteDatabase;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by minkr on 2017-11-09.
 */

public class SaveDBController {
    SQLiteDatabase db;
    CompositeDisposable compositeDisposable;
    DBHelper dbHelper;

    public final static String _SAVE_LIST_TABLE_NAME = "save_list";
    public final static String CREATE_SAVE_LIST_TABLE = "CREATE TABLE IF NOT EXISTS " + _SAVE_LIST_TABLE_NAME + "("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"type VARCHAR, "
            +"title VARCHAR, "
            +"address VARCHAR default null, "
            +"tel VARCHAR default null, "
            +"img_url VARCHAR default null"
            +")";

    public SaveDBController(CompositeDisposable compositeDisposable, DBHelper dbHelper) {
        this.compositeDisposable = compositeDisposable;
        this.dbHelper = dbHelper;
    }

    public void open(SQLiteDatabase db) {
        this.db = db;
    }
}
