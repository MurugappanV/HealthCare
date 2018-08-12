package com.fine_fettle;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fine_fettle.adapter.HospitalAdapter;
import com.fine_fettle.models.HospitalModel;
import com.fine_fettle.models.TipsModel;
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
import java.text.DecimalFormat;
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
    private final String RATING = "rating";
    private RecyclerView mListView;
    private double lat, longg;
    Button mSortButton, mDist, mRat;
    HospitalAdapter mHospitalAdapter;
    public String id;
    private FusedLocationProviderClient mFusedLocationClient;
    private SearchView mSearchView;
    private String sortBy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeapp);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mListView = findViewById(R.id.hospital_list);
        mSortButton = findViewById(R.id.sort_button);
        mSortButton.setOnClickListener(this);
        mDist = findViewById(R.id.distance);
        mDist.setOnClickListener(this);
        mRat = findViewById(R.id.rating);
        mRat.setOnClickListener(this);
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
                    lat = location.getLatitude();
                    longg = location.getLongitude();
                    Log.d("_____LOCATION____", String.valueOf(location.getLatitude())+"////"+String.valueOf(location.getLongitude()));
                    // Logic to handle location object
                }
            }
        });
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location utilLocation = null;
        List<String> providers = manager.getProviders(false);
        for(String provider : providers){

            utilLocation = manager.getLastKnownLocation(provider);
            if(utilLocation != null) {
                lat = utilLocation.getLatitude();
                longg = utilLocation.getLongitude();
                Log.d("_____LOCATION_2___", String.valueOf(utilLocation.getLatitude())+"////"+String.valueOf(utilLocation.getLongitude()));
            }
        }
        new mymethod().execute();
        mSearchView = findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(mHospitalAdapter != null) {
                    ArrayList<HospitalModel> tipsList = new ArrayList<>();
                    for (HospitalModel moel: mTipsList) {
                        if(moel.getHospital_name().toUpperCase().contains(newText.toUpperCase())) {
                            tipsList.add(moel);
                        }
                    }
                    mHospitalAdapter.updateList(tipsList);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        mSortButton.setTextColor(Color.parseColor("#55000000"));
        mDist.setTextColor(Color.parseColor("#55000000"));
        mRat.setTextColor(Color.parseColor("#55000000"));
        if(v.getId()==R.id.sort_button){
            sortList(NAME,ASCENDING);
            mSortButton.setTextColor(Color.parseColor("#FF00ADE7"));
        }
        if(v.getId()==R.id.distance){
            sortList(DISTANCE,ASCENDING);
            mDist.setTextColor(Color.parseColor("#FF00ADE7"));
        }
        if(v.getId()==R.id.rating){
            sortList(RATING,ASCENDING);
            mRat.setTextColor(Color.parseColor("#FF00ADE7"));
        }
    }

    private void sortList(String name, int type) {
        if(mHospitalAdapter!=null){
            Collections.sort(mTipsList, new HospitalItemComparator(name, type));
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
//                    mTipsList.addAll(tipsList);
                    DecimalFormat df2 = new DecimalFormat(".##");
                    for (HospitalModel tips:mTipsList) {

                        tips.setDist(df2.format(distance(lat, longg, Double.parseDouble(tips.getLat()), Double.parseDouble(tips.getLongg()), 'K')));
                    }
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


    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
                        if ((result1.getDist() != null) && (result2.getDist() != null)) {
                            if(orderBy==ASCENDING){
                                if( Double.parseDouble(result1.getDist()) > (Double.parseDouble(result2.getDist())) ) {
                                    return 1;
                                } else if( Double.parseDouble(result1.getDist()) < (Double.parseDouble(result2.getDist())) ) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }else{
                                if( Double.parseDouble(result1.getDist()) > (Double.parseDouble(result2.getDist())) ) {
                                    return -1;
                                } else if( Double.parseDouble(result1.getDist()) < (Double.parseDouble(result2.getDist())) ) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    } else if (sortBy.equalsIgnoreCase(RATING)) {
                        if ((result1.getRating() != null) && (result2.getRating() != null)) {
                            if(orderBy==ASCENDING){
                                if( Double.parseDouble(result1.getRating()) > (Double.parseDouble(result2.getRating())) ) {
                                    return -1;
                                } else if( Double.parseDouble(result1.getRating()) < (Double.parseDouble(result2.getRating())) ) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }else{
                                if( Double.parseDouble(result1.getRating()) > (Double.parseDouble(result2.getRating())) ) {
                                    return 1;
                                } else if( Double.parseDouble(result1.getRating()) < (Double.parseDouble(result2.getRating())) ) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return 0;

        }
    }
}
