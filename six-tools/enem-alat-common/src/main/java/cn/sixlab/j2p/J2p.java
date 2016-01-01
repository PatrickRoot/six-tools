/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.j2p;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 1. 依赖
 * 2. setCallBack
 * 3. start
 * 4. sendData
 * 5. close
 *
 * @author 六楼的雨/loki
 * @date 2015/5/28 21:08
 */
public class J2p {

    private static int jPort = 54321;
    private static DatagramSocket sendDs;
    private static DatagramPacket sendDp;
    private static String sendIp = "127.0.0.1";
    private static SocketThread thread = null;

    public static void setCallBack(Callback callBack){
        if(null==thread){
            thread = new SocketThread();
        }
        thread.setCallBack(callBack);
    }

    public static int sendData(String data) {
        try {
            byte[] buffer = data.getBytes();
            InetAddress address = InetAddress.getByName(sendIp);
            sendDp = new DatagramPacket(buffer, buffer.length, address, jPort);
            sendDs = new DatagramSocket();
            sendDs.send(sendDp);
            sendDs.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void start() {
        thread.start();
    }

    public static void close(){
        thread.close();
    }
}
