package smarthome.byids.com.easybyids;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZAccessToken;

/**
 * Created by 123456 on 2016/5/12.
 */
public class EzvizBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(),"com.videogo.action.OAUTH_SUCCESS_ACTION"))
        {
            EZAccessToken accessToken= EZOpenSDK.getInstance().getEZAccessToken();

        }
    }
}
