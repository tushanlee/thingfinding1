package com.example.thingfinding.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.R;

public class addressbookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addbtn;
    private ListView listView;
    private TextView exitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressbook);
        initView();
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        initEvent();
    }
    private void initView() {
        addbtn = (Button) findViewById(R.id.releasebtn);
        listView = (ListView) findViewById(R.id.addLv);
        exitText=(TextView) findViewById(R.id.exitText);

    }
    private void initEvent() {
        addbtn.setOnClickListener(this);
        exitText.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.releasebtn:

                break;
            case R.id.exitText:
                exit();
                break;
        }
    }
    public void exit() {
        finish();
    }

    class MyBaseAdapter extends BaseAdapter {
        public int getCount() {
            return 1;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int postion) {
            return postion;
        }

        public View getView(int postion, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.addbook_list, parent, false);
                holder = new ViewHolder();
                holder.nameText = (TextView) convertView.findViewById(R.id.nameText);
                holder.addressText = (TextView) convertView.findViewById(R.id.addText);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.nameText.setText("哈哈");
            holder.addressText.setText("广东省茂名市茂南区广东石油化工学院");
            return convertView;
        }

    }

    class ViewHolder {
        TextView nameText;
        TextView addressText;

    }

}
