package smarthome.byids.com.easybyids;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationListener;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import smarthome.byids.com.easybyids.bean.Rooms;
import smarthome.byids.com.easybyids.bean.Root;
import smarthome.byids.com.easybyids.utils.AES;
import smarthome.byids.com.easybyids.utils.ByteUtils;

/**
 * Created by 123456 on 2016/1/5.
 */
public class  LoginActivity extends Activity implements SurfaceHolder.Callback{
    private ArrayList<View> lists = new ArrayList<>();
    private ViewPager viewPager;
    private SurfaceView surfaceView;
    private EditText username;
    private EditText password;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer player;
    private RequestQueue requestQueue;
    private String uname;
    private String pwd;
    private View v2;
    private View v;
    public ImageView iv_login;
    private ArrayList<Rooms> oom;
    private Rooms ms;
    //----------udp-------------------
    public static final int DEFAULT_PORT = 57816;
    public static final String LOG_TAG = "WifiBroadcastActivity";
    private byte[] buffer = new byte[MAX_DATA_PACKET_LENGTH];
    private static final int MAX_DATA_PACKET_LENGTH = 100;
    private JSONObject homeAttr;
    private JSONObject json;
    private String ip;
    private TcpLongSocket tcpLongSocket;
    private String udpJson;
    private JSONArray jsonArray;
    private String udpCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //请求队列
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        viewPager= (ViewPager) findViewById(R.id.vp_login);
        surfaceView= (SurfaceView) findViewById(R.id.sv);
        oom = new ArrayList<Rooms>();
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        //填充第一个页面 由于需要找到填充页面的控件  故必须定义为全局变量
        v=getLayoutInflater().inflate(R.layout.activity_login_01, null);
        lists.add(v);
        //填充第二个页面  同上
        v2= getLayoutInflater().inflate(R.layout.activity_login_02, null);
        lists.add(v2);
        //找到第二填充页面的控件
        iv_login = (ImageView) v2.findViewById(R.id.iv_login);
        username=(EditText)v2.findViewById(R.id.et_username);
        password=(EditText)v2.findViewById(R.id.et_password);
        viewPager.setAdapter(viewPagerAdapter);
//        iv_login.setOnClickListener(ocll);
       /* viewPager.setOnPageChangeListener(opcl);*/
    }
    PagerAdapter viewPagerAdapter=new PagerAdapter() {
        @Override
        public int getCount() {
            return lists.size();
        }


        @Override
        public boolean isViewFromObject( View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(lists.get(position));
            return lists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    public void goon(View view){
        CPUUtils();
        test();
        viewPager.setCurrentItem(1);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        // 必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
        player = MediaPlayer.create(this, R.raw.loginmovie);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDisplay(surfaceHolder);
        player.setLooping(true);
        // 设置显示视频显示在SurfaceView上
        try {
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
    }

    /*View.OnClickListener ocll=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("test", "进入方法");

            uname = username.getText().toString().trim();
            Log.i("test", uname);
            pwd = password.getText().toString().trim();
            Log.i("test", pwd);

            Toast.makeText(LoginActivity.this, uname+","+pwd, Toast.LENGTH_SHORT).show();
            if (TextUtils.isEmpty(uname)||TextUtils.isEmpty(pwd)) {
                Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }else {
                initData();
            }

            Intent intent=new Intent(LoginActivity.this,DemoActivity.class);
            startActivity(intent);
            finish();
        }
    };*/

    //判断修改cpu字节储存机制---------------
   public void CPUUtils(){
       ByteOrder CPUOrder;
       CPUOrder= ByteOrder.nativeOrder();
       Log.i("test", CPUOrder.toString());
       if(CPUOrder== ByteOrder.BIG_ENDIAN){

       }else{

       }

   }

    //接收UDP------------------------
    class RecieveThread extends Thread {
        private DatagramSocket rsSocket;
        public RecieveThread(DatagramSocket socket){
            this.rsSocket =socket;
        }
        public void run(){
            byte[] buffer = new byte[66507];
            while(true){
                DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
                try {
                    rsSocket.receive(dgp);
                    String msg = new String(dgp.getData(), 0, dgp.getLength());
                    System.out.println(msg);
                    Thread.yield();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    //发送UDP--------------------------------
    public class BroadCastUdp extends Thread {
        DatagramPacket dataPacket = null;
        DatagramPacket receiveData= null;
        private byte[] dataByte;
        private DatagramSocket udpSocket;

        public BroadCastUdp(byte[] sendByte) {
            this.dataByte = sendByte;
        }
        public void run() {
            try {
                udpSocket = new DatagramSocket(DEFAULT_PORT);
                dataPacket = new DatagramPacket(buffer, MAX_DATA_PACKET_LENGTH);
                receiveData= new DatagramPacket(buffer,MAX_DATA_PACKET_LENGTH);
                if (this.dataByte == null){
                    return;
                }
                byte[] data = this.dataByte;
                dataPacket.setData(data);
                dataPacket.setLength(data.length);
                dataPacket.setPort(DEFAULT_PORT);

                InetAddress broadcastAddr;
                broadcastAddr = InetAddress.getByName("255.255.255.255");
                dataPacket.setAddress(broadcastAddr);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
                udpSocket.close();
            }
            // while( start ){
            try {
                udpSocket.send(dataPacket);
                sleep(10);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
                udpSocket.close();
            }
            try {
                udpSocket.receive(receiveData);
                udpSocket.receive(receiveData);
            } catch (Exception e) {
// TODO Auto-generated catch block
                Log.e(LOG_TAG, e.toString());
                udpSocket.close();
            }
            if (null!=receiveData){

                if( 0!=receiveData.getLength() ) {
                String codeString = new String( buffer, 0, receiveData.getLength() );

                System.out.println( "接收到数据为："+codeString );
                    System.out.println( "接收到数据为："+codeString.substring(2,4) );
                    udpCheck=codeString.substring(2,4);
                    System.out.println( "接收到数据为："+udpCheck );
                System.out.print("recivedataIP地址为："+receiveData.getAddress().toString().substring(1)); //此为IP地址
                System.out.print("recivedata_sock地址为："+receiveData.getAddress()); //此为IP加端口号


//                Intent intent=new Intent();
//                intent.putExtra("receiveData", receiveData.getAddress().toString().substring(1));
                System.out.println( "receiveData："+receiveData.getAddress().toString().substring(1) );
                ip=receiveData.getAddress().toString().substring(1);

//                tcpLongSocket=new TcpLongSocket(new receiveUdpSendTcp());
//                tcpLongSocket.startConnect(receiveData.getAddress().toString().substring(1),DEFAULT_PORT);

                }
            }else{
                try {
                    udpSocket.send(dataPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            udpSocket.close();
        }

    }

       //包装数据报DATA
    public byte[] handleData(Object object){

        return new byte[0];
    }
    //测试  包装发送udp---------------------------
    public void test(){

       udpJson="{\"command\":\"find\",\"data\":{\"hid\":\"55f6797364c0ce976beb0110\",\"logname\":\"byids\"}}";

        byte[] enByte = AES.encrpt(udpJson);
        if (enByte == null)
            return;
        byte[] lengthByte = ByteUtils.intToByteBigEndian(enByte.length);
        byte[] headByte = new byte[8];
        for (int i = 0;i<headByte.length;i++) {
            headByte[i] = 0x50;
        }
        byte[] tailByte = new byte[4];
        tailByte[0] = 0x0d;
        tailByte[1] = 0x0a;
        tailByte[2] = 0x0d;
        tailByte[3] = 0x0a;

        byte[] sendByte = ByteUtils.byteJoin(headByte,lengthByte,enByte,tailByte);
        AES.byteStringLog(sendByte);
        //发送udp广播
        new BroadCastUdp(sendByte).start();
    }

    //点击登录
    public void iv_login(View v) {
        Log.i("test", "进入登录方法");
        uname = username.getText().toString().trim();
        Log.i("test", uname);
        pwd = password.getText().toString().trim();
        Log.i("test", pwd);

        Toast.makeText(this, uname + "," + pwd, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(uname)|| TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else {
            initData();
        }
        //页面跳转

    }


    //post 账号密码
    public void initData(){
        String url="http://115.29.97.189:2737/api/login";
        Map<String,String> map=new HashMap<String, String>();
        map.put("uname", uname);
        map.put("pwd", pwd);
        JSONObject jsonObject=new JSONObject(map);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("request","initData:-------- "+ jsonObject.toString());
                try {
                    jsonObject.toJSONArray(jsonArray);
                    Log.i("request", "initData:----- "+String.valueOf(jsonObject.toJSONArray(jsonArray)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(LoginActivity.this, jsonObject.toString(), Toast.LENGTH_LONG).show();
                //解析服务器响应返回的数据;
                Root json= new Gson().fromJson(jsonObject.toString(),Root.class);
                //数据解析;
                parseJSON(jsonObject);
                Log.i("网络", homeAttr.toString());
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                //Bundle bundle=new Bundle();
                //bundle.putSerializable("oom", );
                if (homeAttr!=null){
                    intent.putExtra("homeAttr",homeAttr.toString());
                    if (udpCheck.equals("ip")) {
                        intent.putExtra("ip",ip);
                    }
                    intent.putExtra("uname",uname);
                    intent.putExtra("pwd",pwd);
                    Log.i("网络传值", homeAttr.toString());
                }
                startActivity(intent);

            }

        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("网络", volleyError.getMessage(), volleyError);
                Toast.makeText(LoginActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void parseJSON(JSONObject json) {
        try {
            //房子包含的
            JSONObject uid= json.getJSONObject("55f6797364c0ce976beb0110");
            JSONObject profile=uid.getJSONObject("profile");
            homeAttr= profile.getJSONObject("homeAttr");
            JSONArray rooms=homeAttr.getJSONArray("rooms");
            Log.i("active", homeAttr.toString());
            //门锁
            JSONObject lock=homeAttr.getJSONObject("lock");
            lock.getString("active");
            Log.i("active", lock.getString("active"));
            //影藏门
            JSONObject hiddendoor=homeAttr.getJSONObject("hiddendoor");
            hiddendoor.getString("active");
            Log.i("active", hiddendoor.getString("active"));
            //监控
            JSONObject camera=homeAttr.getJSONObject("camera");
            camera.getString("active");
            Log.i("active", camera.getString("active"));
            //安防
            JSONObject securityalarm=homeAttr.getJSONObject("securityalarm");
            securityalarm.getString("active");
            Log.i("active", securityalarm.getString("active"));
            //闹钟
            JSONObject alarmclock=homeAttr.getJSONObject("alarmclock");
            alarmclock.getString("active");
            Log.i("active", alarmclock.getString("active"));
            //喷淋
            JSONObject outdoorwaterflow=homeAttr.getJSONObject("outdoorwaterflow");
            outdoorwaterflow.getString("active");
            Log.i("active", outdoorwaterflow.getString("active"));
            //影音室
            JSONObject cinemaroom=homeAttr.getJSONObject("cinemaroom");
            cinemaroom.getString("active");
            Log.i("active", cinemaroom.getString("active"));
            //音乐
            JSONObject music=homeAttr.getJSONObject("music");
            music.getString("active");
            Log.i("active", music.getString("active"));

//            房间内包含的
//            Toast.makeText(LoginActivity.this, rooms.toString(), Toast.LENGTH_LONG).show();
            for (int i=0;i<rooms.length();i++){
                JSONObject room= rooms.getJSONObject(i);
                ms = new Rooms();
                ms.setRoomAttr(room.optString("roomAttr"));
                ms.setRoomDBName(room.optString("roomDBName"));
                ms.setRoomName(room.optString("roomName"));
                oom.add(ms);
                Log.i("房间名", ms.getRoomName());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}





