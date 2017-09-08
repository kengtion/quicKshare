package cn.kengtion.socketlib.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.util.List;

import cn.kengtion.socketlib.Entity.FileInfo;
import cn.kengtion.socketlib.Utils.FileUtils;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class GetFileListTask extends AsyncTask<String,String,List<FileInfo>> {
    private Context context;
    private int fileType ;
    private TaskListener listener;
    private List<FileInfo> fileInfoList = null;

    public GetFileListTask(Context context , int fileType , TaskListener listener){
        this.context = context;
        this.fileType = fileType;
        this.listener = listener;
    }

    @Override
    protected List<FileInfo> doInBackground(String... params) {
        if(fileType == FileInfo.TYPE_APK){
            fileInfoList = FileUtils.getSpecificTypeFiles(context, new String[]{ FileInfo.EXTEND_APK});
            fileInfoList = FileUtils.getDetailFileInfos(context, fileInfoList, FileInfo.TYPE_APK);
        }else if(fileType == FileInfo.TYPE_JPG){
            fileInfoList = FileUtils.getSpecificTypeFiles(context, new String[]{ FileInfo.EXTEND_JPG, FileInfo.EXTEND_JPEG});
            fileInfoList = FileUtils.getDetailFileInfos(context, fileInfoList, FileInfo.TYPE_JPG);
        }else if(fileType == FileInfo.TYPE_MP3){
            fileInfoList = FileUtils.getSpecificTypeFiles(context, new String[]{ FileInfo.EXTEND_MP3});
            fileInfoList = FileUtils.getDetailFileInfos(context, fileInfoList, FileInfo.TYPE_MP3);
        }else if(fileType == FileInfo.TYPE_MP4){
            fileInfoList = FileUtils.getSpecificTypeFiles(context, new String[]{ FileInfo.EXTEND_MP4});
            fileInfoList = FileUtils.getDetailFileInfos(context, fileInfoList, FileInfo.TYPE_MP4);
        }
        listener.onDoInBackGround(fileInfoList);
        return fileInfoList;
    }

    @Override
    protected void onPreExecute(){
        listener.onPreExecute();
    }


    @Override
    protected void onPostExecute(List<FileInfo> list){
        listener.onPostExecute();
    }
}
