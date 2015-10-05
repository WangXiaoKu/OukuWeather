package androidprojectstest.oukuweather;

/**
 * Created by 18701 on 2015/10/5.
 */
public class Prefecture {
    private int id;
    private String prefectureName;
    private String prefectureCode;
    private int cityId;

    public int getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
    }

    public String getPrefectureCode() {
        return prefectureCode;
    }

    public String getPrefectureName() {
        return prefectureName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setPrefectureCode(String prefectureCode) {
        this.prefectureCode = prefectureCode;
    }

    public void setPrefectureName(String prefectureName) {
        this.prefectureName = prefectureName;
    }
}
