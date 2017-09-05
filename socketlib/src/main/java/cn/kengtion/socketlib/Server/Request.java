package cn.kengtion.socketlib.Server;

import java.net.Socket;
import java.util.HashMap;

/**
 * 创建时间 2017/9/5
 * 创建人 洪坤峰
 * 功能描述：
 */

public class Request {
    private String uri;
    private HashMap<String , String> headerMap = new HashMap<>();
    private Socket underlySocket;

    public void addHeader(String key , String value){
        this.headerMap.put(key,value);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Socket getUnderlySocket() {
        return underlySocket;
    }

    public void setUnderlySocket(Socket underlySocket) {
        this.underlySocket = underlySocket;
    }
}
