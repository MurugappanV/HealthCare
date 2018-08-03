package com.fine_fettle;

/**
 * Created by Priyadharshini on 02-Jun-18.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
            try{
                sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                callBack();
            }
            }
        };
        timerThread.start();
    }

    private void callBack() {
        Intent intent;
        SharedPreferences prefs = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        int id = prefs.getInt("id", -1);
        if (id != -1) {
            String name = prefs.getString("name", "User");
            String pass = prefs.getString("pass", "pass");
            //Toast.makeText(getApplicationContext(), "Welcome" + name, Toast.LENGTH_LONG).show();
            intent = new Intent(SplashActivity.this, Personhome.class);
            intent.putExtra("id",id+"");
            intent.putExtra("user", name);
            intent.putExtra("pass", pass);
        } else {
            intent = new Intent(SplashActivity.this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

}