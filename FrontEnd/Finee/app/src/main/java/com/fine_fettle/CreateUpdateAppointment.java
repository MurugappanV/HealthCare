package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by murugappanviswanathan on 06/08/18.
 */

public class CreateUpdateAppointment extends AppCompatActivity {
    EditText pername, perage, peraddr, heaissue, docname, hosname, dates, slots;
    Button book;
    private ProgressBar progressBar;
    private Calendar newCalendar;
     String id,d_id,hospital_name, doctor_name, doctor_spec;
//    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_app);
//        tv=findViewById(R.id.tvView1);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        d_id=intent.getStringExtra("d_id");
        hospital_name=intent.getStringExtra("hospital_name");
        doctor_name=intent.getStringExtra("doctor_name");
        doctor_spec=intent.getStringExtra("doctor_spec");

        pername = findViewById(R.id.name);
        perage = findViewById(R.id.age);
        peraddr = findViewById(R.id.address);
        heaissue = findViewById(R.id.issue);
//        docspecialization = findViewById(R.id.specialisation);
//        docname = findViewById(R.id.doctorname);
//        hosname = findViewById(R.id.hospitalname);
        dates = findViewById(R.id.date);
        slots = findViewById(R.id.slot);
        book = findViewById(R.id.book);
//        progressBar = findViewById(R.id.progressBar);
        newCalendar = Calendar.getInstance();
        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFromdatePicker();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                onBook(view);
//                Toast.makeText(getApplicationContext(), "Hi !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mFromdatePicker() {
        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(CreateUpdateAppointment.this, new android.app.DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dates.setText(df.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(this, R.color.White));
        fromDatePickerDialog.show();

    }



    private void onBook(View view) {
        final String p_name = pername.getText().toString().trim();
        final String p_age = perage.getText().toString().trim();
        final String p_addr = peraddr.getText().toString().trim();
        final String h_issue = heaissue.getText().toString().trim();
//        final String doc_specialization = docspecialization.getText().toString().trim();
//        final String doc_name = docname.getText().toString().trim();
//        final String hos_name = hosname.getText().toString().trim();
        final String date = dates.getText().toString().trim();
        final String slot = slots.getText().toString().trim();
        if (TextUtils.isEmpty(p_name)) {
            pername.setError("Enter person name");
            pername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(p_age)) {
            perage.setError("Enter person age");
            perage.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(p_addr)) {
            peraddr.setError("Enter person address");
            peraddr.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(h_issue)) {
            heaissue.setError("Enter person health issue");
            heaissue.requestFocus();
            return;
        }
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
        if (TextUtils.isEmpty(date)) {
            dates.setError("Enter Date");
            dates.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(slot)) {
            slots.setError("Enter Slot");
            slots.requestFocus();
            return;
        }
        //if it passes all the validations
        HashMap<String, String> params = new HashMap<>();
        params.put("u_id", id);
        params.put("d_id", d_id);
        params.put("age", p_age);
        params.put("addr", p_addr);
        params.put("h_issue", h_issue);
        params.put("d_special", doctor_spec);
        params.put("d_name", doctor_name);
        params.put("h_name", hospital_name);
        params.put("date", date);
        params.put("slot", slot);
        params.put("name",p_name);
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.URL_REGISTER, params);
        postRequestHandler.execute();
        Toast.makeText(getApplicationContext(), "Appoinment is done for: " + params.get("name"), Toast.LENGTH_LONG).show();
        employeeList(view);
    }

    public void employeeList(View view) {
        Intent intent = new Intent(CreateUpdateAppointment.this,Appointment.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
