package cn.kengtion.fileserver.Presenter;

import android.content.Context;

import java.util.List;

import cn.kengtion.fileserver.Model.FileModel;
import cn.kengtion.fileserver.Model.FileModelImpl;
import cn.kengtion.fileserver.View.ChooseFileView;
import cn.kengtion.socketlib.Entity.FileInfo;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class FilePresenterImpl implements FilePresenter , FileModelImpl.onFileListener {

    private ChooseFileView view ;
    private FileModel fileModel;
    private Context context;
    private int fileType;

    public FilePresenterImpl(ChooseFileView view , Context context, int fileType){
        this.view = view;
        this.context = context;
        this.fileModel = new FileModelImpl(context,this);
        this.fileType = fileType;
    }

    @Override
    public void getFileList() {
        fileModel.getFileList(fileType);
    }


    @Override
    public void success(List<FileInfo> fileInfoList) {
        view.showFileList(fileInfoList);
    }

    @Override
    public void fail() {

    }
}
