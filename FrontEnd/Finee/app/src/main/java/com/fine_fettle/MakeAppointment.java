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

//        tv=findViewById(R.id.tvView1);
//        Intent intent = getIntent();
//       final String id=intent.getStringExtra("id");
//       System.out.println(id);
//       tv.setText(id);
//        pername = findViewById(R.id.p_name);
//        perage = findViewById(R.id.p_age);
//        peraddr = findViewById(R.id.p_addr);
//        heaissue = findViewById(R.id.h_issue);
//        docspecialization = findViewById(R.id.doc_specializtion);
//        docname = findViewById(R.id.doc_name);
//        hosname = findViewById(R.id.hos_name);
//        dates = findViewById(R.id.date);
//        slots = findViewById(R.id.slot);
//        book = findViewById(R.id.book);
//        progressBar = findViewById(R.id.progressBar);
//       newCalendar = Calendar.getInstance();
//        dates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mFromdatePicker();
//            }
//        });
//
//        book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //if user pressed on button register
//                //here we will register the user to server
//                onBook(view);
//                Toast.makeText(getApplicationContext(), "Hi !", Toast.LENGTH_SHORT).show();
//            }
//        });


//    private void mFromdatePicker() {
//        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(MakeAppointment.this, new android.app.DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                dates.setText(df.format(newDate.getTime()));
//            }
//        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//        fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(this, R.color.White));
//        fromDatePickerDialog.show();
//
//    }
//
//
//    private void onBook(View view) {
//        final String p_name = pername.getText().toString().trim();
//        final String p_age = perage.getText().toString().trim();
//        final String p_addr = peraddr.getText().toString().trim();
//        final String h_issue = heaissue.getText().toString().trim();
//        final String doc_specialization = docspecialization.getText().toString().trim();
//        final String doc_name = docname.getText().toString().trim();
//        final String hos_name = hosname.getText().toString().trim();
//        final String date = dates.getText().toString().trim();
//        final String slot = slots.getText().toString().trim();
//        if (TextUtils.isEmpty(p_name)) {
//            pername.setError("Enter person name");
//            pername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(p_age)) {
//            perage.setError("Enter person age");
//            perage.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(p_addr)) {
//            peraddr.setError("Enter person address");
//            peraddr.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(h_issue)) {
//            heaissue.setError("Enter person health issue");
//            heaissue.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(doc_specialization)) {
//            docspecialization.setError("Enter Doctor Specialization");
//            docspecialization.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(doc_name)) {
//            docname.setError("Enter Doctor name");
//            docname.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(hos_name)) {
//            hosname.setError("Enter Hospital Name");
//            hosname.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(date)) {
//            dates.setError("Enter Date");
//            dates.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(slot)) {
//            slots.setError("Enter Slot");
//            slots.requestFocus();
//            return;
//        }
//        //if it passes all the validations
//        HashMap<String, String> params = new HashMap<>();
//        params.put("u_id", tv.getText().toString());
//        params.put("age", p_age);
//        params.put("addr", p_addr);
//        params.put("h_issue", h_issue);
//        params.put("d_special", doc_specialization);
//        params.put("d_name", doc_name);
//        params.put("h_name", hos_name);
//        params.put("date", date);
//        params.put("slot", slot);
//        params.put("name",p_name);
//        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.URL_REGISTER, params);
//        postRequestHandler.execute();
//        Toast.makeText(getApplicationContext(), "Appoinment is done for: " + params.get("name"), Toast.LENGTH_LONG).show();
//        employeeList(view);
//    }

//    public void employeeList(View view) {
//        Intent intent = new Intent(MakeAppointment.this,ViewAppointment.class);
//        intent.putExtra("id", tv.getText().toString());
//        startActivity(intent);
//    }