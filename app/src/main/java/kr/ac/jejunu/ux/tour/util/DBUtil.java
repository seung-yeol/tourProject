package kr.ac.jejunu.ux.tour.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Osy on 2017-10-09.
 */

public class DBUtil extends SQLiteOpenHelper {
    private static DBUtil INSTANCE;
    public static final String TABLE_DIARY = "TABLE_DIARY";
    public static final String TABLE_TENDENCY = "TABLE_TENDENCY";

    public static DBUtil getInstance( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if (INSTANCE == null){
            INSTANCE = new DBUtil(context, name, factory, version);
        }
        return INSTANCE;
    }
    public static DBUtil getInstance( ) {
        if (INSTANCE == null){
            throw new NullPointerException();
        }
        return INSTANCE;
    }

    private DBUtil( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate( SQLiteDatabase db) {
        //다이어리 내용 넣을 테이블
        db.execSQL("CREATE TABLE " + TABLE_DIARY + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT NOT NULL, " +
                "STORY TEXT, " +
                "PATH TEXT NOT NULL);");
        //성향 내용 넣을 테이블
        db.execSQL("CREATE TABLE " + TABLE_TENDENCY + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade( db, oldVersion, newVersion);
    }

    public void insertData(String tableName, ArrayList<String> strings){

        String values = "";
        for (String s : strings){
            values += ", '"+ s +"'";
        }

        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO "+ tableName +" VALUES(NULL" + values + ");";
        db.execSQL(sql);
    }

    //리스트 삭제시
    public void deleteData( String tableName, String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM " + tableName + " WHERE NAME='" + name + "';");
    }

    public ArrayList getAllData( String tableName){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from '"+ tableName + "';", null);
        cursor.moveToNext();

        ArrayList<ArrayList> allData = new ArrayList<>( cursor.getCount());
        ArrayList<String> data;

        for (int k = 0 ; k < cursor.getCount(); k++){
            data  = new ArrayList<>( cursor.getColumnCount());
            for ( int i = 1 ; i < cursor.getColumnCount() ; i++){
                data.add( cursor.getString(i));
            }
            allData.add( data);
            cursor.moveToNext();
        }

        cursor.close();

        return allData;
    }

    public ArrayList getData( String tableName, String name){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from '"+ tableName + "' where NAME ='" + name + "';", null);
        cursor.moveToNext();

        ArrayList<String> arrayList = new ArrayList<>();

        for ( int i = 1 ; i < cursor.getColumnCount() ; i++){
            arrayList.add( cursor.getString(i));

            Log.e(this.toString(), "getData: " + cursor.getString(i) );
        }
        cursor.close();

        return arrayList;
    }
}
