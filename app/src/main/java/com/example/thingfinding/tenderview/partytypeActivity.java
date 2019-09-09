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

public class partytypeActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    //需要适配的数据
    private String[] names = {"结婚party","生日party","圣诞party","泳池party","音乐party"};;
    //图片集合
    private int[] icons = {R.drawable.jiehunparty,R.drawable.shengriparty,
            R.drawable.shengdanparty,R.drawable.yongciparty,R.drawable.musicparty};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partytype);
        //初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv1);
        //创建一个Adapter的实例
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        //设置Adapter
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < names.length; i++) {
            list.add(names[i]);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).equals("结婚party")){
                    setData("结婚party");
                }if (list.get(i).equals("圣诞party")){
                    setData("圣诞party");
                }if (list.get(i).equals("生日party")){
                    setData("生日party");
                }if (list.get(i).equals("泳池party")){
                    setData("泳池party");
                }if (list.get(i).equals("音乐party")){
                    setData("音乐party");
                }
            }
        });
    }
    public void setData(String partyname){
        Intent intent=new Intent(this,partyActivity.class);
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
                holder = new ViewHolder();
                holder.mTextView = (TextView)convertView.findViewById
                        (R.id. item_tv);
                holder.imageView=(ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
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
