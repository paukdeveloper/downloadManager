package com.datsenko.yevhenii.newdownloadmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.datsenko.yevhenii.newdownloadmanager.model.Show;

/**
 * Created by Yevhenii on 6/17/2016.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database_tv_revival";
    private static final String TABLE_DOWNLOADED_VIDEO = "downloaded_video";

    private static final String DOWN_VIDEO_ID = "_id";
    private static final String DOWN_VIDEO_ID_VIDEO = "id_Video";
    private static final String DOWN_VIDEO_ID_GROUP = "id_Group";
    private static final String DOWN_VIDEO_NAME_VIDEO = "name_Video";
    private static final String DOWN_VIDEO_NAME_GROUP = "name_Group";
    private static final String DOWN_VIDEO_IS_DOWNLOADED = "is_Downloaded";
    private static final String DOWN_VIDEO_PATH_IMAGE = "path_Image";
    private static final String DOWN_VIDEO_URL_IMAGE = "url_Image";
    private static final String DOWN_VIDEO_PATH_VIDEO = "path_Video";
    private static final String DOWN_VIDEO_URL_VIDEO = "url_Video";

    private static String[] allColumns = {DOWN_VIDEO_ID, DOWN_VIDEO_ID_VIDEO, DOWN_VIDEO_ID_GROUP,
            DOWN_VIDEO_NAME_VIDEO, DOWN_VIDEO_NAME_GROUP, DOWN_VIDEO_IS_DOWNLOADED, DOWN_VIDEO_PATH_IMAGE,
            DOWN_VIDEO_URL_IMAGE, DOWN_VIDEO_PATH_VIDEO, DOWN_VIDEO_URL_VIDEO };


    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DOWN_VIDEO = "create table "
                + TABLE_DOWNLOADED_VIDEO + " ("
                + DOWN_VIDEO_ID + " integer primary key autoincrement, "
                + DOWN_VIDEO_ID_VIDEO + " integer not null, "
                + DOWN_VIDEO_ID_GROUP + " integer not null, "
                + DOWN_VIDEO_NAME_VIDEO + " text not null, "
                + DOWN_VIDEO_NAME_GROUP + " text not null, "
                + DOWN_VIDEO_IS_DOWNLOADED + " integer, "
                + DOWN_VIDEO_PATH_IMAGE + " text, "
                + DOWN_VIDEO_URL_IMAGE + " text not null, "
                + DOWN_VIDEO_PATH_VIDEO + " text, "
                + DOWN_VIDEO_URL_VIDEO + " text not null);";
        db.execSQL(CREATE_DOWN_VIDEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOWNLOADED_VIDEO);
        onCreate(db);
    }

    public static ContentValues getContentValueFromShow(Show show) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DOWN_VIDEO_ID_VIDEO, show.getId());
        contentValues.put(DOWN_VIDEO_ID_GROUP, 12);
        contentValues.put(DOWN_VIDEO_NAME_VIDEO,show.getTitle() );
        contentValues.put(DOWN_VIDEO_NAME_GROUP, "everything");
        contentValues.put(DOWN_VIDEO_IS_DOWNLOADED, show.isDownloaded());
        contentValues.put(DOWN_VIDEO_PATH_IMAGE, "null");
        contentValues.put(DOWN_VIDEO_URL_IMAGE, show.getImageUrlSmallPreview());
        contentValues.put(DOWN_VIDEO_PATH_VIDEO, "null");
        contentValues.put(DOWN_VIDEO_URL_VIDEO, show.getUrl());
        return contentValues;
    }

    public static void readTableTABLE_DOWNLOADED_VIDEO(SQLiteDatabase db) {
        Log.d("DataBaseDownloaded","read data");
        String SELECT_EVERYTHING = "SELECT * FROM " + TABLE_DOWNLOADED_VIDEO;
        Cursor cursor = db.rawQuery(SELECT_EVERYTHING, null);
        if (cursor.moveToFirst()) {
           do {
                Log.d("DataBaseDownloaded", cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_ID)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_ID_VIDEO)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_ID_GROUP)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_NAME_VIDEO)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_NAME_GROUP)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_IS_DOWNLOADED)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_PATH_IMAGE)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_URL_IMAGE)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_PATH_VIDEO)) + " \t "
                        + cursor.getString(cursor.getColumnIndex(DOWN_VIDEO_URL_VIDEO)) + " \t ");
            } while (cursor.moveToNext());
        }

    }

}
