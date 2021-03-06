package com.fine_fettle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.fine_fettle.adapter.TipsAdapter;
import com.fine_fettle.models.TipsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
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
import java.util.List;
import java.util.function.Consumer;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Priyadharshini on 05-Jul-18.
 */

public class Tips extends AppCompatActivity{
    private ArrayList<TipsModel> mTipsList = new ArrayList<>();
    private RecyclerView mListView;
    private SearchView mSearchView;
    private TipsAdapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_lyt);
        mListView = findViewById(R.id.tips_list);
        mSearchView = findViewById(R.id.search);
        new mymethod().execute();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter != null) {
                     ArrayList<TipsModel> tipsList = new ArrayList<>();
                    for (TipsModel moel: mTipsList) {
                        if(moel.getmTitle().toUpperCase().contains(newText.toUpperCase())) {
                            tipsList.add(moel);
                        }
                    }
                    adapter.updateList(tipsList);
                }
                return false;
            }
        });
    }

    private void setAdapter(){
        adapter = new TipsAdapter(this,R.layout.tips_item_lyt,mTipsList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(adapter);
    }

    public class mymethod extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL("http://35.204.108.96/all_tips.php");
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
                Type type = new TypeToken<List<TipsModel>>(){}.getType();
                ArrayList<TipsModel> tipsList = gson.fromJson(jsonObj.getString("result"), type);
                if(tipsList!=null){
                    mTipsList = tipsList;
//                    mTipsList.addAll(tipsList);
//                    mTipsList.addAll(tipsList);
                    setAdapter();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }


    }
}



//    public void prepareTipsList(){
//        //TipsModel tipsModel = new TipsModel();
////        tipsList.add(new TipsModel("Bharadvajasana ","This gentle twist is a tonic for the spine and the abdominal organs",R.drawable.ambulance_icon));
////        tipsList.add(new TipsModel("Padangusthasana ","This pose gently lengthens and strengthens even stubbornly tight hamstrings",R.drawable.appoinment_icon));
////        tipsList.add(new TipsModel("Paripurna Navasana ","An ab and deep hip flexor strengthener, Paripurna Navasana requires you to balance on " +
////                "the tripod of your sitting bones and tailbone",R.drawable.addicon));
////        tipsList.add(new TipsModel("Baddha Konasana ","One of the best hip openers around, Bound Angle " +
////                "Pose counteracts chair- and cardio-crunched hips.",R.drawable.blood_bank));
////        tipsList.add(new TipsModel("Dhanurasana ","Bend back into the shape of a bow to feel " +
////                "energetically locked, loaded, and ready to take aim",R.drawable.bmi_icon));
////        tipsList.add(new TipsModel("Setu Bandha Sarvangasana ","Bridge Pose can be whatever " +
////                "you need—energizing, rejuvenating, or luxuriously restorative",R.drawable.chat_icon));
////        tipsList.add(new TipsModel("Ustrasana ","Bump up your energy by bending back into Camel Pose.",R.drawable.logo));
////        tipsList.add(new TipsModel("Marjaryasana ","This pose provides a gentle massage to the spine and belly organs.",R.drawable.appointment));
//
//
//    }