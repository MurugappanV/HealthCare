package com.fine_fettle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.fine_fettle.adapter.HelpAdapter;
import com.fine_fettle.adapter.ViewAppoinmentAdapter;
import com.fine_fettle.models.HelpModel;
import com.fine_fettle.models.HospitalModel;
import com.fine_fettle.models.NeedModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Murugappan V on 8/6/2018.
 */

public class NeedList extends AppCompatActivity {
//    Button makeapp;
    private ProgressDialog pDialog;
    ArrayList<HelpModel> contactList = new ArrayList<>();
    private RecyclerView mListView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_need);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        mListView = findViewById(R.id.need_list);
        new AppointmentGetter().execute();
//        makeapp = findViewById(R.id.request);
//        makeapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NeedList.this, Need.class);
//                intent.putExtra("id", id);
////                Toast.makeText(getApplicationContext(), "Id" + id,Toast.LENGTH_LONG).show();
//                startActivity(intent);
//            }
//        });
    }

    private void setAdapter(){
        HelpAdapter adapter = new HelpAdapter(this,R.layout.help_item_layout,contactList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(adapter);
    }

    private class AppointmentGetter extends AsyncTask<Void, Void, Void> {

        private ListAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(NeedList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();
            String url= "http://35.204.108.96/needy_user_view.php";
            String jsonStr = sh.convertJson(url);
            System.out.println(url);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<HelpModel>>(){}.getType();
                    ArrayList<HelpModel> tipsList = gson.fromJson(jsonObj.getString("result"), type);

                    contactList.addAll(tipsList);
                    // Getting JSON Array node
//                    JSONArray employeeArray = jsonObj.getJSONArray("response");
//
//                    // looping through All Contacts
//                    contactList = new ArrayList<>();
//                    for (int i = 0; i < employeeArray.length(); i++) {
//                        JSONObject c = employeeArray.getJSONObject(i);
//
//                        String p_id = c.getString("id");
//                        String name = c.getString("p_name");
//                        String age = c.getString("p_age");
//                        String address = c.getString("p_addr");
//                        String health = c.getString("h_issue");
//                        String docspecial = c.getString("doc_specialization");
//                        String docname = c.getString("doc_name");
//                        String hosname = c.getString("hos_name");
//                        String dates = c.getString("date");
//                        String slots = c.getString("slot");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> employee = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        employee.put("id", p_id);
//                        employee.put("p_name", name);
//                        employee.put("p_age", age);
//                        employee.put("p_addr", address);
//                        employee.put("h_issue", health);
//                        employee.put("doc_specialization", docspecial);
//                        employee.put("doc_name", docname);
//                        employee.put("hos_name", hosname);
//                        employee.put("date", dates);
//                        employee.put("slot", slots);
//                        // adding contact to contact list
//                        contactList.add(employee);
//                    }
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
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            if(contactList != null) {
                setAdapter();
            }



        }

    }
}
