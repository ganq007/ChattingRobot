package com.tms.geq.chattingrobot;

import android.content.Context;
import android.media.ExifInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tms.geq.chattingrobot.vo.MsgItem;

import java.util.ConcurrentModificationException;
import java.util.List;

public class MyAdpater extends BaseAdapter {
    private  Context context;
    private  List<MsgItem> list;
    public MyAdpater(Context context ,List<MsgItem> list){
        this.context =context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView= View.inflate(context, R.layout.msg_item, null);
            viewHolder = new ViewHolder();
            viewHolder.leftLaytout = convertView.findViewById(R.id.left_layout);
            viewHolder.rightLayout = convertView.findViewById(R.id.right_layout);
            viewHolder.leftInfo = convertView.findViewById(R.id.left_msg);
            viewHolder.rightInfo = convertView.findViewById(R.id.right_msg);
            viewHolder.leftInfo.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.rightInfo.setMovementMethod(LinkMovementMethod.getInstance());
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MsgItem msgItem = (MsgItem) getItem(position);
        Log.e("ma", "getView: "+msgItem.info+msgItem.type );
        if (msgItem.type==MsgItem.ME){
            viewHolder.leftLaytout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightInfo.setText(msgItem.info);
        }else{
            viewHolder.leftLaytout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftInfo.setText(Html.fromHtml(msgItem.info));
        }
     //   MsgItem msgItem = (MsgItem) getItem(position);
       // Log.e("sssssssss", "getView: "+msgItem.type+"===="+msgItem.info );
//        View view = View.inflate(context, R.layout.msg_item, null);
//        TextView msg = view.findViewById(R.id.left_msg);
//        msg.setText("ddddddddddddddddddddddddd");
//        //Log.e("sssssssss", "getView: "+msgItem.type+"===="+msgItem.info );
        return convertView;
    }

    static  class ViewHolder{
        LinearLayout leftLaytout, rightLayout;
        TextView leftInfo ,rightInfo;
    }
}