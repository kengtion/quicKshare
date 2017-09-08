package cn.kengtion.fileserver;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class AppContext extends Application{
    /**
     * 主要的线程池
     */
    public static Executor MAIN_EXECUTOR = Executors.newFixedThreadPool(5);
    /**
     * 文件发送单线程
     */
    public static Executor FILE_SENDER_EXECUTOR = Executors.newSingleThreadExecutor();
    /**
     * 全局应用的上下文
     */
    static AppContext mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppContext = this;
    }
}
