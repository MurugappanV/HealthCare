package com.example.surya.fine_fettle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.example.surya.fine_fettle.app.AppConfig;
import com.example.surya.fine_fettle.app.Root;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DoctorMedicineActivity extends AppCompatActivity {



    private EditText mPatientName,mPatientAge,mAppointmentDate,mPatientStage, mPatientMedicine,mDisease,mPatientNum;
    private Calendar newCalendar;
    private Button mSubmit;
    private ImageView mImageBack;
    String doc_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_medicine);
            processBundle();
        mPatientName= findViewById(R.id.edit_patientname);
        mPatientAge= findViewById(R.id.edit_patientage);
        mAppointmentDate= findViewById(R.id.edit_date);
        mPatientStage= findViewById(R.id.edit_stage);
        mPatientNum=findViewById(R.id.edit_num);
        mImageBack = findViewById(R.id.img_back);
        mPatientMedicine= findViewById(R.id.edit_Prescription);
        mDisease=findViewById(R.id.edit_disease);
        mSubmit= findViewById(R.id.btn_Submit);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        newCalendar = Calendar.getInstance();
        Intent intent = getIntent();
        final String hosname=intent.getStringExtra("hosname");
        mAppointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFromdatePicker();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=   mPatientName.getText().toString().trim();
                String Age=   mPatientAge.getText().toString().trim();
                String date=   mAppointmentDate.getText().toString().trim();
                String num=mPatientNum.getText().toString().trim();
                String Stage=   mPatientStage.getText().toString().trim();
                String medicine=   mPatientMedicine.getText().toString().trim();
                String disease=mDisease.getText().toString().trim();
                if(name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
                }
                else if(Age.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter Age",Toast.LENGTH_SHORT).show();
                }
                else if(date.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter Date",Toast.LENGTH_SHORT).show();
                }
                else if(disease.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter disease",Toast.LENGTH_SHORT).show();
                }
                else if(Stage.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter Stage",Toast.LENGTH_SHORT).show();
                }
                else if(medicine.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter medicine",Toast.LENGTH_SHORT).show();
                }
                else if(num.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter medicine",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Prescription(name,Age,date,num,disease,Stage,medicine,doc_id,hosname);
                }
            }
        });
    }

    private void processBundle() {
        Intent i = this.getIntent();

        doc_id = i.getStringExtra("Doc_id");
    }

    private void mFromdatePicker() {
        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(DoctorMedicineActivity.this, new android.app.DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                mAppointmentDate.setText(df.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(this, R.color.White));
        fromDatePickerDialog.show();

    }

    public void  Prescription(final String name,final String age,final String date,final String num,final String disease,final String stage,final String medicine,final String doc_id,final String hosname)
    {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);

        StringRequest postRequest = new StringRequest(Request.Method.POST,
                Root.URL+ AppConfig.Patient_Prescription, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                loading.dismiss();

                Get_Customer_status(response);
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
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
                params.put("p_name",name);
                params.put("p_id",age);
                params.put("entry_date",date);
                params.put("mobile",num);
                params.put("p_disease",disease);
                params.put("p_condition",stage);
                params.put("prescription",medicine);
                params.put("d_id",doc_id);
                params.put("h_name",hosname);
                Log.e("Params",""+params);
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
            String message = jsonObject.getString("message");
            if (success == 200) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                onBackPressed();
                finish();
            }
            else  if (success == 202){
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
            else  if (success == 500){
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
