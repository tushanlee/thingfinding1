package com.example.thingfinding.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.DatePickerDialog;
import com.example.thingfinding.R;
import android.widget.DatePicker;
import android.widget.Toast;

public class My_DemandActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView exitText;
    private TextView choicebtn;
    private TextView timeText;
    private TextView addressbookText;
    private Button releasebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__demand);
        initView();
        initEvent();
    }

    private void initView() {
        exitText = (TextView) findViewById(R.id.exitText);
        choicebtn = (TextView) findViewById(R.id.choiceText);
        timeText = (TextView) findViewById(R.id.timeText);
        addressbookText= (TextView) findViewById(R.id.addressbookText);
        releasebtn= (Button) findViewById(R.id.releasebtn);
    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        choicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(My_DemandActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        timeText.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2019, 9, 11).show();
            }
        });
        addressbookText.setOnClickListener(this);
        releasebtn.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitText:
                exit();
                break;
            case R.id.addressbookText:
                addressbook();
                break;
            case R.id.releasebtn:
                showtijiaoDialog();
                break;
        }
    }

    public void exit() {
        finish();
    }

    public void addressbook() {
        Intent intent=new Intent(this,addressbookActivity.class);
        startActivity(intent);
    }

    public void showtijiaoDialog(){
        final android.app.AlertDialog.Builder normalDialog = new android.app.AlertDialog.Builder(this);
        normalDialog.setTitle("发布需求");
        normalDialog.setMessage("是否发布需求");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(My_DemandActivity.this, "发布成功", Toast.LENGTH_SHORT).show();

            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        normalDialog.show();// 显示
    }

}
