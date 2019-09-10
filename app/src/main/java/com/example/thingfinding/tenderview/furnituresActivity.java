package com.example.thingfinding.tenderview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thingfinding.R;

public class furnituresActivity extends AppCompatActivity {
    private Button btntijiao;
    private Button btngoueuche;
    private EditText etfuwuming;
    private String Mark = "mark";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnitures);
        btntijiao=(Button)findViewById(R.id.btntijiao);
        btngoueuche=(Button)findViewById(R.id.btngouwuche);
        etfuwuming=(EditText)findViewById(R.id.etjiajufuwuming);
        Intent intent=getIntent();
        String fuwuming=intent.getStringExtra("fuwuming");
        etfuwuming.setText(fuwuming);
        btngoueuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("isLogin", true) == true){
                    showgouwucheDialog();
                }else{
                    isLoginDialog();
                }

            }
        });
        btntijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("isLogin", true) == true){
                    showtijiaoDialog();
                }else{
                    isLoginDialog();
                }
            }
        });
    }
    public void showgouwucheDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("购物车")
                .setMessage("加入购物车")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create();
        dialog.show();

    }
    public void showtijiaoDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提交订单")
                .setMessage("是否提交订单")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create();
        dialog.show();
    }

    public void isLoginDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("请先登录")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create();
        dialog.show();
    }
}
