package smarthome.byids.com.easybyids;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import smarthome.byids.com.easybyids.View.WheelView;
import smarthome.byids.com.easybyids.utils.CommandJsonUtils;
import smarthome.byids.com.easybyids.utils.Encrypt;
import smarthome.byids.com.easybyids.utils.VibratorUtil;


public class MainActivity extends AppCompatActivity {
    private ImageView iv_air_condition;
    private ImageView iv_curtain;
    private ImageView iv_light;
    private ImageView iv_monitor;
    private ImageView iv_music;
    private ImageView iv_security;
    private ImageView iv_lock;
    private ImageView iv_panel;
    private Animation an;
    private PopupWindow popupWindow;
    private WheelView wv_rooms;
    private TextView tv_rooms;
    private Button btn_sure;
    private String str;
    private String roomName;

    private ArrayList<String> roomNames;
    private RelativeLayout rl_lock;
    private RelativeLayout rl_panel;
    private RelativeLayout rl_monitor;
    private RelativeLayout rl_air_condition;
    private RelativeLayout rl_curtain;
    private RelativeLayout rl_light;
    private RelativeLayout rl_music;
    private RelativeLayout rl_security;
    private JSONObject roomDatas;
    private String selectedroomName;
    private JSONArray rooms;
    private JSONObject jsonObject;
    //房间内属性
    private JSONObject floorheater;
    private String floorheater_active;
    private JSONObject panel;
    private String panel_active;
    private JSONObject ibeacon;
    private String ibeacon_active;
    private JSONObject camera_indoor;
    private String camera_indoor_active;
    private JSONObject aircondition;
    private JSONObject sence;
    private JSONObject light;
    private JSONObject lightbelt;
    private JSONObject curtain;
    private String aircondition_active;
    private String curtain_active;
    private String lightbelt_active;
    private String sence_active;
    private String light_active;
    //房子内属性
    private String securityalarm_active;
    private String lock_active;
    private String alarmclock_active;
    private String music_active;
    private String cinemaroom_active;
    private String outdoorwaterflow_active;
    private String hiddendoor_active;
    private String camera_active;
    private String ip;
     //Socket
     public static final int DEFAULT_PORT = 57816;
     private TcpLongSocket tcplongSocket;
    private String uname;
    private String pwd;

    private String light_protocol;
    private String roomDBName;
    private String aircondition_protocol;
    private String curtain_protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip=getIntent().getStringExtra("ip");
        if (ip == null) {
            Toast.makeText(MainActivity.this, "找不到主机", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "找到主机", Toast.LENGTH_LONG).show();
            tcplongSocket=new TcpLongSocket(new ConnectTcp());
            tcplongSocket.startConnect(ip,DEFAULT_PORT);
        }
        System.out.print("------->");
        System.out.println(ip);
        uname=getIntent().getStringExtra("uname");
        Log.i("logname",uname);
        pwd=getIntent().getStringExtra("pwd");
        Log.i("password",pwd);
        //CommandData


        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());

        Log.i("MAIN", "进入main");
        checkData();
        tv_rooms = (TextView) findViewById(R.id.tv_rooms);
        tv_rooms.setText(roomNames.get(0));
        try {
            resetData(checkRoomName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("显示popupwindow", "test");
                //showSelectWindow();
                initPopuptWindow();
            }

        });


        //空调哇卡卡卡卡
//        rl_air_condition = (RelativeLayout) findViewById(R.id.rl_air_condition);
//        iv_air_condition = (ImageView) findViewById(R.id.iv_air_condition);
//        rl_air_condition.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_air_condition.setImageResource(R.drawable.air_conditioning_on);
//                    an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                    an.setInterpolator(new LinearInterpolator());//不停顿
//                    an.setRepeatCount(-1);//重复次数
//                    an.setFillAfter(true);//停在最后
//                    an.setDuration(4000);
//                    iv_air_condition.startAnimation(an);
//                    flag = 1;
//                } else if (flag == 1) {
//                    iv_air_condition.clearAnimation();
//                    iv_air_condition.setImageResource(R.drawable.air_conditioning_off);
//                    flag = 0;
//                }
//            }
//        });
        //-------------------------------------------------------------------------
        //窗帘哇卡卡卡
//        rl_curtain = (RelativeLayout) findViewById(R.id.rl_curtain);
//        iv_curtain = (ImageView) findViewById(R.id.iv_curtain);
//        rl_curtain.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_curtain.setImageResource(R.drawable.curtain_on);
//                    flag = 1;
//                } else if (flag == 1) {
//                    iv_curtain.setImageResource(R.drawable.curtain_off);
//                    flag = 0;
//                }
//            }
//        });
        //----------------------------------------------------------------------------
        //灯光思密达
//        rl_light = (RelativeLayout) findViewById(R.id.rl_light);
//        iv_light = (ImageView) findViewById(R.id.iv_light);
//        rl_light.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_light.setImageResource(R.drawable.lights_on);
//                    flag = 1;
//                } else if (flag == 1) {
//                    iv_light.setImageResource(R.drawable.lights_off);
//                    flag = 0;
//                }
//            }
//        });
        //------------------------------------------------------------------------
        //监控咔哇卡
//        rl_monitor = (RelativeLayout) findViewById(R.id.rl_monitor);
//        iv_monitor = (ImageView) findViewById(R.id.iv_monitor);
//        rl_monitor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                EZOpenSDK.getInstance().openLoginPage();
//
//            }
//        });
        //----------------------------------------------------------------------------
        //音乐孔卡孔卡
//        rl_music = (RelativeLayout) findViewById(R.id.rl_music);
//        iv_music = (ImageView) findViewById(R.id.iv_music);
//        rl_music.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_music.setImageResource(R.drawable.music_on);
//                    flag = 1;
//                } else if (flag == 1) {
//                    iv_music.setImageResource(R.drawable.music_off);
//                    flag = 0;
//                }
//            }
//        });
        //------------------------------------------------------------------------------
        //安防猴赛雷
//        rl_security = (RelativeLayout) findViewById(R.id.rl_security);
//        iv_security = (ImageView) findViewById(R.id.iv_security);
//        rl_security.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_security.setImageResource(R.drawable.security_on);
//     nn               flag = 1;
//                } else if (flag == 1) {
//                    iv_security.setImageResource(R.drawable.security_off);
//                    flag = 0;
//                }
//            }
//        });
        //------------------------------------------------------------------------------------
        //门锁康撒米达
//        rl_lock = (RelativeLayout) findViewById(R.id.rl_lock);
//        iv_lock = (ImageView) findViewById(R.id.iv_lock);
//        rl_lock.setOnClickListener(new View.OnClickListener() {
//            int flag = 0;
//            @Override
//            public void onClick(View v) {
//                if (flag == 0) {
//                    iv_lock.setImageResource(R.drawable.door_lock_on);
//                    flag = 1;
//                } else if (flag == 1) {
//                    iv_lock.setImageResource(R.drawable.door_lock_off);
//                    flag = 0;
//                }
//            }
//        });
        //-------------------------------------------------------------------------------------
        //面板斯基
//        rl_panel = (RelativeLayout) findViewById(R.id.rl_panel);
//        iv_panel = (ImageView) findViewById(R.id.iv_panel);
//        rl_panel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class ConnectTcp implements TCPLongSocketCallback {


        @Override
        public void connected() {
            Log.i("MAIN", String.valueOf(tcplongSocket.getConnectStatus()));
            VibratorUtil.Vibrate(MainActivity.this,300);
            JSONObject checkCommandData=new JSONObject();
            try {
                checkCommandData.put("kong","keys");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String  checkJson=CommandJsonUtils.getCommandJson(1,checkCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
            Log.i("CHECK",checkJson);
            tcplongSocket.writeDate(Encrypt.encrypt(checkJson));

        }

        @Override
        public void receive(byte[] buffer) {
            Log.i("收到数据","--------");
            if("Hello client"==buffer.toString()){
                Log.i("心跳", String.valueOf(tcplongSocket.getConnectStatus()));
            }

        }

        @Override
        public void disconnect() {
            tcplongSocket.close();
        }
    }


    //显示房间选择框
    /*private void showSelectWindow() {
        Log.i("进入显示popupwindow","test");
        if (null != popupWindow) {
            popupWindow.dismiss();
        } else {
            initPopuptWindow();
        }
    }*/
    public void resetData(JSONObject jsonobject) throws JSONException {
        JSONObject roomAttr = jsonobject.getJSONObject("roomAttr");
        roomDBName=jsonobject.getString("roomDBName");
        Log.i("room", String.valueOf(roomAttr));
        Log.i("room", "-----------------------");
        Log.i("room", String.valueOf(roomDBName));
        //地热
        floorheater = roomAttr.getJSONObject("floorheater");
        floorheater_active = floorheater.getString("active");
        Log.i("floorheater_active", floorheater_active);

        //面板
        rl_panel = (RelativeLayout) findViewById(R.id.rl_panel);
        boolean panel_exists = true;
        JSONObject panel = null;
        try {
            panel = jsonObject.getJSONObject("panel");
        } catch (JSONException e) {
            panel_exists =false;
        }
        if (panel_exists){
            panel_active = panel.getString("active");
            Log.i("panel", panel.getString("active"));
            if ("1".equals(panel_active)) {
                rl_panel.setEnabled(true);
            } else {
                rl_panel.setEnabled(false);
            }}
        //ibeacon
        ibeacon = roomAttr.getJSONObject("ibeacon");
        ibeacon_active = ibeacon.getString("active");
        Log.i("ibeacon_active", ibeacon_active);
        //室内监控
        camera_indoor = roomAttr.getJSONObject("camera_indoor");
        camera_indoor_active = camera_indoor.getString("active");
        Log.i("camera_indoor_active", camera_indoor_active);

        //空调
        iv_air_condition = (ImageView) findViewById(R.id.iv_air_condition);
        rl_air_condition = (RelativeLayout) findViewById(R.id.rl_air_condition);
        boolean air_condition_exists = true;
        try {
            aircondition = roomAttr.getJSONObject("aircondition");
        } catch (JSONException e) {
            Log.i("aircondition_active", "catch");
            air_condition_exists =false;
        }
        if (air_condition_exists){
            aircondition_active = aircondition.getString("active");
            aircondition_protocol=aircondition.getString("protocol");
            Log.i("aircondition_active", aircondition_active);
            if ("1".equals(aircondition_active)) {
                rl_air_condition.setEnabled(true);
                rl_air_condition.setOnClickListener(new View.OnClickListener() {
                    int flag = 0;
                    @Override
                    public void onClick(View v) {
                        if (flag == 0) {
                            //空调Animation
                            iv_air_condition.setImageResource(R.drawable.air_conditioning_on);
                            an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            an.setInterpolator(new LinearInterpolator());//不停顿
                            an.setRepeatCount(-1);//重复次数
                            an.setFillAfter(true);//停在最后
                            an.setDuration(4000);
                            iv_air_condition.startAnimation(an);

                            //控制命令
                            JSONObject airConditionOnCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                airConditionOnCommandData.put("controlProtocol",aircondition_protocol);
                                airConditionOnCommandData.put("machineName","aircondition");
                                airConditionOnCommandData.put("controlData",controlData);
                                airConditionOnCommandData.put("controlSence","temp");
                                airConditionOnCommandData.put("houseDBName",roomDBName);
                                controlData.put("controType",50);
                                controlData.put("controlValue",1);
                                String  aircondiotionOnJson=CommandJsonUtils.getCommandJson(0,airConditionOnCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                tcplongSocket.writeDate(Encrypt.encrypt(aircondiotionOnJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            flag = 1;
                        } else if (flag == 1) {
                            iv_air_condition.clearAnimation();
                            iv_air_condition.setImageResource(R.drawable.air_conditioning_off);


                            //控制命令
                            JSONObject airConditionOffCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                airConditionOffCommandData.put("controlProtocol",aircondition_protocol);
                                airConditionOffCommandData.put("machineName","aircondition");
                                airConditionOffCommandData.put("controlData",controlData);
                                airConditionOffCommandData.put("controlSence","temp");
                                airConditionOffCommandData.put("houseDBName",roomDBName);
                                controlData.put("controType",50);
                                controlData.put("controlValue",0);
                                String  aircondiotionOffJson=CommandJsonUtils.getCommandJson(0,airConditionOffCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                tcplongSocket.writeDate(Encrypt.encrypt(aircondiotionOffJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            flag = 0;
                        }
                    }
                });
            } else {
                rl_air_condition.setEnabled(false);
            }}
        //窗帘
        iv_curtain = (ImageView) findViewById(R.id.iv_curtain);
        rl_curtain = (RelativeLayout) findViewById(R.id.rl_curtain);
        boolean curtain_exists = true;
        try {
            curtain = roomAttr.getJSONObject("curtain");
        } catch (JSONException e) {
            Log.i("curtain_active", "catch");
            curtain_exists =false;
        }
        if (curtain_exists){
            curtain_active = curtain.getString("active");
            curtain_protocol=curtain.getString("protocol");
            Log.i("curtain_active",curtain_active);
            if ("1".equals(curtain_active)) {
                rl_curtain.setEnabled(true);
                rl_curtain.setOnClickListener(new View.OnClickListener() {
                    int flag = 0;

                    @Override
                    public void onClick(View v) {
                        if (flag == 0) {
                            iv_curtain.setImageResource(R.drawable.curtain_on);
                            //控制命令
                            JSONObject curtainOnCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                curtainOnCommandData.put("controlProtocol",curtain_protocol);
                                curtainOnCommandData.put("machineName","curtain");
                                curtainOnCommandData.put("controlData",controlData);
                                curtainOnCommandData.put("controlSence","all_both_open");
                                curtainOnCommandData.put("houseDBName",roomDBName);
                                controlData.put("pauseStatus",-1);
                                String  curtainOnJson=CommandJsonUtils.getCommandJson(0,curtainOnCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                tcplongSocket.writeDate(Encrypt.encrypt(curtainOnJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            flag = 1;
                        } else if (flag == 1) {
                            iv_curtain.setImageResource(R.drawable.curtain_off);
                            //控制命令
                            JSONObject curtainOffCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                curtainOffCommandData.put("controlProtocol",curtain_protocol);
                                curtainOffCommandData.put("machineName","curtain");
                                curtainOffCommandData.put("controlData",controlData);
                                curtainOffCommandData.put("controlSence","all_both_close");
                                curtainOffCommandData.put("houseDBName",roomDBName);
                                controlData.put("pauseStatus",-1);
                                String  curtainOffJson=CommandJsonUtils.getCommandJson(0,curtainOffCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                tcplongSocket.writeDate(Encrypt.encrypt(curtainOffJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            flag = 0;
                        }
                    }
                });
            } else {
                rl_curtain.setEnabled(false);
            }}
        //灯带
        lightbelt = roomAttr.getJSONObject("lightbelt");
        lightbelt_active = lightbelt.getString("active");
        Log.i("lightbelt_active", lightbelt_active);
        //灯
        iv_light = (ImageView) findViewById(R.id.iv_light);
        rl_light = (RelativeLayout) findViewById(R.id.rl_light);
        boolean light_exists = true;
        try {
            light = roomAttr.getJSONObject("light");
        } catch (JSONException e) {
            light_exists =false;
        }
        if (light_exists){
            //active属性
            light_active = light.getString("active");
            Log.i("light", light.getString("active"));
            //protocol
            light_protocol=light.getString("protocol");
            if ("1".equals(light_active)) {
                rl_light.setEnabled(true);
                rl_light.setOnClickListener(new View.OnClickListener() {
                    int flag = 0;

                    @Override
                    public void onClick(View v) {
                        if (flag == 0) {
                            iv_light.setImageResource(R.drawable.lights_on);
                            JSONObject lightOnCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                lightOnCommandData.put("controlProtocol",light_protocol);
                                lightOnCommandData.put("machineName","light");
                                lightOnCommandData.put("controlData",controlData);
                                controlData.put("lightValue","100");
                                controlData.put("isServerAUTO","0");
                                lightOnCommandData.put("controlSence","all");
                                lightOnCommandData.put("houseDBName",roomDBName);
                                String  lightJson=CommandJsonUtils.getCommandJson(0,lightOnCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                Log.i("result", "onClick:------------ "+lightJson);
                                tcplongSocket.writeDate(Encrypt.encrypt(lightJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            flag = 1;
                        } else if (flag == 1) {
                            iv_light.setImageResource(R.drawable.lights_off);
                            JSONObject lightOffCommandData=new JSONObject();
                            JSONObject controlData=new JSONObject();
                            try {
                                lightOffCommandData.put("controlProtocol",light_protocol);
                                lightOffCommandData.put("machineName","light");
                                lightOffCommandData.put("controlData",controlData);
                                controlData.put("lightValue","0");
                                controlData.put("isServerAUTO","0");
                                lightOffCommandData.put("controlSence","all");
                                lightOffCommandData.put("houseDBName",roomDBName);
                                String  lightJson=CommandJsonUtils.getCommandJson(0,lightOffCommandData,"55f6797364c0ce976beb0110",uname,pwd, String.valueOf(System.currentTimeMillis()));
                                tcplongSocket.writeDate(Encrypt.encrypt(lightJson));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            flag = 0;
                        }
                    }
                });
            } else {
                rl_light.setEnabled(false);
            }}
        //场景
        sence = roomAttr.getJSONObject("sence");
        sence_active = sence.getString("active");
        Log.i("sence_active", sence_active);
    }

    public JSONObject checkRoomName() {
        try {
            jsonObject = new JSONObject(str);
            rooms = jsonObject.getJSONArray("rooms");
            Log.i("MAIN", String.valueOf(rooms));
            for (int i = 0; i < rooms.length(); i++) {
                roomDatas = (JSONObject) rooms.get(i);
                if (tv_rooms.getText().equals(roomDatas.getString("roomName"))) {
                    Log.i("ROOMDATA", String.valueOf(roomDatas));
                    return roomDatas;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkData() {
        roomNames = new ArrayList<String>();
        //接收数据
        str = getIntent().getStringExtra("homeAttr");
        Log.i("MAIN", str);

        try {
            jsonObject = new JSONObject(str);
            rooms = jsonObject.getJSONArray("rooms");
            Log.i("MAIN", String.valueOf(rooms));
            for (int i = 0; i < rooms.length(); i++) {
                roomDatas = (JSONObject) rooms.get(i);
                roomName = roomDatas.getString("roomName");
                Log.i("ROOMNAME", roomDatas.getString("roomName"));
                roomNames.add(roomName);
            }
            //1门锁
            iv_lock = (ImageView) findViewById(R.id.iv_lock);
            rl_lock = (RelativeLayout) findViewById(R.id.rl_lock);
            boolean rl_lock_exists = true;
            JSONObject lock = null;
            try {
                lock = jsonObject.getJSONObject("lock");
            } catch (JSONException e) {

                rl_lock_exists =false;
            }
            if (rl_lock_exists){
                lock_active = lock.getString("active");
                Log.i("lock", lock.getString("active"));
                if ("1".equals(lock_active)) {
                    rl_lock.setOnClickListener(new View.OnClickListener() {
                        int flag = 0;

                        @Override
                        public void onClick(View v) {
                            if (flag == 0) {
                                iv_lock.setImageResource(R.drawable.door_lock_on);
                                flag = 1;
                            } else if (flag == 1) {
                                iv_lock.setImageResource(R.drawable.door_lock_off);
                                flag = 0;
                            }
                        }
                    });
                } else {
                    rl_lock.setEnabled(false);
                }}


            //2影藏门
            JSONObject hiddendoor = jsonObject.getJSONObject("hiddendoor");
            hiddendoor_active = hiddendoor.getString("active");
            Log.i("active", hiddendoor.getString("active"));


            //3监控
            rl_monitor = (RelativeLayout) findViewById(R.id.rl_monitor);
            boolean monitor_exists = true;
            JSONObject monitor = null;
            try {
                monitor = jsonObject.getJSONObject("monitor");
            } catch (JSONException e) {
                monitor_exists =false;
            }
            if (monitor_exists){
                camera_active = monitor.getString("active");
                Log.i("monitor", monitor.getString("active"));
                if ("1".equals(camera_active)) {
                    rl_security.setEnabled(true);
                } else {
                    rl_security.setEnabled(false);
                }}


            //4安防
            iv_security = (ImageView) findViewById(R.id.iv_security);
            rl_security = (RelativeLayout) findViewById(R.id.rl_security);
            boolean securityalarm_exists = true;
            JSONObject securityalarm = null;
            try {
                 securityalarm = jsonObject.getJSONObject("securityalarm");
            } catch (JSONException e) {
                securityalarm_exists =false;
            }
            if (securityalarm_exists){
                securityalarm_active = securityalarm.getString("active");
            Log.i("security", securityalarm.getString("active"));
            if ("1".equals(securityalarm_active)) {
                rl_security.setOnClickListener(new View.OnClickListener() {
                    int flag = 0;

                    @Override
                    public void onClick(View v) {
                        if (flag == 0) {
                            iv_security.setImageResource(R.drawable.security_on);
                            flag = 1;
                        } else if (flag == 1) {
                            iv_security.setImageResource(R.drawable.security_off);
                            flag = 0;
                        }
                    }
                });
            } else {
                rl_security.setEnabled(false);
            }}


            //5闹钟
            JSONObject alarmclock = jsonObject.getJSONObject("alarmclock");
            alarmclock_active = alarmclock.getString("active");
            Log.i("alarmclock", alarmclock.getString("active"));


            //6喷淋
            JSONObject outdoorwaterflow = jsonObject.getJSONObject("outdoorwaterflow");
            outdoorwaterflow_active = outdoorwaterflow.getString("active");
            Log.i("outdoorwaterflow", outdoorwaterflow.getString("active"));


            //7影音室
            JSONObject cinemaroom = jsonObject.getJSONObject("cinemaroom");
            cinemaroom_active = cinemaroom.getString("active");
            Log.i("cinemaroom", cinemaroom.getString("active"));


            //8音乐
            iv_music = (ImageView) findViewById(R.id.iv_music);
            rl_music = (RelativeLayout) findViewById(R.id.rl_music);
            boolean music_exists = true;
            JSONObject music = null;
            try {
                music = jsonObject.getJSONObject("music");
            } catch (JSONException e) {
                music_exists =false;
            }
            if (music_exists){
                music_active = music.getString("active");
                Log.i("security", music.getString("active"));
                if ("1".equals(music_active)) {
                    rl_music.setOnClickListener(new View.OnClickListener() {
                        int flag = 0;

                        @Override
                        public void onClick(View v) {
                            if (flag == 0) {
                                iv_music.setImageResource(R.drawable.music_on);
                                flag = 1;
                            } else if (flag == 1) {
                                iv_music.setImageResource(R.drawable.music_off);
                                flag = 0;
                            }
                        }
                    });
                } else {
                    rl_music.setEnabled(false);
                }}
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initPopuptWindow() {
        Log.i("加载popupwindow", "test");
        final View popupwindow = View.inflate(this, R.layout.popupwindow, null);
        wv_rooms = (WheelView) popupwindow.findViewById(R.id.wheel);
        wv_rooms.setData(roomNames);
        wv_rooms.setDefault(0);
        wv_rooms.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtils.isEmpty(text)) {
                    return;
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        btn_sure = (Button) popupwindow.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedroomName = wv_rooms.getSelectedText();
                tv_rooms.setText(selectedroomName);
                try {
                    resetData(checkRoomName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupWindow.dismiss();

            }
        });
        //初始化一个popupWindows
        popupWindow = new PopupWindow(popupwindow, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(tv_rooms, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);


    }


    //写一个方法控制屏幕透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}
