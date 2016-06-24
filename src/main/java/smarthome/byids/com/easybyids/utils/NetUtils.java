package smarthome.byids.com.easybyids.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 123456 on 2016/1/4.
 */
public class NetUtils {
    //判断网络连接
    public static boolean judgeNetConnected(Context context){
        //获取网络连接管理者
       ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络类型
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if (networkInfo==null){
            return false;
        }
        int type=networkInfo.getType();
        switch (type){
                case  ConnectivityManager.TYPE_MOBILE :
                    case  ConnectivityManager.TYPE_WIFI:
                        return true;
                    default:
                        return false;
        }
    }
}
