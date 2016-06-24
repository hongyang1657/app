package smarthome.byids.com.easybyids.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 123456 on 2016/1/5.
 */
public class SpUtils {
    private static final String SP_NAME = "SP";

    public static SharedPreferences getSp(Context context){
         SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
         return sp;
     }
    public static boolean readSp(Context context,String key,boolean defValue){
        return  getSp(context).getBoolean(key,defValue);

    }
    public static void writeSp(Context context,String key,boolean Value) {
        getSp(context).edit().putBoolean(key, Value).commit();
    }
}
