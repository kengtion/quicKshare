package cn.kengtion.socketlib.Client;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建时间 2017/9/5
 * 创建人 洪坤峰
 * 功能描述：
 */

public class Client {
    private Socket socket;
    private ExecutorService threadPool;
    private boolean isEnable = true;
    private Handler handler ;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;

    public Client(Handler handler){
        this.handler = handler;
    }
    /*
        第一步先发送文件列表大小,在接收端展示
     */
    public void sendFileListInfo(byte[] bytes){

    }

    public void sendMsg(byte[] bytes){
            if(socket.isConnected()){
                try {
                    OutputStream os = socket.getOutputStream();
                    os.write(bytes);
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    socket = new Socket("192.168.43.1",5656);
                    if(socket.isConnected()){
                        OutputStream os = socket.getOutputStream();
                        os.write(bytes);
                        os.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void connect(){
        if(threadPool == null)
            threadPool = Executors.newCachedThreadPool();
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.43.1",5656);
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (isEnable){
                    try {
                        is = socket.getInputStream();
                        isr = new InputStreamReader(is);
                        br = new BufferedReader(isr);
                        String msg = br.readLine();
                        if(msg!=null && !msg.equals("")){
                            Message message = new Message();
                            message.what = 1;
                            message.obj = msg;
                            handler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    };

}
