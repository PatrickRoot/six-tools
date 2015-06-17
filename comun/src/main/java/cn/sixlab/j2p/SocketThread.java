/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.j2p;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/28 21:28
 */
public class SocketThread extends Thread{

    private int pPort = 12345;
    private DatagramSocket inDs;
    private DatagramPacket inDp;
    private Callback<String> callBack;
    private boolean keepRun = true;

    public void close(){
        keepRun = false;
        inDs.close();
    }

    public void setCallBack(Callback<String> callBack) {
        this.callBack = callBack;
    }

    public void run() {
        try {
            byte[] buffer = new byte[1024];
            inDs = new DatagramSocket(pPort);
            inDp = new DatagramPacket(buffer,buffer.length);

            while (keepRun){
                if(inDs==null){
                    break;
                }else{
                    inDs.receive(inDp);
                    String message = new String(inDp.getData(),0,inDp.getData().length);
                    if( null!=message && !"".equals(message)){
                        callBack.call(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
