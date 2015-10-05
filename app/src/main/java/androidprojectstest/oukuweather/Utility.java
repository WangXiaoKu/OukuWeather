package androidprojectstest.oukuweather;

import android.text.TextUtils;

/**
 * Created by 18701 on 2015/10/5.
 */
public class Utility {
    //解析和处理服务器返回的省级数据
    public synchronized static boolean handleProvincesResponse(OukuWeatherDB oukuWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    oukuWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理返回的市级数据
    public synchronized static boolean handleCityResponse(OukuWeatherDB oukuWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    oukuWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理服务器返回的县级数据
    public static boolean handlePrefecturesResponse(OukuWeatherDB oukuWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allPrefectures = response.split(",");
            if (allPrefectures != null && allPrefectures.length > 0) {
                for (String c : allPrefectures) {
                    String[] array = c.split("\\|");
                    Prefecture prefecture = new Prefecture();
                    prefecture.setPrefectureCode(array[0]);
                    prefecture.setPrefectureName(array[1]);
                    prefecture.setCityId(cityId);
                    //将解析出来的数据存储到Prefecture表
                    oukuWeatherDB.savePrefecture(prefecture);
                }
                return true;
            }
        }
        return false;
    }
}
