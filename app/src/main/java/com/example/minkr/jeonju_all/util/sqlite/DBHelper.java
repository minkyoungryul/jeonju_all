package com.example.minkr.jeonju_all.util.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.minkr.jeonju_all.util.Logger;

import io.reactivex.disposables.CompositeDisposable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mkr on 2017-08-22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jeonju.db";
    private static final int DATABASE_VERSION = 1; //2가 기본값
    private Context context;
    SQLiteDatabase db;
    private static DBHelper instance;
    CompositeDisposable compositeDisposable;

//    public MessageDBController messageDBController;
//    public BestFriendDBController bestFriendDBController;
//    public MyRoomDBController myRoomDBController;
    public SaveDBController saveDBController;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null)
            instance = new DBHelper(context);

        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        compositeDisposable = new CompositeDisposable();
//        messageDBController = new MessageDBController(compositeSubscription, this);
//        bestFriendDBController = new BestFriendDBController(compositeSubscription, this);
//        myRoomDBController = new MyRoomDBController(compositeSubscription, this);
        saveDBController = new SaveDBController(compositeDisposable, this);

    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
//        String CRETE_TABLE1 = "create table IF NOT EXISTS friend("
//                + "idx integer primary key autoincrement,"
//                + "mb_idx integer default 0,"
//                + "name text,"
//                + "mobile text UNIQUE,"
//                + "photo text default '',"
//                + "best integer default 100 ,"
//                + "is_group text default 'false' ,"
//                + "photo_is_icon text ,"
//                + "catcha_user integer default 0 ,"
//                + "block integer default 'false'"
//                + ")";


        try {
//            db.execSQL(CRETE_TABLE1);
//            db.execSQL(MessageDBController.CREATE_MESSAGE_TABLE);
            db.execSQL(saveDBController.CREATE_SAVE_LIST_TABLE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.log("onUpgrade db -> " + oldVersion + ">>>" + newVersion);
        if(oldVersion >= newVersion)
            return;

//        String query = "DROP TABLE IF EXISTS friend";
//        db.execSQL(query);
//        query = "DROP TABLE IF EXISTS " + MessageDBController._MESSAGE_TABLE_NAME;
//        db.execSQL(query);
//        query = "DROP TABLE IF EXISTS " + MessageDBController._CHATTINGROOM_TABLE_NAME;
//        db.execSQL(query);
//        query = "DROP TABLE IF EXISTS " + MessageDBController._SHARE_TABLE_NAME;
//        db.execSQL(query);
//        query = "DROP TABLE IF EXISTS " + BestFriendDBController._BEST_LIST_TABLE_NAME;
//        db.execSQL(query);
//        query = "DROP TABLE IF EXISTS " + BestFriendDBController._FRIEND_IN_BEST_LIST_TABLE_NAME;
//        db.execSQL(query);


        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void openDB() {
        if (db == null || !db.isOpen()) {
            db = getWritableDatabase();
//            messageDBController.open(db);
//            bestFriendDBController.open(db);
//            myRoomDBController.open(db);
            saveDBController.open(db);
        }
    }

    public void closeDB() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
            compositeDisposable.clear();
            Logger.log("#1500 close db done");
        }
    }

    public boolean isDBOpend() {
        if (db != null) {
            return db.isOpen();
        }

        return false;
    }
}
