package cn.kengtion.fileserver;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.kengtion.socketlib.Client.Client;
import cn.kengtion.socketlib.Server.MiniServer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MiniServer miniServer;
    private Button sendButton;
    private Button beClient;
    private TextView msgRecv;
    private EditText msgSend;
    private boolean isServer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new MsgHandler();
        msgSend = (EditText) findViewById(R.id.message_send);
        sendButton = (Button) findViewById(R.id.send);
        msgRecv = (TextView) findViewById(R.id.message_recv);
        sendButton.setOnClickListener(this);
        beClient = (Button) findViewById(R.id.be_client);
        beClient.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                if (isServer)
                    miniServer.sendMsg(msgSend.getText().toString()+"\n");
                else {
                    miniServer = new MiniServer(5656 , handler);
                    miniServer.start();
                    isServer = true;
                }
                break;
            case R.id.be_client:
                Client client = new Client(handler);
                client.connect();
                isServer = false;
        }
    }

    class MsgHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    msgRecv.setText(msg.obj.toString());
            }
        }
    }
}
