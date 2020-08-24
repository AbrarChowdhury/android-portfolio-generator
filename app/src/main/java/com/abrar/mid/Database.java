package com.abrar.mid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "portfoDB";
    public static final String TABLE_NAME = "userDetails";
    public static final int DB_VERSION = 3;
    public Database(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("debug","db dropped");
        try{
            //NoobNote - This line all you should really care about TBH
            sqLiteDatabase.execSQL(
                    "create table "+TABLE_NAME+ " (_id integer primary key autoincrement , name text, phone text, email text, skills text)"
            );
        } catch (SQLiteException e){
            try{
                throw new IOException(e);
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //NoobNote- Everytime database you feedle with the DB this line just Drops the table and creates a new One from scratch
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insert(String name, String phone, String email, String skills){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("skills", skills);
        sqLiteDatabase.replace(TABLE_NAME, null, contentValues);
        return true;
    }
    public String getThatOneData(String column){
        String data;
        SQLiteDatabase db =this.getReadableDatabase();
        String selectQuery = "SELECT "+column+" from "+TABLE_NAME+ " ORDER BY _id DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data=cursor.getString(cursor.getColumnIndex(column));
        return data;
    }
    public boolean update(String column, String columnValue, int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+column+" = "+"'"+columnValue+"' "+"WHERE _id = "+id);
        return true;
    }
}
