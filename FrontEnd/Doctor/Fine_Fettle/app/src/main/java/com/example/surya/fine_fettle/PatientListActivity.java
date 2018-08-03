package com.example.surya.fine_fettle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surya.fine_fettle.adapter.PatientListAdapter;
import com.example.surya.fine_fettle.app.AppConfig;
import com.example.surya.fine_fettle.app.Root;
import com.example.surya.fine_fettle.model.Patienthistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PatientListActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private PatientListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ImageView mImageBack;
    private ArrayList<Patienthistory> mhistoryList;
    String doc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        processBundle();
        initObjects();
        initCallbacks();
        initRecyclerView();
        getBookingHistory(doc_id);
    }
    private void processBundle() {
        Intent i = this.getIntent();

        doc_id = i.getStringExtra("Doc_id");
    }
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initCallbacks() {
        mImageBack.setOnClickListener(this);
    }

    private void initObjects() {
        mContext = this;
        mRecyclerView = findViewById(R.id.recyclerview_patientlist);
        mImageBack = findViewById(R.id.img_back);
        mLayoutManager = new LinearLayoutManager(mContext);
        mhistoryList = new ArrayList<>();
        mAdapter = new PatientListAdapter(mContext, mhistoryList);
    }

    @Override
    public void onClick(View v) {
        if (v == mImageBack) {
            onBackPressed();
        }
    }
    public void getBookingHistory(final String doc_id) {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Root.URL + AppConfig.Patient_List, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "" + response);
                Get_Customer_status(response);
                loading.dismiss();
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                } else if (error instanceof AuthFailureError) {

                } else if (error instanceof ServerError) {

                } else if (error instanceof NetworkError) {

                } else if (error instanceof ParseError) {
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("d_id", doc_id);
                Log.e("Params", "" + params);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(postRequest);

    }
    public void Get_Customer_status(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            int success = jsonObject.getInt("status");
            JSONArray jsonArray = jsonObject.getJSONArray("booking_list");
            if (success == 200) {
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject userObj22 = jsonArray.getJSONObject(j);
                    String doc_id = userObj22.getString("d_id");
                    String patient_name = userObj22.getString("p_name");

                    String patient_age = userObj22.getString("p_age");
                    String appoinment_date = userObj22.getString("date");
                    String appointment_time = userObj22.getString("slot");
                    String patient_problem = userObj22.getString("h_issue");
                    String patient_id = userObj22.getString("p_id");
                    String Doc_status = userObj22.getString("status");
                    mhistoryList.add(new Patienthistory(patient_name, patient_age, appoinment_date,appointment_time,patient_problem,patient_id,doc_id,Doc_status));
                    mAdapter.notifyDataSetChanged();


                }
            } else if (success == 201) {
                Toast.makeText(getApplicationContext(), "Please Try Again", Toast.LENGTH_SHORT).show();

            } else if (success == 500) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
