package com.fine_fettle;

/**
 * Created by Priyadharshini on 03-Jun-18.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Personreg extends AppCompatActivity {

    private static final String TAG = "personreg";

    EditText username, pass, mobile, email, rpassword;
    Button submit, cancel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personreg);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        username = findViewById(R.id.puser);
        pass = findViewById(R.id.pass);
        mobile = findViewById(R.id.pmobile);
        rpassword = findViewById(R.id.p1pass);
        email = findViewById(R.id.pemail);
        submit = findViewById(R.id.signup);
        cancel = findViewById(R.id.cancel);

         submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                onRegister(view);
                Toast.makeText(getApplicationContext(), "Hi !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onRegister(View view) {
        final String user = username.getText().toString().trim();
        final String password = pass.getText().toString().trim();
        final String phone = mobile.getText().toString().trim();
        final String mail = email.getText().toString().trim();
        final String ppass=rpassword.getText().toString().trim();

        if (TextUtils.isEmpty(user)) {
            username.setError("Enter name");
            username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            pass.setError("Enter password");
            pass.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            mobile.setError("Enter Mobile");
            mobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mail)) {
            email.setError("Enter Mail");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ppass)) {
            rpassword.setError("Enter Re-password");
            rpassword.requestFocus();
            return;
        }

        //if it passes all the validations
        HashMap<String, String> params = new HashMap<>();
        params.put("name", user);
        params.put("pass", password);
        params.put("email", mail);
        params.put("phone", phone);
        Toast.makeText(getApplicationContext(), "Hi: " + params.get("name")+"You have successfully registered", Toast.LENGTH_LONG).show();
        PostRequestHandler postRequestHandler = new PostRequestHandler(URLs.REGISTER, params);
        postRequestHandler.execute();
        employeeList(view);
    }

    public void employeeList(View view) {
        Intent intent = new Intent(Personreg.this, MainActivity.class);
        startActivity(intent);
    }
}
