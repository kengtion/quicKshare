package cn.kengtion.socketlib.Tasks;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public interface TaskListener<PROGRESS , RESULT> {
    void onPreExecute();
    void onProgressUpdate(PROGRESS progress);
    void onDoInBackGround(RESULT param);
    void onPostExecute();
}
