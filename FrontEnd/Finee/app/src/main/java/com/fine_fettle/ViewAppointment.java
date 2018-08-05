package com.fine_fettle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Priyadharshini on 16-Jul-18.
 */

public class ViewAppointment extends AppCompatActivity {
        private String TAG = MainActivity.class.getSimpleName();
        private ProgressDialog pDialog;
        private ListView lv;
       String id;
    TextView tvView;
    ArrayList<HashMap<String, String>> contactList;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_viewall);
            tvView = findViewById(R.id.tvView);
        Intent intent = getIntent();
        final String id=intent.getStringExtra("id");
            tvView.setText(id);
            contactList = new ArrayList<>();
            lv = findViewById(R.id.list);
//            new Handler().execute();
            }
        /**
         * Async task class to get json by making HTTP call
         */



    }