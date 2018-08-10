package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Priyadharshini on 05-Jul-18.
 */

public class Need extends AppCompatActivity{

    EditText phone, hosp, doct, oper, amont, name, dates, msg;
    Button book;
    private ProgressBar progressBar;
    private Calendar newCalendar;
    String id,d_id,hospital_name, doctor_name, doctor_spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        name = findViewById(R.id.pname);
        phone = findViewById(R.id.phone);
        hosp = findViewById(R.id.phospital);
        doct = findViewById(R.id.pdoctor);
        oper = findViewById(R.id.poperation);
        amont = findViewById(R.id.opamount);
        msg = findViewById(R.id.msg);

        newCalendar = Calendar.getInstance();
        book = findViewById(R.id.send);
        dates = findViewById(R.id.date);
        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFromdatePicker();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBook(view);
            }
        });
    }

    private void onBook(View view) {
        final String nam = name.getText().toString().trim();
        final String pho = phone.getText().toString().trim();
        final String hos = hosp.getText().toString().trim();
        final String doc = doct.getText().toString().trim();
        final String dat = dates.getText().toString().trim();
        final String opr = oper.getText().toString().trim();
        final String amt = amont.getText().toString().trim();
        final String mg = msg.getText().toString().trim();
        if (TextUtils.isEmpty(nam)) {
            name.setError("Enter patient name");
            name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pho)) {
            phone.setError("Enter contact phone number");
            phone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(hos)) {
            hosp.setError("Enter hospital name");
            hosp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(doc)) {
            doct.setError("Enter doctor name");
            doct.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dat)) {
            dates.setError("Enter operation date");
            dates.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(opr)) {
            oper.setError("Enter operation name");
            oper.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(amt)) {
            amont.setError("Enter amount");
            amont.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mg)) {
            msg.setError("Enter message");
            msg.requestFocus();
            return;
        }
        //if it passes all the validations
        HashMap<String, String> params = new HashMap<>();
        params.put("u_id", id);
        params.put("p_id", id);
        params.put("d_id", 1+"");
        params.put("operation", opr);
        params.put("p_name", nam);
        params.put("h_name", hos);
        params.put("d_name", doc);
        params.put("o_fees", amt);
        params.put("o_date",dat);
        params.put("phone",pho);
        params.put("message",mg);
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.HELP_REGISTER, params);
        postRequestHandler.execute();
        Toast.makeText(getApplicationContext(), "Added help request for: " + params.get("p_name"), Toast.LENGTH_LONG).show();
        name.setText("");
         phone.setText("");
         hosp.setText("");
        doct.setText("");
         dates.setText("");
         oper.setText("");
         amont.setText("");
        msg.setText("");
//        employeeList(view);
    }

//    public void employeeList(View view) {
//        Intent intent = new Intent(Need.this,Personhome.class);
//        intent.putExtra("id", id);
//        startActivity(intent);
//        finish();
//    }



    private void mFromdatePicker() {
        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(Need.this, new android.app.DatePickerDialog.OnDateSetListener() {
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
}
