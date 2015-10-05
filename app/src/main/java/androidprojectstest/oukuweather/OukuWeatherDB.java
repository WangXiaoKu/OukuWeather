package androidprojectstest.oukuweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18701 on 2015/10/5.
 */
public class OukuWeatherDB {
    //数据库名
    public static final String DB_NAME = "ouku_weather";
    //数据库版本
    public static final int VERSION = 2;
    private static OukuWeatherDB oukuWeatherDB;
    private SQLiteDatabase db;

    //将构造方法私有化
    private OukuWeatherDB(Context context) {
        OukuWeatherOpenHelper dbHelper = new OukuWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //获取OukuWeatherDB的实例
    public synchronized static OukuWeatherDB getInstance(Context context) {
        if (oukuWeatherDB == null) {
            oukuWeatherDB = new OukuWeatherDB(context);
        }
        return oukuWeatherDB;
    }

    //将Province的实例存储到数据库中
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }
    //从数据库读取全国所有省份的信息
    public List<Province> loadProvinces(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }
    //将City实例存储到数据库
    public void saveCity(City city){
        if (city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    //从数据库读取某省下所有的城市信息
    public List<City> loadCities(int provinceId){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }
    //将Prefecture实例存储到数据库
    public void savePrefecture(Prefecture prefecture){
        if (prefecture!=null){
            ContentValues values=new ContentValues();
            values.put("city_id",prefecture.getCityId());
            values.put("prefecture_name",prefecture.getPrefectureName());
            values.put("prefecture_code",prefecture.getPrefectureCode());
            db.insert("Prefecture",null,values);
        }
    }
    //从数据库读取某城市下所有的县信息
    public List<Prefecture> loadPrefectures(int cityId){
        List<Prefecture> list=new ArrayList<Prefecture>();
        Cursor cursor=db.query("Prefecture",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                Prefecture prefecture=new Prefecture();
                prefecture.setId(cursor.getInt(cursor.getColumnIndex("id")));
                prefecture.setPrefectureCode(cursor.getString(cursor.getColumnIndex("prefecture_code")));
                prefecture.setPrefectureName(cursor.getString(cursor.getColumnIndex("prefecture_name")));
                prefecture.setCityId(cityId);
                list.add(prefecture);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
