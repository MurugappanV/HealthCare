package com.fine_fettle;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Priyadharshini on 05-Jun-18.
 */

public class PersonInfo extends AppCompatActivity {
    EditText firstname, lastname, age, dob, bgroup, address, city, pincode;
    RadioGroup genderRadioGroup;
    Button info;
    ProgressDialog progressDialog;
    private Calendar newCalendar;
    private static final String TAG = "PersonInfo";
    TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        tvView =findViewById(R.id.tvView);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("user");
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        tvView.setText(username);
        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        age = findViewById(R.id.age);
        genderRadioGroup =findViewById(R.id.gender_radio_group);
        dob = findViewById(R.id.dob);
        bgroup = findViewById(R.id.bgroup);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pincode);
        info = findViewById(R.id.send);
        newCalendar = Calendar.getInstance();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFromdatePicker();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitForm(view);
                    }
                });

    }
    private void mFromdatePicker() {
        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(PersonInfo.this, new android.app.DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dob.setText(df.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(this, R.color.White));
        fromDatePickerDialog.show();

    }


    private void submitForm(View view) {
        final String first = firstname.getText().toString().trim();
        final String last = lastname.getText().toString().trim();
        final String p_age = age.getText().toString().trim();
        final String date = dob.getText().toString().trim();
        final String blood = bgroup.getText().toString().trim();
        final String addr = address.getText().toString().trim();
        final String p_city = city.getText().toString().trim();
        final String pin = pincode.getText().toString().trim();
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();

        String gender;
        if(selectedId == R.id.radioButton2) {
            gender = "Female";
        }
        else if(selectedId == R.id.radioButton1)
        {
            gender = "Male";
        }
        else
        {
            gender = "others";
        }



        if (TextUtils.isEmpty(first)) {
            firstname.setError("Enter Firstname");
            firstname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(last)) {
            lastname.setError("Enter Lastname");
            lastname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(p_age)) {
            age.setError("Enter Age");
            age.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(date)) {
            dob.setError("Enter DOB");
            dob.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(blood)) {
            bgroup.setError("Enter Blood Group");
            bgroup.requestFocus();
            return;
        } if (TextUtils.isEmpty(addr)) {
            address.setError("Enter Address");
            address.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(p_city)) {
            city.setError("Enter City");
            city.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pin)) {
            pincode.setError("Enter Pincode");
            pincode.requestFocus();
            return;
        }


        //if it passes all the validations
        HashMap<String, String> params = new HashMap<>();
        params.put("user", tvView.getText().toString());
        params.put("firstname", first);
        params.put("lastname", last);
        params.put("age", p_age);
        params.put("gender", gender);
        params.put("dob", date);
        params.put("bgroup", blood);
        params.put("address", addr);
        params.put("city", p_city);
        params.put("pin", pin);
        Toast.makeText(getApplicationContext(), "Hi: " + params.get("user") + "You have successfully updated you profile", Toast.LENGTH_LONG).show();
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.PROFILE, params);
        postRequestHandler.execute();
        employeeList(view);
    }

    public void employeeList(View view) {
        Intent intent = new Intent(PersonInfo.this, PersonInfoList.class);
        intent.putExtra("user", tvView.getText().toString());
        startActivity(intent);
    }




}

