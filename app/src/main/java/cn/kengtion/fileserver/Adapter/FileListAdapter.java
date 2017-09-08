package cn.kengtion.fileserver.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import cn.kengtion.fileserver.R;
import cn.kengtion.socketlib.Entity.FileInfo;

/**
 * 创建时间 2017/9/7
 * 创建人 洪坤峰
 * 功能描述：
 */

public class FileListAdapter extends RecyclerView.Adapter{
    private final LayoutInflater inflater;
    private final Context context;
    private List<FileInfo> fileInfoList;
    private List<FileInfo> fileSelected = new ArrayList<>();
    public boolean isSend = false;

    public FileListAdapter(Context context){
        this.context =context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileHolder(inflater.inflate(R.layout.item_file_to_choose , null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FileHolder){
            final FileInfo fileInfo = fileInfoList.get(position);
            if(fileInfo == null)
                return;
            else {
                ((FileHolder) holder).v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(((FileHolder) holder).isSelected && !isSend){
                            v.setBackgroundColor(context.getColor(R.color.white));
                            ((FileHolder) holder).isSelected = false;
                            fileSelected.remove(fileInfo);
                        }else if (!isSend){
                            ((FileHolder) holder).isSelected = true;
                            v.setBackgroundColor(context.getColor(R.color.colorPrimary));
                            fileSelected.add(fileInfo);
                        }
                    }
                });
                ((FileHolder) holder).icon.setImageBitmap(fileInfo.getBitmap());
                ((FileHolder) holder).size.setText(String.valueOf(fileInfo.getSize()));
                ((FileHolder) holder).name.setText(fileInfo.getName());
                ((FileHolder) holder).path.setText(fileInfo.getFilePath());
            }
        }
    }

    @Override
    public int getItemCount() {
        if(fileInfoList!=null)
            return fileInfoList.size();
        return 0;
    }

    public void setFileInfoList(List<FileInfo> fileInfoList) {
        this.fileInfoList = fileInfoList;
        notifyDataSetChanged();
    }

    public List<FileInfo> getFileSelected() {
        return fileSelected;
    }

    class FileHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView name;
        TextView size;
        TextView path;
        View v;
        boolean isSelected;

        public FileHolder(View v){
            super(v);
            this.v = v;
            icon = (ImageView) v.findViewById(R.id.icon);
            name = (TextView) v.findViewById(R.id.name);
            size = (TextView) v.findViewById(R.id.size);
            path = (TextView) v.findViewById(R.id.path);
            isSelected = false;
        }
    }
}
