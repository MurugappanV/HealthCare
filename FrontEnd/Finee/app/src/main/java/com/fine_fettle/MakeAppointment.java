package com.fine_fettle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fine_fettle.adapter.HospitalAdapter;
import com.fine_fettle.models.HospitalModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Priyadharshini on 16-Jul-18.
 */

public class MakeAppointment extends AppCompatActivity implements View.OnClickListener {
//    EditText pername, perage, peraddr, heaissue, docspecialization, docname, hosname, dates, slots;
//    Button book;
//    private ProgressBar progressBar;
//    private Calendar newCalendar;
//    TextView tv;

    private ArrayList<HospitalModel> mTipsList = new ArrayList<>();
    private final int ASCENDING = 0;
    private final int DESCENDING = 1;
    private final String NAME = "name";
    private final String DISTANCE = "distance";
    private RecyclerView mListView;
    Button mSortButton;
    HospitalAdapter mHospitalAdapter;
    public String id;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeapp);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mSortButton = findViewById(R.id.sort_button);
        mListView = findViewById(R.id.hospital_list);
        new mymethod().execute();
        mSortButton.setOnClickListener(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    Log.d("__________________LOCATION_______________", String.valueOf(location.getLatitude())+"////"+String.valueOf(location.getLongitude()));
                    // Logic to handle location object
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sort_button){
            sortList();
        }
    }

    private void sortList() {
        if(mHospitalAdapter!=null){
            Collections.sort(mTipsList, new HospitalItemComparator(NAME,ASCENDING));
            mHospitalAdapter.updateList(mTipsList);
        }
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
        mHospitalAdapter = new HospitalAdapter(this,R.layout.hospital_item_layout,mTipsList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(mHospitalAdapter);
    }

    private class HospitalItemComparator implements Comparator< HospitalModel> {
        private final String sortBy;
        private final int orderBy;

        HospitalItemComparator(String sortBy,int orderBy) {
            this.sortBy = sortBy;
            this.orderBy = orderBy;
        }

        @Override
        public int compare(HospitalModel result1, HospitalModel result2) {
            if (result1 != null && result2 != null) {
                try {
                    if (sortBy.equalsIgnoreCase(NAME)) {
                        if ((result1.getHospital_name() != null) && (result2.getHospital_name() != null)) {
                            if(orderBy==ASCENDING){
                                return result1.getHospital_name().compareTo(result2.getHospital_name());
                            }else{
                                return -(result1.getHospital_name().compareTo(result2.getHospital_name()));
                            }
                        }
                    } else if (sortBy.equalsIgnoreCase(DISTANCE)) {

                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return 0;

        }
    }
}
