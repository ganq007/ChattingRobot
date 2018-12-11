package com.tms.geq.chattingrobot;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tms.geq.chattingrobot.utils.HttpGetAllSenseUtils;
import com.tms.geq.chattingrobot.utils.JSONUtils;
import com.tms.geq.chattingrobot.vo.MsgItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "AppCompatActivity";
    private Button mBtu;
    private EditText mBox;
    private ListView mListView;
    private List<MsgItem>  list;
    private MyAdpater myAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //
        senHello("开始了");
    }



    private void initView() {
        mListView = findViewById(R.id.msg_list_view);
        mBox = findViewById(R.id.input_box);
        mBtu = findViewById(R.id.send_btn);
        //发送按钮点击事件
        mBtu.setOnClickListener(this);
        //展示的adpater
        list = new ArrayList<MsgItem>();
        myAdpater = new MyAdpater(MainActivity.this,list);
        mListView.setAdapter(myAdpater);
    }

    private void sendtMsg(String msg,int type) {
        MsgItem msgItem = new MsgItem(msg,type);
        list.add(msgItem);
        Log.e(TAG, "sendtMsg: listsizwe" +list.size());
        myAdpater.notifyDataSetChanged();
        mListView.setSelection(list.size());
    }
    @Override
    public void onClick(View v) {
        final String msg = mBox.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)){
            new Thread(){
                @Override
                public void run() {
                    String json = HttpGetAllSenseUtils.JsonPost(msg);
                    final String result = JSONUtils.getResult(json);
                    Log.e(TAG, "run: "+result );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (result==""){
                                        sendtMsg("请联网",MsgItem.ME);
                                    }else{
                                        sendtMsg(msg,MsgItem.ME);
                                        sendtMsg(result,MsgItem.HE);
                                    }
                                }
                            });
                        }
                    });
                }
            }.start();
        }
        mBox.setText("");
    }


    public void senHello(final String msg){
        if (!TextUtils.isEmpty(msg)){
            new Thread(){
                @Override
                public void run() {
                    String json = HttpGetAllSenseUtils.JsonPost(msg);
                    final String result = JSONUtils.getResult(json);
                    Log.e(TAG, "run: "+result );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (TextUtils.isEmpty(result)){
                                sendtMsg("请联网",MsgItem.HE);
                            }else{
                                sendtMsg(result,MsgItem.ME);
                            }
                        }
                    });
                }
            }.start();
        }
    }

}
