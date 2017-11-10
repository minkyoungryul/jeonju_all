package com.example.minkr.jeonju_all.util.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.main.BookmarkList;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by minkr on 2017-11-09.
 */

public class SaveDBController {
    SQLiteDatabase db;
    CompositeDisposable compositeDisposable;
    DBHelper dbHelper;

    public final static String _SAVE_LIST_TABLE_NAME = "save_list";
    public final static String CREATE_SAVE_LIST_TABLE = "CREATE TABLE IF NOT EXISTS " + _SAVE_LIST_TABLE_NAME + "("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
            +"type VARCHAR, "
            +"title VARCHAR UNIQUE, "
            +"address VARCHAR default null, "
            +"tel VARCHAR default null, "
            +"img_url VARCHAR default null, "
            +"homepage_url VARCHAR default null, "
            +"posX VARCHAR default null, "
            +"posY VARCHAR default null"
            +")";

    public SaveDBController(CompositeDisposable compositeDisposable, DBHelper dbHelper) {
        this.compositeDisposable = compositeDisposable;
        this.dbHelper = dbHelper;
    }

    public void open(SQLiteDatabase db) {
        this.db = db;
    }

    public void addKindFood(KindFoodListData data) {
//        BookmarkList bookmarkList = new BookmarkList();
//        bookmarkList.setType("모범업소");
//        bookmarkList.setTitle(data.getName());
//        bookmarkList.setAddress(data.getAddress());
//        bookmarkList.setTel(data.getTel());
//        bookmarkList.setImg_url(data.getImg_url());
//        bookmarkList.setHomepage_url(Variable._NAVER_STORE_INFO_URL+data.getStoreId());
//        bookmarkList.setPosX(data.getX());
//        bookmarkList.setPosX(data.getY());

        ContentValues contentValues = new ContentValues();
        contentValues.put("type" , "모범업소");
        contentValues.put("title" , data.getName());
        contentValues.put("address" , data.getAddress());
        contentValues.put("tel" , data.getTel());
        contentValues.put("img_url" ,data.getImg_url());
        contentValues.put("homepage_url" , Variable._NAVER_STORE_INFO_URL+data.getStoreId());
        contentValues.put("posX", data.getX());
        contentValues.put("posY", data.getY());

        db.insert(_SAVE_LIST_TABLE_NAME, null, contentValues);
    }

    public void deleteKindFood(String type, KindFoodListData data){
        String query = "DELETE FROM "+_SAVE_LIST_TABLE_NAME+" WHERE type = ? and title = ?";
        db.execSQL(query, new String[]{type, data.getName()});
    }

    public BookmarkList getBookMarkList(Cursor cursor) {
        BookmarkList bookmarkList = new BookmarkList();
        bookmarkList.setType(cursor.getString(cursor.getColumnIndex("type")));
        bookmarkList.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        bookmarkList.setAddress(cursor.getString(cursor.getColumnIndex("address")));
        bookmarkList.setTel(cursor.getString(cursor.getColumnIndex("tel")));
        bookmarkList.setImg_url(cursor.getString(cursor.getColumnIndex("img_url")));
        bookmarkList.setHomepage_url(cursor.getString(cursor.getColumnIndex("homepage_url")));
        bookmarkList.setPosX(cursor.getString(cursor.getColumnIndex("posX")));
        bookmarkList.setPosY(cursor.getString(cursor.getColumnIndex("posY")));

        return bookmarkList;
    }

    public Maybe<List<BookmarkList>> getDBData(String type) {
        String query = "SELECT * FROM "+_SAVE_LIST_TABLE_NAME+" WHERE type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{type});

        List<BookmarkList> datas = new ArrayList<>();
        while(cursor.moveToNext()){
            BookmarkList bookmarkList = getBookMarkList(cursor);
            datas.add(bookmarkList);
        }

        cursor.close();
        return Maybe.just(datas);
    }

    public void addFood(FoodListData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type" , "음식");
        contentValues.put("title" , data.getStoreName());
        contentValues.put("address" , data.getNewAddr());
        contentValues.put("tel" , data.getTel());
        contentValues.put("img_url" ,data.getMainImg());
        contentValues.put("homepage_url" , Variable._NAVER_STORE_INFO_URL+data.getStoreId());
        contentValues.put("posX", data.getPosX());
        contentValues.put("posY", data.getPosY());

        db.insert(_SAVE_LIST_TABLE_NAME, null, contentValues);
    }

    public void deleteFood(String type, FoodListData data) {
        String query = "DELETE FROM "+_SAVE_LIST_TABLE_NAME+" WHERE type = ? and title = ?";
        db.execSQL(query, new String[]{type, data.getStoreName()});
    }

    public Maybe<List<BookmarkList>> getAllBookMarkList() {
        String query = "SELECT * FROM " + _SAVE_LIST_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        List<BookmarkList> datas = new ArrayList<>();
        while (cursor.moveToNext()){
            BookmarkList bookmarkList = getBookMarkList(cursor);
            datas.add(bookmarkList);
        }

        return Maybe.just(datas);
    }

    public void deleteAllData() {
        String query = "DELETE FROM " + _SAVE_LIST_TABLE_NAME;
        db.execSQL(query);
    }

    public void deleteBookmarkData(BookmarkList bookmarkList) {
        String query= "DELETE FROM " + _SAVE_LIST_TABLE_NAME + " WHERE title = ?";
        db.execSQL(query, new String[]{bookmarkList.getTitle()});
    }
}
