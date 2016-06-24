package smarthome.byids.com.easybyids.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import smarthome.byids.com.easybyids.TCPLongSocketCallback;
import smarthome.byids.com.easybyids.TcpLongSocket;

public class MyService extends Service {
	
	public static final String IP = "192.168.1.112";
	public static final int PORT = 9001;
	OnCreenBroadcastReceiver receiver;
	private TcpLongSocket tcpSocket;

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d("Service", "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("Service", "..myService..................onCreate");

		// 注册一个开机广播
		if (receiver == null) {
			receiver = new OnCreenBroadcastReceiver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
			intentFilter.addAction(Intent.ACTION_SCREEN_ON);
			this.registerReceiver(receiver, intentFilter);
		}
		//启动长连接
		startLongConnect();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("Service", "onDestroy");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("Service", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	public void startLongConnect() {
		if (tcpSocket != null)
			tcpSocket.close();
		tcpSocket = null;
		if (tcpSocket == null) {
			tcpSocket = new TcpLongSocket(new TCPLongSocketCallback() {
				@Override
				public void receive(byte[] buffer) {
					String result = new String(buffer);
					Log.i("====", "liangfan....receive:" + result);
					//将消息回复服务器
					tcpSocket.writeDate(result.getBytes());
				}

				@Override
				public void disconnect() {
					Log.e("====", "liangfan....tcp disconnect");
					if (tcpSocket != null)
						tcpSocket.close();
				}

				@Override
				public void connected() {
					Log.i("====", "liangfan....tcp connect");
				}
			});
			tcpSocket.startConnect(IP, PORT);
		} else {
			// 检查是否连接成功
			Log.i("====",
					"fanliang....tcpSocket not null ="
							+ tcpSocket.getConnectStatus());
			if (!tcpSocket.getConnectStatus()) {
				tcpSocket.close();
				tcpSocket = null;
			}
		}
	}

	class OnCreenBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
				//亮起屏幕之后，启动一次长连接
				System.out.println(".................................screen on");
			}
		}

	}
}
