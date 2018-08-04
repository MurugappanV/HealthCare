package com.example.surya.fine_fettle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.surya.fine_fettle.app.AppConfig;
import com.example.surya.fine_fettle.app.Root;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DoctorProfileActivity extends AppCompatActivity {


    String doc_id;
    private TextView mName, mMobile, mDocId, mDochospitalName, mgender, mdob, mEmail,dspl;
    private Context mContext;
    private ImageView mImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        processBundle();
        initObjects();
    }

    private void processBundle() {
        Intent i = this.getIntent();
        doc_id = i.getStringExtra("Doc_id");
    }

    private void initObjects() {
        mContext = this;
        mName = findViewById(R.id.usr_name);
        mMobile = findViewById(R.id.usr_phone);
        mDocId = findViewById(R.id.usr_id);
        mImageBack = findViewById(R.id.img_back);
        mDochospitalName = findViewById(R.id.usr_doc_clinic);
        mgender = findViewById(R.id.usr_doc_gender);
        mEmail = findViewById(R.id.usr_email);
        mdob = findViewById(R.id.usr_doc_dob);
        dspl = findViewById(R.id.usr_special);
        getProfile(doc_id);

        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DoctorProfileActivity.this,Doctorhome.class);
                intent.putExtra("hosname", mDochospitalName.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void getProfile(final String doc_id) {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Root.URL + AppConfig.Profile, new Response.Listener<String>() {
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
            if (success == 200) {

                String doc_id = jsonObject.getString("d_id");
                String name = jsonObject.getString("d_name");
                String email = jsonObject.getString("d_email");
                String mobilenumber = jsonObject.getString("d_mobile");
                String gender = jsonObject.getString("gender");
                String dob = jsonObject.getString("dob");
                String clinic_name = jsonObject.getString("h_name");
                String d_special = jsonObject.getString("d_specialization");
                mName.setText(name);
                mMobile.setText(mobilenumber);
                mDocId.setText(doc_id);
                mDochospitalName.setText(clinic_name);
                mgender.setText(gender);
                mdob.setText(dob);
                mEmail.setText(email);
                dspl.setText(d_special);

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
