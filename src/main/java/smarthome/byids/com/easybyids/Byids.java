package smarthome.byids.com.easybyids;

import android.app.Application;

import com.videogo.constant.Config;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EzvizAPI;

/**
 * Created by 123456 on 2016/5/10.
 */
public class Byids extends Application {
    private static String APP_KEY="707a7c9dd4e64dbf9649f0cdd532aaaf";
    public static String  API_URL = "https://open.ys7.com";
    public static String  WEB_URL = "https://auth.ys7.com";
    @Override
    public void onCreate() {
        super.onCreate();
        Config.LOGGING=true;
        //初始化摄像头SDK
       //  EZOpenSDK.initLib(this, APP_KEY, "");
       //  EzvizAPI.getInstance().setServerUrl(API_URL, WEB_URL);
       //  EZOpenSDK.getInstance().setAccessToken("at.dmtlxyp47nejsckiai1pdwzsdvxmo7jp-8ofxo9vacz-1s48ov1-p3r36v0vj");
     //Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }
}
