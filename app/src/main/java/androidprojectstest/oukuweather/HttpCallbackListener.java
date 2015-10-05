package androidprojectstest.oukuweather;

/**
 * Created by 18701 on 2015/10/5.
 */
public interface HttpCallbackListener {
    void onFinish(String s);

    void onError(Exception e);
}
