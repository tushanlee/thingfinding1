package com.example.thingfinding.tenderview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.R;

import java.util.ArrayList;

public class pettypeActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    //需要适配的数据
    private String[] names = {"宠物猫","宠物狗","宠物鼠","宠物猪","小猪"};;
    //图片集合
    private int[] icons = {R.drawable.cat,R.drawable.dog2,
            R.drawable.shu,R.drawable.pig,R.drawable.guipig};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pettype);
        //初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv2);
        //创建一个Adapter的实例
       MyBaseAdapter mAdapter = new pettypeActivity.MyBaseAdapter();
        //设置Adapter
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < names.length; i++) {
            list.add(names[i]);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).equals("宠物猫")){
                    setData("宠物猫");
                }if (list.get(i).equals("宠物狗")){
                    setData("宠物狗");
                }if (list.get(i).equals("宠物猪")){
                    setData("宠物猪");
                }if (list.get(i).equals("宠物鼠")){
                    setData("宠物鼠");
                }if (list.get(i).equals("小猪")){
                    setData("小猪");
                }
            }
        });
    }
    public void setData(String partyname){
        Intent intent=new Intent(this,petActivity.class);
        intent.putExtra("fuwuming",partyname);
        startActivity(intent);
    }
    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        @Override
        public int getCount() {
            //返回ListView Item条目的总数
            return names.length;
        }
        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView Item条目代表的对象
            return names[position];
        }
        //得到Item的id
        @Override
        public long getItemId(int position) {
            //返回ListView Item的id
            return position;
        }
        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.list_item2,parent,false);
                holder = new MyBaseAdapter.ViewHolder();
                holder.mTextView = (TextView)convertView.findViewById
                        (R.id. item_tv);
                holder.imageView=(ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(holder);
            } else {
                holder = (pettypeActivity.MyBaseAdapter.ViewHolder) convertView.getTag();
            }
            holder.mTextView.setText(names[position]);
            holder.imageView.setBackgroundResource(icons[position]);
            return convertView;
        }
        class ViewHolder {
            TextView mTextView;
            ImageView imageView;
        }
    }
}
