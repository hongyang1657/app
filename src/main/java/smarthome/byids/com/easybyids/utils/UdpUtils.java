package smarthome.byids.com.easybyids.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Created by 123456 on 2016/4/5.
 */
public class UdpUtils {
    /*发送广播端的socket*/
    private static MulticastSocket ms;

    public static void SendUDPBrocast(){
        try {
            ms = new MulticastSocket();
            //发送的数据包，局网内的所有地址都可以收到该数据包
            DatagramPacket dataPacket = null;
            ms.setTimeToLive(4);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
