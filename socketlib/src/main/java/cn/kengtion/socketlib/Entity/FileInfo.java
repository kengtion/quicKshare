package cn.kengtion.socketlib.Entity;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：文件信息定义
 */

public class FileInfo {
    /*
        拓展名
     */
    public static final String EXTEND_APK = ".apk";
    public static final String EXTEND_JPEG = ".jpeg";
    public static final String EXTEND_JPG = ".jpg";
    public static final String EXTEND_PNG = ".png";
    public static final String EXTEND_MP3 = ".mp3";
    public static final String EXTEND_MP4 = ".mp4";

    /**
     * 自定义文件类型
     */
    public static final int TYPE_APK = 1;
    public static final int TYPE_JPG = 2;
    public static final int TYPE_MP3 = 3;
    public static final int TYPE_MP4 = 4;

    /**
     * 文件传输的标识
     */
// 1 成功  -1 失败
    public static final int FLAG_SUCCESS = 1;
    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_FAILURE = -1;

    //必要属性
    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private int fileType;

    /**
     * 文件大小
     */
    private long size;

    //非必要属性
    /**
     * 文件显示名称
     */
    private String name;
    /**
     * 文件大小描述
     */
    private String sizeDesc;

    /**
     * 文件缩略图 (mp4与apk可能需要)
     */
    private Bitmap bitmap;

    /**
     * 文件额外信息
     */
    private String extra;


    /**
     * 已经处理的（读或者写）
     */
    private long procceed;

    /**
     * 文件传送的结果
     */
    private int result;

    public FileInfo(){};

    public FileInfo(String filePath, long size) {
        this.filePath = filePath;
        this.size = size;
    }

    /*
        转为JSON数据
     */
    public static String toJsonStr(FileInfo fileInfo){
        String jsonStr = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("filePath", fileInfo.getFilePath());
            jsonObject.put("fileType", fileInfo.getFileType());
            jsonObject.put("size", fileInfo.getSize());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /*
        解析JSON数据
     */
    public static FileInfo toObject(String jsonStr){
        FileInfo fileInfo = new FileInfo();
        try {
            JSONObject jsonObject =  new JSONObject(jsonStr);
            String filePath = (String) jsonObject.get("filePath");
            long size = jsonObject.getLong("size");
            int type = jsonObject.getInt("fileType");

            fileInfo.setFilePath(filePath);
            fileInfo.setSize(size);
            fileInfo.setFileType(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    public static String toJsonArrayStr(List<FileInfo> fileInfoList){
        JSONArray jsonArray = new JSONArray();
        if(fileInfoList != null){
            for(FileInfo fileInfo : fileInfoList ){
                if(fileInfo != null){
                    try {
                        jsonArray.put(new JSONObject(toJsonStr(fileInfo)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jsonArray.toString();
    }

    public static List<FileInfo> toFileInfoList(String jsonArr) throws JSONException {
        List<FileInfo> fileInfoList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonArr);
        for(int i=0 ; i<jsonArray.length();i++){
            FileInfo fileInfo = toObject(jsonArray.getJSONObject(i).toString());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }
    //getter and setter

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getProcceed() {
        return procceed;
    }

    public void setProcceed(long procceed) {
        this.procceed = procceed;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
