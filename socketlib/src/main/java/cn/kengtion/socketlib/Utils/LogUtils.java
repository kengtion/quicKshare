package cn.kengtion.socketlib.Utils;

import android.util.Log;

import cn.kengtion.socketlib.Common.Constant;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class LogUtils {

    public static void e(String tag , String info){
        if(Constant.DEBUG)
            Log.e(tag,info);
    }

    public static void v(String tag , String info){
        if(Constant.DEBUG)
            Log.v(tag,info);
    }
    public static void i(String tag , String info){
        if(Constant.DEBUG)
            Log.i(tag,info);
    }
    public static void d(String tag , String info){
        if(Constant.DEBUG)
            Log.d(tag,info);
    }
}
