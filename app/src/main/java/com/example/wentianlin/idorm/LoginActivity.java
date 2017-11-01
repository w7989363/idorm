package com.example.wentianlin.idorm;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final int CHECK_NUM_AND_PWD = 1;
    private ImageView loginBtn;
    private EditText numEdit;
    private EditText pwdEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (ImageView) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what) {
                case CHECK_NUM_AND_PWD:
                    if(msg.obj.equals("1")){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("stu_num",numEdit.getText().toString());
                        startActivity(i);
                        Toast.makeText(LoginActivity.this,"登录成功！", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"学号或密码错误！", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //点击事件监听函数
    @Override
    public void onClick(View v) {
        //点击登录按钮
        if(v.getId() == R.id.loginBtn){
            //获取输入
            numEdit = (EditText)findViewById(R.id.numEditText);
            pwdEdit= (EditText)findViewById(R.id.pwdEditText);
            String numText = numEdit.getText().toString();
            String pwdText = pwdEdit.getText().toString();
            Log.d("debug",numText);
            Log.d("debug",pwdText);
            checkNumPwd(numText,pwdText);
        }
    }

    //验证账号密码
    private void checkNumPwd(String num, String pwd){
        final String address = "http://45.78.15.145/test.php";
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con=null;
                try{
                    URL url = new URL(address);
                    con = (HttpURLConnection)url.openConnection( );
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder() ;
                    String str;
                    while((str=reader.readLine()) != null){
                        response.append(str);
                        Log.d("debug", str);
                    }
                    String responseStr=response.toString();
                    Message msg =new Message();
                    msg.what = CHECK_NUM_AND_PWD;
                    msg.obj= responseStr;
                    mHandler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start();
    }


}
