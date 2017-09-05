package cn.kengtion.socketlib.Server;

/**
 * 创建时间 2017/9/5
 * 创建人 洪坤峰
 * 功能描述：
 */

public interface ResponseHandler {
    boolean matches(String response);
    void Handler(Request  request);
    void Destroy();
}
