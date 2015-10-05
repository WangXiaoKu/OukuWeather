package androidprojectstest.oukuweather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 18701 on 2015/10/5.
 */
public class OukuWeatherOpenHelper extends SQLiteOpenHelper {
    //Province表建表语句
    public static final String CREATE_PROVINCE = "create table Province(id integer primary key autoincrement , province_name text , province_code text )";
    //City表建表语句
    public static final String CREATE_CITY = "create table City(id integer primary key autoincrement , city_name text , city_code text , province_id integer )";
    //Prefecture表建表语句
    public static final String CREATE_PREFECTURE = "create table Prefecture(id integer primary key autoincrement , prefecture_name text , prefecture_code text , city_id integer )";

    public OukuWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);//创建Province表
        db.execSQL(CREATE_CITY);//创建City表
        db.execSQL(CREATE_PREFECTURE);//创建Prefecture表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
