package com.example.navigaiit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_BOOKMARK_TABLE = "USER_BOOKMARK";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BUILDING = "BUILDING";
    public static final String COLUMN_FLOOR = "FLOOR";
    public static final String COLUMN_ROOM = "ROOM";
    public static final String COLUMN_NOTES = "NOTES";
    public static final String USER_PATH_CHECK = "USER_PATH_CHECK";



    public DatabaseHelper(@Nullable Context context) {
//        String name = "user_bookmark.db";
//        SQLiteDatabase.CursorFactory factory = null;
//        int version = 1;
        super(context, "user_bookmark.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_BOOKMARK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BUILDING + " TEXT, " + COLUMN_FLOOR + " TEXT, " + COLUMN_ROOM + " TEXT, " + COLUMN_NOTES + " TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addBookmark(BookmarkModel bookmarkModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BUILDING, bookmarkModel.getBuilding());
        cv.put(COLUMN_FLOOR, bookmarkModel.getFloor());
        cv.put(COLUMN_ROOM, bookmarkModel.getRoom());
        cv.put(COLUMN_NOTES, bookmarkModel.getNotes());

        long insert = db.insert(USER_BOOKMARK_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteBookmark(BookmarkModel bookmarkModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_BOOKMARK_TABLE + " WHERE " + COLUMN_ID + " = " + bookmarkModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteALL() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ USER_BOOKMARK_TABLE);
    }

    public ArrayList<BookmarkModel> getAll() {
         ArrayList<BookmarkModel> returnArrayList = new ArrayList<>();

         String queryString = "SELECT * FROM " + USER_BOOKMARK_TABLE;

         SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            // loop through the cursor and create new customer objects. Put them into return list.
            do {
                int bookmarkID = cursor.getInt(0);
                String bookmarkBuilding = cursor.getString(1);
                String bookmarkFloor = cursor.getString(2);
                String bookmarkRoom = cursor.getString(3);
                String bookmarkNotes = cursor.getString(4);

                BookmarkModel bookmarkModel = new BookmarkModel(bookmarkID, bookmarkBuilding, bookmarkFloor, bookmarkRoom, bookmarkNotes);
                returnArrayList.add(bookmarkModel);

            }while(cursor.moveToNext());

        }
        else {
            // fail to add. Do not do anything
        }
        cursor.close();
        db.close();
        return returnArrayList;
    }

}
