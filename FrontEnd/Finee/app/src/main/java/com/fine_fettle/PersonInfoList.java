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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonInfoList extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    String username;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfolist);
        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        contactList = new ArrayList<>();
        new Handler().execute();
        lv = findViewById(R.id.list);
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
            pDialog = new ProgressDialog(PersonInfoList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();
            //String url= "http://192.168.43.194/u_profile_select.php?user="+tvView.getText().toString();
            String url= "http://10.13.1.17/u_profile_select.php?user="+username;
            //String url= "http://192.168.0.111/u_profile_select.php?user="+tvView.getText().toString();
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

                        String id = c.getString("u_id");
                        String name = c.getString("u_name");
                        String email = c.getString("email");
                        String phone = c.getString("phone");
                        String fname = c.getString("first_name");
                        String lname = c.getString("last_name");
                        String age = c.getString("age");
                        String gender = c.getString("gender");
                        String dob = c.getString("dob");
                        String blood = c.getString("bloodgroup");
                        String address = c.getString("address");
                        String city = c.getString("city");
                        String pincode = c.getString("pincode");

                        // tmp hash map for single contact
                        HashMap<String, String> employee = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employee.put("u_id", id);
                        employee.put("u_name", name);
                        employee.put("email", email);
                        employee.put("phone", phone);
                        employee.put("first_name", fname);
                        employee.put("last_name", lname);
                        employee.put("age", age);
                        employee.put("gender",gender);
                        employee.put("dob", dob);
                        employee.put("address", address);
                        employee.put("bloodgroup", blood);
                        employee.put("city", city);
                        employee.put("pincode", pincode);
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
            /*
             * Updating parsed JSON data into ListView
             */
            ListAdapter adapter = new SimpleAdapter(
                    PersonInfoList.this, contactList,
                    R.layout.activity_personinfoup, new String[]{"u_id", "u_name","first_name",
                    "last_name","email",
                    "phone","age","gender"
                    ,"dob","bloodgroup","address","city","pincode"}, new int[]{R.id.uid, R.id.uname,
                    R.id.fname, R.id.lname,R.id.email,R.id.phone,R.id.age,R.id.gender,R.id.dob,R.id.bgroup
                    ,R.id.address,R.id.city,R.id.pincode});

            lv.setAdapter(adapter);


        }

    }
}
