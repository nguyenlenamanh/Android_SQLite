package com.example.student.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(Context context) {
        super(context, "db.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase = getWritableDatabase();
        String sql = "CREATE TABLE SV (Id int, Name nvarchar(50))";
        sqLiteDatabase.execSQL(sql);
    }

    public void InsertSV(SinhVien sv) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",sv.getId());
        contentValues.put("Name",sv.getName());
        sqLiteDatabase.insert("sv",null,contentValues);
    }

    public void UpdateSV(SinhVien sv) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",sv.getId());
        contentValues.put("Name",sv.getName());
        sqLiteDatabase.update("sv",contentValues,"Id=" + sv.getId(),null);
    }

    public void UpdateSV(SinhVien sv, int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",sv.getId());
        contentValues.put("Name",sv.getName());
        sqLiteDatabase.update("sv",contentValues,"Id=" + id,null);
    }

    public void DeleteSV(SinhVien sv) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("sv","Id=" + sv.getId(),null);
    }

    public Cursor getTable(String tableName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName,null);
        return cursor;
    }

    public ArrayList<SinhVien> sinhViens(Cursor cursor) {
        ArrayList<SinhVien> svs = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex("Id"));
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                SinhVien sv = new SinhVien(id,name);
                svs.add(sv);
                cursor.moveToNext();
            }
        }

        return svs;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
