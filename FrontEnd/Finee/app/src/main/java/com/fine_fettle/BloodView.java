package com.fine_fettle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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


public class BloodView extends AppCompatActivity{
    TextView tvView,tv;
    private ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodview);
        tvView = findViewById(R.id.tvView);
        tv = findViewById(R.id.tvView1);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("user");
        final String id = intent.getStringExtra("id");
        tvView.setText(username);
        tv.setText(id);
        lv = findViewById(R.id.list);
        contactList = new ArrayList<>();
        new Handler().execute();
    }
    private class Handler extends AsyncTask<Void, Void, Void> {

        private ListAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BloodView.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();

            String url= "http://192.168.43.194/viewallapp.php?ID="+tvView.getText();
            //String url= "http://10.13.1.17/bloodselect.php?u_id="+tv.getText();
            //String url= "http://192.168.0.111/viewallapp.php?ID="+tvView.getText();
            String jsonStr = sh.convertJson(url);
            System.out.println(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray employeeArray = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < employeeArray.length(); i++) {
                        JSONObject c = employeeArray.getJSONObject(i);

                        String text = c.getString("text");


                        // tmp hash map for single contact
                        HashMap<String, String> employee = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employee.put("text", text);

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
                    BloodView.this, contactList,
                    R.layout.activity_bloodviewlist, new String[]{"text"}, new int[]{R.id.textv});

            lv.setAdapter(adapter);


        }

    }

}
