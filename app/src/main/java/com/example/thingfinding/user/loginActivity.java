package com.example.thingfinding.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.Bean.userBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.HttpUtil;
import com.example.thingfinding.ItemInfo;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private ImageView image;
    private Button btn;
    Bitmap bitmap=null;
    byte buff[] = new byte[125*250];
    private CheckBox checkBox;
    static String YES = "yes";
    static String NO = "no";
    static String name, password,image1;
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    private ItemInfo itemInfo;
    private List<Map<String,String>> list;
    private Map<String,String> map;
    private JSONArray arr;
    private SQLiteHelper dbhelper;
    private OkHttpHelp mokhttphelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        et_username=(EditText)findViewById(R.id.username) ;
        et_password=(EditText)findViewById(R.id.password) ;
        image=(ImageView)findViewById(R.id.imageView2) ;
        btn=(Button)findViewById(R.id.button3);
        checkBox=(CheckBox)findViewById(R.id.checkboxs) ;
       sp = getSharedPreferences(FILE, MODE_PRIVATE);
        name = sp.getString("name", "");
        password = sp.getString("password", "");
        image1 = sp.getString("image", "");
        isMemory = sp.getString("isMemory", NO);
//进入界面时，这个if用来判断SharedPreferences里面name和password有没有数据，有的话则直接打在EditText上面
        if (isMemory.equals(YES)) {
            et_username.setText(name);
            et_password.setText(password);
            checkBox.setChecked(true);
            queryImage();
        }else{
            et_username.setText(name);
            queryImage();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                passData();
            }
        });

    }

    public void exit(View v){
        finish();
    }

    public void register(View v){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivityForResult(intent, 1);
    }

    public void findpassword(View v){
        Intent intent=new Intent(this,FindPasswordActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void passData() {
        remenber();
       Intent intent=new Intent(this,Fragment_Me.class);
        String name=et_username.getText().toString().trim();
        String paw=et_password.getText().toString().trim();
        String url="http://192.168.1.101:8080/login?";
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Map<String,String> map=new HashMap<>();
        map.put("user",name);
        map.put("pwd",paw);
        try {
            mokhttphelp=OkHttpHelp.getinstance();
            mokhttphelp.post(url, map, new BaseCallback <CommonResultBean>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onSuccess(CommonResultBean response) {
                    String data=(String) response.getData();
                    String code=response.getCode();
                    String type=response.getType();
                    String msg=response.getMsg();
                    Log.i("--**-**--","登录成功");
                    Log.i("--**",data);
                    Log.i("--**",code);
                    Log.i("--**",type);
                    Log.i("--**",msg);
                }

//                @Override
//                public void onSuccess(CommonResultBean<userBean> response) {
//                    if (response.getData()!=null){
//                        userBean userBean=response.getData();
//                        String name=userBean.getUsername();
//                        Log.i("--**-**--","登录成功");
//                    }
//                }
                @Override
                public void onError(Response response, int errorCode, Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            DialogUtil.showDialog(this,"服务器响应异常",false);
            e.printStackTrace();
        }

//        String username = null;
//        String password=null;
//        try{
//            String result=query(name,paw);
//            if (result!=null&&Integer.parseInt(result)>0){
//                DialogUtil.showDialog(this,"登录成功",false);
//                intent.putExtra("login",name);
//                 setResult(1, intent);
//                 finish();
//            }
//        }catch (Exception e){
//            DialogUtil.showDialog(this,"服务器响应异常，请稍后再试",false);
//            e.printStackTrace();
//        }
//       Cursor cur = db.query("Users",new String[]{"username","password"},
//                "username=? and password=?", new String[]{et_username.getText().toString().trim(),
//                       et_password.getText().toString().trim()}, null, null,
//               null);
//         while(cur.moveToNext()) {
//            //将Blob数据转化为字节数组
//            username = cur.getString(cur.getColumnIndex("username"));
//             password = cur.getString(cur.getColumnIndex("password"));
//             if(et_username.getText().toString().trim().equals(username)&&
//                     et_password.getText().toString().trim().equals(password)){
//                intent.putExtra("login",name);
//                 setResult(1, intent);
//                 finish();
//             }else if(!et_username.getText().toString().trim().equals(username)){
//                 AlertDialog diaglog;
//                 diaglog=new AlertDialog.Builder(this).setTitle("")
//                         .setMessage("用户名错误！")
//                         .setIcon(R.mipmap.ic_launcher)
//                         .setPositiveButton("确定",null)
//                         .create();
//                 diaglog.show();
//             }else if(!et_password.getText().toString().trim().equals(password)){
//                 AlertDialog diaglog;
//                 diaglog=new AlertDialog.Builder(this).setTitle("")
//                         .setMessage("密码错误！")
//                         .setIcon(R.mipmap.ic_launcher)
//                         .setPositiveButton("确定",null)
//                         .create();
//                 diaglog.show();
//             }
//        }
//        cur.close();
//        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String query(String username, String password) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("user",username);
        map.put("pwd",password);
         String url=HttpUtil.BASE_URL+ "/login";
         return HttpUtil.postRequest(url,map);
    }

    public void remenber() {
        if (checkBox.isChecked()) {
            if (sp == null) {
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("name", et_username.getText().toString());
            edit.putString("password", et_password.getText().toString());
            edit.putString("isMemory", YES);
            edit.putBoolean("isLogin", true);//存入boolean类型的登录状态
            edit.commit();
        } else if (!checkBox.isChecked()) {
            if (sp == null) {
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("name", et_username.getText().toString());
            edit.putString("isMemory", NO);
            edit.commit();
        }
    }

    public byte[] bitmabToBytes(){

        //将图片转化为位图
        image.setDrawingCacheEnabled(Boolean.TRUE);
        Bitmap bi=image.getDrawingCache();
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.id.imageView2);
        int size = bi.getWidth() * bitmap.getHeight() * 4;
        //创建一个字节数组输出流,流的大小为size
        ByteArrayOutputStream baos= new ByteArrayOutputStream(size);
        try {
            //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bi.compress(Bitmap.CompressFormat.PNG, 100, baos);
            //将字节数组输出流转化为字节数组byte[]
            byte[] imagedata = baos.toByteArray();
            return imagedata;
        }catch (Exception e){
        }finally {
            try {
                bi.recycle();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
    public void queryImage() {
         this.dbhelper = SQLiteHelper.getInstance(this);
      //  Toast.makeText(this,"信息已删除！",Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        byte[] imgData = null;

       Cursor cur = db.query("Users",new String[]{"avatar"},
                "username=?", new String[]{et_username.getText().toString()},
               null, null, null);

        if(cur.getCount()==0){
        }
        while(cur.moveToNext()) {
            //将Blob数据转化为字节数组
            imgData = cur.getBlob(cur.getColumnIndex("avatar"));
            Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            //将位图显示为图片
             image.setImageBitmap(imagebitmap);
        }
        cur.close();
        db.close();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //从注册界面传递过来的用户名
            String userName =data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_username.setText(userName);
                //设置光标的位置
                et_username.setSelection(userName.length());
                queryImage();
            }
        }
    }



}
