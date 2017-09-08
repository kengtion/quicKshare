package cn.kengtion.fileserver.View;

import java.util.List;

import cn.kengtion.socketlib.Entity.FileInfo;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public interface ChooseFileView {
    void showFileList(List<FileInfo> fileInfoList);
    void showErrMsg();
}
