package com.fine_fettle;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button submit, reg;
    private String TAG = MainActivity.class.getSimpleName();
    String name;
    int id;
    String pass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.puser);
        password = findViewById(R.id.ppass);
        submit = findViewById(R.id.pb1);
        reg = findViewById(R.id.pb2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Personreg.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Authorizing user",Toast.LENGTH_SHORT).show();
                new mymethod().execute();
//                Intent intent = new Intent(MainActivity.this, Personhome.class);
//                intent.putExtra("id","1003");
//
//                intent.putExtra("user", "Valliyappan");
//                intent.putExtra("pass", "");
//                startActivity(intent);
//                finish();
            }
        });

    }
    public class mymethod extends AsyncTask<String, Void, String> {
        String user=username.getText().toString();
        String pass=password.getText().toString();
        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL("http://192.168.43.194/user_login.php");
                //URL url = new URL("http://192.168.42.229/user_login.php");// here is your URL path
                //URL url = new URL("http://192.168.43.59/user_login.php");
                //URL url = new URL("http://10.13.1.17/user_login.php");
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user", user);
                postDataParams.put("pass",pass);
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuilder sb = new StringBuilder("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return "Invalid Response : " + responseCode;
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result) {

            try {
                System.out.println(result);
                JSONObject jsonObj = new JSONObject(result);


                   id = Integer.parseInt(jsonObj.getString("u_id"));
                   name = jsonObj.getString("uname");
                    pass1 =jsonObj.getString("password");
                if (name.equals(user) && pass1.equals(pass)) {
                    Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit();
                    editor.putString("name", name);
                    editor.putString("pass", pass);
                    editor.putInt("id", id);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, Personhome.class);
                    intent.putExtra("id",jsonObj.getString("u_id"));

                    intent.putExtra("user", user);
                    intent.putExtra("pass", pass);
                    intent.putExtra("user", username.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
           // Toast.makeText(getApplicationContext(), "Invalid Username and Password" + result,
                   // Toast.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(), "Id" + id,
//                    Toast.LENGTH_LONG).show();
        }


    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            System.out.println(result);

        }
        return result.toString();
    }
}



