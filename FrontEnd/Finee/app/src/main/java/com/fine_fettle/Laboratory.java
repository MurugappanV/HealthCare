package com.fine_fettle;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;

import com.fine_fettle.adapter.DoctorAdapter;
import com.fine_fettle.models.DoctorModal;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Priyadharshini on 05-Jul-18.
 */

public class Laboratory extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<DoctorModal> mTipsList = new ArrayList<>();
    private RecyclerView mListView;
    public String id, hospital_name;
    private final int ASCENDING = 0;
    private final int DESCENDING = 1;
    private final String NAME = "name";
    private final String EXP = "exp";
    private final String RATING = "rating";
    Button mSortButton, mDist, mRat;
    DoctorAdapter adapter;
    private android.support.v7.widget.SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratories);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        hospital_name=intent.getStringExtra("hospital_name");
        mListView = findViewById(R.id.doctor_list);
        new mymethod().execute();
        mSortButton = findViewById(R.id.sort_button);
        mSortButton.setOnClickListener(this);
        mDist = findViewById(R.id.distance);
        mDist.setOnClickListener(this);
        mRat = findViewById(R.id.rating);
        mRat.setOnClickListener(this);
        mSearchView = findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter != null) {
                    ArrayList<DoctorModal> tipsList = new ArrayList<>();
                    for (DoctorModal moel: mTipsList) {
                        if(moel.getName().toUpperCase().contains(newText.toUpperCase())) {
                            tipsList.add(moel);
                        }
                    }
                    adapter.updateList(tipsList);
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
            sortList(EXP,ASCENDING);
            mDist.setTextColor(Color.parseColor("#FF00ADE7"));
        }
        if(v.getId()==R.id.rating){
            sortList(RATING,ASCENDING);
            mRat.setTextColor(Color.parseColor("#FF00ADE7"));
        }
    }

    private void sortList(String name, int type) {
        if(adapter!=null){
            Collections.sort(mTipsList, new DoctorItemComparator(name, type));
            adapter.updateList(mTipsList);
        }
    }

    public class mymethod extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {
                URL url;
                if(hospital_name != null) {
                    url = new URL("http://35.204.108.96/doctor_select.php");

                } else {
                    url = new URL("http://35.204.108.96/all_doctor_select.php");

                }

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                if(hospital_name != null) {
                    JSONObject postDataParams = new JSONObject();
                    postDataParams.put("hos_name", hospital_name);
                    writer.write(getPostDataString(postDataParams));
                }
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
                Type type = new TypeToken<List<DoctorModal>>(){}.getType();
                ArrayList<DoctorModal> tipsList = gson.fromJson(jsonObj.getString("result"), type);
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
         adapter = new DoctorAdapter(this,R.layout.doctor_item_layout,mTipsList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(adapter);
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            System.out.println(result);

        }
        return result.toString();
    }

    private class DoctorItemComparator implements Comparator< DoctorModal> {
        private final String sortBy;
        private final int orderBy;

        DoctorItemComparator(String sortBy,int orderBy) {
            this.sortBy = sortBy;
            this.orderBy = orderBy;
        }

        @Override
        public int compare(DoctorModal result1, DoctorModal result2) {
            if (result1 != null && result2 != null) {
                try {
                    if (sortBy.equalsIgnoreCase(NAME)) {
                        if ((result1.getName() != null) && (result2.getName() != null)) {
                            if(orderBy==ASCENDING){
                                return result1.getName().compareTo(result2.getName());
                            }else{
                                return -(result1.getName().compareTo(result2.getName()));
                            }
                        }
                    } else if (sortBy.equalsIgnoreCase(EXP)) {
                        if ((result1.getExp() != null) && (result2.getExp() != null)) {
                            if(orderBy==ASCENDING){
                                if( Double.parseDouble(result1.getExp()) > (Double.parseDouble(result2.getExp())) ) {
                                    return -1;
                                } else if( Double.parseDouble(result1.getExp()) < (Double.parseDouble(result2.getExp())) ) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }else{
                                if( Double.parseDouble(result1.getExp()) > (Double.parseDouble(result2.getExp())) ) {
                                    return 1;
                                } else if( Double.parseDouble(result1.getExp()) < (Double.parseDouble(result2.getExp())) ) {
                                    return -1;
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
