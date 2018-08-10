package com.fine_fettle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Priyadharshini on 02-Jul-18.
 */

public class Blood extends AppCompatActivity {
    Button breq,bview;
    String id, name;
    Spinner spinner;
    RatingBar ratingBar;
    String spin, rate;
    private ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);
        breq=findViewById(R.id.bloodreq);
        Intent intent = getIntent();
        name = intent.getStringExtra("user");
        id=intent.getStringExtra("id");

        addListenerOnSpinnerItemSelection();
        ratingBar = findViewById(R.id.rating);

        breq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                rate = String.valueOf(ratingBar.getRating());
                submitReq(view);
            }
        });

        lv = findViewById(R.id.list);
        contactList = new ArrayList<>();
        new Handler().execute();
    }

    private void submitReq(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("u_name", name);
        params.put("id", id);
        params.put("req_blood_grp", spin);
        params.put("rate", rate);
        System.out.println("NAME:" + name + "ID:" + id + "BLOOD:" + spin + "RATE:" + rate);
        Toast.makeText(getApplicationContext(), "Hi: " + params.get("u_name") + "Your Blood Request is sent", Toast.LENGTH_LONG).show();
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.BLOODREQ, params);
        postRequestHandler.execute();

    }


    private void addListenerOnSpinnerItemSelection() {
        spinner = findViewById(R.id.blood);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spin = String.valueOf(spinner.getSelectedItem());

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class Handler extends AsyncTask<Void, Void, Void> {

        private ListAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();
            String url= "http://35.204.108.96/bloodselect.php?u_id="+id;
            String jsonStr = sh.convertJson(url);
            System.out.println(url);

            if (jsonStr != null) {
                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray employeeArray = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < employeeArray.length(); i++) {
                        String text = employeeArray.getString(i);
//                        JSONObject c = employeeArray.getJSONObject(i);
//
//                        String text = c.getString("text");


                        // tmp hash map for single contact
                        HashMap<String, String> employee = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employee.put("text", text);

                        // adding contact to contact list
                        contactList.add(employee);
                    }
                } catch (final JSONException e) {
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
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Blood.this, contactList,
                    R.layout.activity_bloodviewlist, new String[]{"text"}, new int[]{R.id.textv});

            lv.setAdapter(adapter);


        }

    }
}
