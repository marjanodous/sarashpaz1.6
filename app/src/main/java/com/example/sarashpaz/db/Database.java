package com.example.sarashpaz.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sarashpaz.model.LikedFoods;

import java.io.IOException;
import java.util.ArrayList;


public class Database {

    private static DatabaseHelper databaseHelper;
    private static final String tbl_liked = "tbl_liked";
    private static final String Name = "title";
    public static boolean flagAdd,flagDel,flagSelect;

    public Database() {
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (databaseHelper == null) {
            try {
                databaseHelper = new DatabaseHelper(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return databaseHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getInstance2(Context context) {
        if (databaseHelper == null) {
            try {
                databaseHelper = new DatabaseHelper(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return databaseHelper.getReadableDatabase();
    }


    public static ArrayList getliked(Context context) {

        String selectQuery = "SELECT  * FROM " + tbl_liked;
        ArrayList<LikedFoods> list = new ArrayList<LikedFoods>();
        Cursor cursor = getInstance2(context).rawQuery(selectQuery, null, null);
        while (cursor.moveToNext()) {
            list.add(new LikedFoods(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("liked"))),
                    cursor.getString(cursor.getColumnIndex("img")),
                    cursor.getString(cursor.getColumnIndex("mavad")),
                    cursor.getString(cursor.getColumnIndex("tahaieh"))
            ));
        }
        cursor.close();
        getInstance2(context).close();
        return list;
    }

    public static void addLiked(String title, boolean like, String img
            , String mavad, String tahaieh, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("liked", like);
        contentValues.put("img", img);
        contentValues.put("mavad", mavad);
        contentValues.put("tahaieh", tahaieh);

        // Inserting Row
        Long value = getInstance(context).insert(tbl_liked, null, contentValues);
        if (String.valueOf(value).equals("-1")) {
            flagAdd = false;
            getInstance(context).close(); // Closing database connection
            Log.i(" Long value11111111111", String.valueOf(value));
            Log.i(" flag value22222222222", String.valueOf(flagAdd));
        } else if (!(String.valueOf(value).equals("-1"))) {
            Log.i(" Long value33333333333", String.valueOf(value));
            flagAdd = true;
            Log.i(" flag value44444444444", String.valueOf(flagAdd));
        }
        getInstance(context).close(); // Closing database connection
    }


    //    /////////////deleted data
    public static void delLiked(Context context, String name) {
        int valdel = getInstance(context).delete(tbl_liked,
                Name + " = ?", new String[]{"" + name});
        Log.i("namenamenamenamename",String.valueOf(valdel));
        if (!String.valueOf(valdel).equals("1")) {
            flagDel = false;
            getInstance(context).close(); // Closing database connection
            Log.i(" Int valdel", String.valueOf(valdel));
            Log.i(" flag flagMaliDel", String.valueOf(flagDel));
        } else if ((String.valueOf(valdel).equals("1"))) {
            flagDel = true;
            Log.i(" int valdel", String.valueOf(valdel));
            Log.i(" flag flagMaliDel", String.valueOf(flagDel));
        }
        getInstance(context).close();
    }
    public static LikedFoods selectliked(Context context,String title) {
        String selectQuery = "SELECT  * FROM " + tbl_liked  + " WHERE " + Name +" LIKE '%" + title + "%' ";
        Cursor cursor = getInstance2(context).rawQuery(selectQuery, null, null);
        LikedFoods foods = new LikedFoods();
        while (cursor.moveToNext()) {
            foods = (new LikedFoods(cursor.getString(cursor.getColumnIndex("title"))));
        }
        cursor.close();
        getInstance2(context).close();
        return foods;
    }
}