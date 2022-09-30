package com.example.t3s2021;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private Context mContext = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Config.DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a table
        String CREATE_COURSE_TABLE = "CREATE TABLE "+Config.TABLE_COURSE+" ("
                +Config.COLUMN_COURSE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Config.COLUMN_COURSE_TITLE+" TEXT NOT NULL,"
                +Config.COLUMN_COURSE_CODE+" TEXT NOT NULL"+")";

        db.execSQL(CREATE_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table
        db.execSQL("DROP TABLE IF EXISTS "+Config.TABLE_COURSE);
        onCreate(db);
    }

    public long insertCourse(Course course){
        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());
        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());

        try {
            id = db.insertOrThrow(Config.TABLE_COURSE, null, contentValues);
        } catch (SQLiteException e) {
            Toast.makeText(mContext, "Operation failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return id;
    }

    public List<Course> getAllCourses(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        List<Course> courseList = new ArrayList<>();

        try {
            cursor = db.query(Config.TABLE_COURSE, null, null, null, null, null, null);

            if (cursor != null){
                if(cursor.moveToFirst()){
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                        courseList.add(new Course(id,title,code));
                    } while(cursor.moveToNext());

                    return courseList;
                }
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Operation failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor != null)
                cursor.close();
            db.close();
        }
        return Collections.emptyList();
    }
}
