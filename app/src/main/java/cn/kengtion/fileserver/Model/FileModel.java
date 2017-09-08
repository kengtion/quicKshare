package cn.kengtion.fileserver.Model;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public interface FileModel {
     void getFileList(int fileType);
     void sendFile();
     void receiveFile();
}
