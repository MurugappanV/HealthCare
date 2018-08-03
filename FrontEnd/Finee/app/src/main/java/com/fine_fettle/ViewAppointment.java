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
            new Handler().execute();
            }
        /**
         * Async task class to get json by making HTTP call
         */
        private class Handler extends AsyncTask<Void, Void, Void> {

            private ListAdapter adapter;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(ViewAppointment.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                JsonParser sh = new JsonParser();

               //String url= "http://192.168.42.229/viewallapp.php?ID="+tvView.getText();
                String url= "http://10.13.1.17/viewallapp.php?ID="+tvView.getText();
               //String url= "http://192.168.0.111/viewallapp.php?ID="+tvView.getText();
                String jsonStr = sh.convertJson(url);
                System.out.println(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray employeeArray = jsonObj.getJSONArray("response");

                        // looping through All Contacts
                        for (int i = 0; i < employeeArray.length(); i++) {
                            JSONObject c = employeeArray.getJSONObject(i);

                            String p_id = c.getString("id");
                            String name = c.getString("p_name");
                            String age = c.getString("p_age");
                            String address = c.getString("p_addr");
                            String health = c.getString("h_issue");
                            String docspecial = c.getString("doc_specialization");
                            String docname = c.getString("doc_name");
                            String hosname = c.getString("hos_name");
                            String dates = c.getString("date");
                            String slots = c.getString("slot");

                            // tmp hash map for single contact
                            HashMap<String, String> employee = new HashMap<>();

                            // adding each child node to HashMap key => value
                           employee.put("id", p_id);
                            employee.put("p_name", name);
                            employee.put("p_age", age);
                            employee.put("p_addr", address);
                            employee.put("h_issue", health);
                            employee.put("doc_specialization", docspecial);
                            employee.put("doc_name", docname);
                            employee.put("hos_name", hosname);
                            employee.put("date", dates);
                            employee.put("slot", slots);
                            // adding contact to contact list
                            contactList.add(employee);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        ViewAppointment.this, contactList,
                        R.layout.activity_viewapp, new String[]{"id", "p_name", "p_age",
                        "p_addr","h_issue","doc_name","doc_specialization","hos_name"
                ,"date","slot"}, new int[]{R.id.id, R.id.p_name,
                        R.id.p_age, R.id.p_addr,R.id.h_issue,R.id.doc_name,R.id.doc_specialization,R.id.hos_name,R.id.date,R.id.slot});

                lv.setAdapter(adapter);


            }

        }


    }