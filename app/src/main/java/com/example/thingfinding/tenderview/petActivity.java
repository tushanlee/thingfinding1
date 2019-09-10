package com.example.thingfinding.tenderview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thingfinding.R;

public class petActivity extends AppCompatActivity {
    private Button btntijiao;
    private Button btngoueuche;
    private EditText etfuwuming;
    private EditText  etyuanjia;
    private EditText etcuxiaojia;
    private EditText etchongwulaingfeifei;
    private EditText etbaoyangfei;
    private String Mark = "mark";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        btntijiao=(Button)findViewById(R.id.btntijiao);
        btngoueuche=(Button)findViewById(R.id.btngouwuche);
        etfuwuming=(EditText)findViewById(R.id.etchongwufuwuming);
        etyuanjia=(EditText)findViewById(R.id.etyuanjia);
        etcuxiaojia=(EditText)findViewById(R.id.etcuxiaojia);
        etchongwulaingfeifei=(EditText)findViewById(R.id.etchongwuliangfei);
        etbaoyangfei=(EditText)findViewById(R.id.etchongwubaoyangfei);
        sp = this.getSharedPreferences(Mark, Context.MODE_PRIVATE);
        etyuanjia.setText("800");
        etcuxiaojia.setText("750");
        etchongwulaingfeifei.setText("100");
        etbaoyangfei.setText("150");
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
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(petActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        dialog.show();

    }
    public void showtijiaoDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提交订单")
                .setMessage("是否提交订单")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(petActivity.this,"提交订单成功",Toast.LENGTH_SHORT).show();
                    }
                })
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
