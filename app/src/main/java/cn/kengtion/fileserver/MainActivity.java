package cn.kengtion.fileserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.kengtion.fileserver.View.ChooseFileViewImpl;
import cn.kengtion.socketlib.Client.Client;
import cn.kengtion.socketlib.Server.MiniServer;
import cn.kengtion.socketlib.Utils.LogUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button sendButton;
    private Button beClient;
    private MiniServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(this);
        beClient = (Button) findViewById(R.id.receive);
        beClient.setOnClickListener(this);
    }


    @Override
    protected void onPause(){
        super.onPause();
        LogUtils.d("MainActivity" , "OnPause");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                startActivity(new Intent(this, ChooseFileViewImpl.class));
                break;
            case R.id.receive:
                server = new MiniServer(5656, new Handler());
                server.start();
        }
    }

}
