package cn.kengtion.socketlib.Server;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.kengtion.socketlib.Entity.FileInfo;
import cn.kengtion.socketlib.Utils.LogUtils;

/**
 * 创建时间 2017/9/5
 * 创建人 洪坤峰
 * 功能描述：
 */

public class MiniServer {
    public static final String TAG = "MiniServer";

    /*
        服务端口
     */
    private int port;

    /*
        服务Socket
     */
    private ServerSocket serverSocket;

    /*
        是否正在运行
     */
    private boolean isEnable = true;

    /*
        线程池，设定最大并发数为5
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private Handler handler;
    private List<Socket> clients = new ArrayList<>();
    /**
     * 头部分割字符
     */
    public static final String SPERATOR = "::";

    private List<FileInfo> infoList = new ArrayList<>();

    public MiniServer(int port , Handler handler){
        this.port = port;
        this.handler = handler;
    }


    public void start(){
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    while (isEnable){
                        Socket socket = serverSocket.accept();
                        synchronized (MiniServer.class){
                            clients.add(socket);
                            LogUtils.d(TAG,"Linked success");
                        }
                        handlerSocket(socket);
                    }
                }catch (IOException e){}
            }
        });
    }

    /**
     * @method handlerSocket
       @auther 创建人：洪坤峰
       @auther 修改时间：2017/9/5
       @param  socket
       @description：处理请求
     */
    private void handlerSocket(final Socket socket){
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
                    InputStream is = socket.getInputStream();
                    OutputStream os = new ByteArrayOutputStream();
                    int readByte = -1;
                    int total = 0;
                    byte[] buffer = new byte[1];
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((readByte = is.read())!=-1){
                        stringBuilder.append(readByte);
                    }
                    os.close();
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void sendMsg(final String msg){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = clients.get(clients.size()-1);
                    OutputStream out = socket.getOutputStream();
                    out.write(msg.getBytes());
                    out.flush();
                    out.close();
                }catch (IOException e){}
            }
        });
    }
    public void stop(){
        if(isEnable)
            isEnable = false;

        if(serverSocket!=null){
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
