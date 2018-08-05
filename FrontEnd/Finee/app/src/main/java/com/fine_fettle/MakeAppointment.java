package com.fine_fettle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fine_fettle.adapter.HospitalAdapter;
import com.fine_fettle.adapter.TipsAdapter;
import com.fine_fettle.models.HospitalModel;
import com.fine_fettle.models.TipsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Priyadharshini on 16-Jul-18.
 */

public class MakeAppointment extends AppCompatActivity {
//    EditText pername, perage, peraddr, heaissue, docspecialization, docname, hosname, dates, slots;
//    Button book;
//    private ProgressBar progressBar;
//    private Calendar newCalendar;
//    TextView tv;

    private ArrayList<HospitalModel> mTipsList = new ArrayList<>();
    private RecyclerView mListView;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeapp);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        mListView = findViewById(R.id.hospital_list);
        new mymethod().execute();
    }

    public class mymethod extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL("http://35.204.108.96/hospital_select.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
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
                Gson gson = new Gson();
                Type type = new TypeToken<List<HospitalModel>>(){}.getType();
                ArrayList<HospitalModel> tipsList = gson.fromJson(jsonObj.getString("result"), type);
                if(tipsList!=null){
                    mTipsList = tipsList;
                    mTipsList.addAll(tipsList);
                    setAdapter();
                }
            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }


    }

    private void setAdapter(){
        HospitalAdapter adapter = new HospitalAdapter(this,R.layout.hospital_item_layout,mTipsList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(adapter);
    }

}
