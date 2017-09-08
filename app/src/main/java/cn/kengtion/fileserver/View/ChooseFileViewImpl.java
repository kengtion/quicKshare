package cn.kengtion.fileserver.View;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import cn.kengtion.fileserver.Adapter.FileListAdapter;
import cn.kengtion.fileserver.Presenter.FilePresenter;
import cn.kengtion.fileserver.Presenter.FilePresenterImpl;
import cn.kengtion.fileserver.R;
import cn.kengtion.socketlib.Client.Client;
import cn.kengtion.socketlib.Entity.FileInfo;
import cn.kengtion.socketlib.Utils.FileUtils;
import cn.kengtion.socketlib.Utils.LogUtils;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class ChooseFileViewImpl extends Activity implements ChooseFileView , View.OnClickListener{

    private static final String TAG = "ChooseFileViewImpl";
    private Button button;
    private RecyclerView fileList;
    private FileListAdapter fileListAdapter;
    private FilePresenter presenter;
    private ProgressBar progressBar;
    private Client client;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_choose_file);
        button = (Button) findViewById(R.id.send);
        fileList = (RecyclerView) findViewById(R.id.file_list);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        fileListAdapter = new FileListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fileList.setLayoutManager(layoutManager);
        fileList.setAdapter(fileListAdapter);
        presenter = new FilePresenterImpl(this , this , FileUtils.TYPE_APK);
        presenter.getFileList();
        progressBar.setVisibility(View.VISIBLE);
        button.setOnClickListener(this);
        handler = new Handler();//TODO DELETE
    }

    class SendFileHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    File file = new File(fileListAdapter.getFileSelected().get(0).getFilePath());
                    try {
                        InputStream is = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    @Override
    public void showFileList(List<FileInfo> fileInfoList) {
        progressBar.setVisibility(View.GONE);
        fileListAdapter.setFileInfoList(fileInfoList);
    }

    @Override
    public void showErrMsg() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                //修改为跳转到选择AP页面，当前仅测试功能
                client = new Client(handler);
                client.connect();
        }
    }

}
