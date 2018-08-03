package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class BloodRequest extends AppCompatActivity {
    TextView tvView, tv;

    Button req;
    Spinner spinner;
    RatingBar ratingBar;
    String spin, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodreq);
        tvView = findViewById(R.id.tvView);
        tv = findViewById(R.id.tvView1);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("user");
        final String id = intent.getStringExtra("id");
        tvView.setText(username);
        tv.setText(id);
        addListenerOnSpinnerItemSelection();

    }

    private void addListenerOnSpinnerItemSelection() {
        spinner = findViewById(R.id.blood);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        ratingBar = findViewById(R.id.rating);
        req = findViewById(R.id.request);

        req.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodRequest.this, BloodView.class);
                rate = String.valueOf(ratingBar.getRating());
                intent.putExtra("user", tvView.getText().toString());
                intent.putExtra("id", tv.getText().toString());
                submitReq(view);
                startActivity(intent);
            }
        });
    }

    private void submitReq(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("u_name", tvView.getText().toString());
        params.put("id", tv.getText().toString());
        params.put("req_blood_grp", spin);
        params.put("rate", rate);
        System.out.println("NAME:" + tvView.getText().toString() + "ID:" + tv.getText().toString() + "BLOOD:" + spin + "RATE:" + rate);
        Toast.makeText(getApplicationContext(), "Hi: " + params.get("u_name") + "Your Blood Request is sent", Toast.LENGTH_LONG).show();
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.BLOODREQ, params);
        postRequestHandler.execute();
        employeeList(view);
    }

    public void employeeList(View view) {
        Intent intent = new Intent(BloodRequest.this, BloodView.class);
        intent.putExtra("user", tvView.getText().toString());
        intent.putExtra("id", tv.getText().toString());
        startActivity(intent);
    }

    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spin = String.valueOf(spinner.getSelectedItem());
            Toast.makeText(BloodRequest.this,
                    "OnClickListener : " +
                            "\nSpinner 1 : " + String.valueOf(spinner.getSelectedItem()),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



}
