package com.fine_fettle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fine_fettle.adapter.TipsAdapter;
import com.fine_fettle.adapter.ViewAppoinmentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Priyadharshini on 05-Jun-18.
 */

public class Appointment extends AppCompatActivity {
    Button makeapp;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList;
    private RecyclerView mListView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        mListView = findViewById(R.id.tips_list);
        new AppointmentGetter().execute();
        makeapp = findViewById(R.id.make);
        makeapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Appointment.this, MakeAppointment.class);
                intent.putExtra("id", id);
//                Toast.makeText(getApplicationContext(), "Id" + id,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private void setAdapter(){
        ViewAppoinmentAdapter adapter = new ViewAppoinmentAdapter(this,R.layout.appointment_item_layout,contactList);
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
            pDialog = new ProgressDialog(Appointment.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JsonParser sh = new JsonParser();
            String url= "http://35.204.108.96/viewallapp.php?ID="+id;
            String jsonStr = sh.convertJson(url);
            System.out.println(url);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray employeeArray = jsonObj.getJSONArray("response");

                    // looping through All Contacts
                    contactList = new ArrayList<>();
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
                        String status = c.getString("status");

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
                        employee.put("status", status);
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

// viewapp, rescheduleapp, cancelapp, remainderapp

//        tv=findViewById(R.id.tvView);
//        tv.setText(id);
//        viewapp = findViewById(R.id.view);
//        rescheduleapp = findViewById(R.id.reschedule);
//        cancelapp = findViewById(R.id.cancel);
//        remainderapp = findViewById(R.id.remainder);


//        viewapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Appointment.this, ViewAppointment.class);
//                intent.putExtra("id", tv.getText().toString());
//               Toast.makeText(getApplicationContext(), "Id" + id,
//                        Toast.LENGTH_LONG).show();
//                startActivity(intent);
//            }
//        });
//        rescheduleapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Appointment.this, RescheduleAppointment.class);
//                startActivity(intent);
//            }
//        });


//    TextView tv;
