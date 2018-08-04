package com.example.surya.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Doctorhome extends AppCompatActivity {
    Button info, alert;
    Button chat, history, appoint;
    TextView mTextviewTitle;
    String doc_id, Name, Mobilenumber,hosname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorhome);

        processBundle();
        info = findViewById(R.id.button);
        alert = findViewById(R.id.button1);
        chat = findViewById(R.id.button4);
        history = findViewById(R.id.button2);
        appoint = findViewById(R.id.button3);
        mTextviewTitle = findViewById(R.id.textView12);
        Intent intent = getIntent();
        final String hosname=intent.getStringExtra("hosname");

        mTextviewTitle.setText(String.format("Welcome %s", Name));
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctorhome.this, DoctorProfileActivity.class);
                intent.putExtra("Doc_id", doc_id);
                startActivity(intent);
            }
        });
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctorhome.this, DoctorAlaramActivity.class);
                intent.putExtra("Doc_id", doc_id);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doctorhome.this, DoctorMedicineActivity.class);
                i.putExtra("Doc_id", doc_id);
                i.putExtra("hosname",hosname);
                startActivity(i);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctorhome.this, PatitentBookingHistoryActivity.class);
                intent.putExtra("Doc_id", doc_id);
                startActivity(intent);
            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doctorhome.this, PatientListActivity.class);
                i.putExtra("Doc_id", doc_id);
                startActivity(i);
            }
        });

    }

    private void processBundle() {
        Intent i = this.getIntent();
        Name = i.getStringExtra("d_name");
        doc_id = i.getStringExtra("d_id");
        Mobilenumber = i.getStringExtra("d_mobile");
    }
}
