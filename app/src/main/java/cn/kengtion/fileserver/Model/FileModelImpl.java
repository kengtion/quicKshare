package cn.kengtion.fileserver.Model;

import android.content.Context;

import java.util.List;

import cn.kengtion.fileserver.AppContext;
import cn.kengtion.socketlib.Entity.FileInfo;
import cn.kengtion.socketlib.Tasks.GetFileListTask;
import cn.kengtion.socketlib.Tasks.TaskListener;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class FileModelImpl implements FileModel {

    private List<FileInfo> fileInfoList = null;
    //监听搜索文件任务的监听器实例
    private onFileListener fileListener;
    private Context context;

    public FileModelImpl(Context context , onFileListener fileListener){
        this.context = context;
        this.fileListener = fileListener;
    }

    @Override
    public void getFileList(int fileType) {
        new GetFileListTask(context,fileType,taskListener).executeOnExecutor(AppContext.MAIN_EXECUTOR);
    }

    @Override
    public void sendFile() {

    }

    @Override
    public void receiveFile() {

    }

    private TaskListener<String , List<FileInfo>> taskListener  = new TaskListener<String , List<FileInfo>>() {
        @Override
        public void onPreExecute() {

        }

        @Override
        public void onProgressUpdate(String progress) {

        }

        @Override
        public void onDoInBackGround(List<FileInfo> param) {
            FileModelImpl.this.fileInfoList = param;
        }


        @Override
        public void onPostExecute() {
            fileListener.success(fileInfoList);
        }
    };


    public interface onFileListener{
        void success(List<FileInfo> fileInfoList);
        void fail();
    }


}
