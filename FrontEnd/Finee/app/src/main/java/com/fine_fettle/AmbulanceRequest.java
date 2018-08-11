package com.fine_fettle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Murugappan V on 8/10/2018.
 */

public class AmbulanceRequest extends AppCompatActivity {

    Button b;
    String username, id;
    EditText issue, location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amb_req);

        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        id =intent.getStringExtra("id");
        issue = findViewById(R.id.issue);
        location = findViewById(R.id.location);
        b = findViewById(R.id.btn_request);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            submitForm();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AmbulanceRequest.this, Ambulance.class);
                    intent.putExtra("id",id );
                    startActivity(intent);
                    finish();
                }
            }, 2000);
            }
        });
        new AmbHandler().execute();
    }

    private void submitForm() {
        final String first = issue.getText().toString().trim();
        final String last = location.getText().toString().trim();

        if (TextUtils.isEmpty(first)) {
            issue.setError("Enter Firstname");
            issue.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(last)) {
            location.setError("Enter Lastname");
            location.requestFocus();
            return;
        }


        //if it passes all the validations
        HashMap<String, String> params = new HashMap<>();
        params.put("u_id", id);
        params.put("message", first + " " + last);
//        Toast.makeText(getApplicationContext(), "Hi: " + params.get("user") + "You have successfully updated you profile", Toast.LENGTH_LONG).show();
        PostRequestHandler postRequestHandler = new PostRequestHandler("http://35.204.108.96/ambulance_req.php", params);
        postRequestHandler.execute();
//        employeeList(view);
    }

    private class AmbHandler extends AsyncTask<Void, Void, Void> {

        private ListAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();
            String url= "http://35.204.108.96/amb_stat.php?u_id="+id;
            String jsonStr = sh.convertJson(url);
            System.out.println(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    int result = jsonObj.getInt("success");
                    if(result == 1) {
                        Intent intent = new Intent(AmbulanceRequest.this, Ambulance.class);
                        intent.putExtra("id",id );
                        startActivity(intent);
                        finish();
                    }
                    // Getting JSON Array node
//                    JSONArray employeeArray = new JSONArray(jsonStr);

                    // looping through All Contacts
//                    for (int i = 0; i < employeeArray.length(); i++) {
//                        String text = employeeArray.getString(i);
////                        JSONObject c = employeeArray.getJSONObject(i);
////
////                        String text = c.getString("text");
//
//
//                        // tmp hash map for single contact
//                        if(!text.contains("The request for  blood")) {
//                            HashMap<String, String> employee = new HashMap<>();
//
//                            // adding each child node to HashMap key => value
//                            employee.put("text", text);
//
//                            // adding contact to contact list
//                            contactList.add(employee);
//                        }
//                    }
                } catch (final JSONException e) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });

                }
            } else {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    Blood.this, contactList,
//                    R.layout.activity_bloodviewlist, new String[]{"text"}, new int[]{R.id.textv});
//
//            lv.setAdapter(adapter);


        }

    }
}
