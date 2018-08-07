package com.example.surya.fine_fettle;

import android.accessibilityservice.GestureDescription;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surya.fine_fettle.app.AppConfig;
import com.example.surya.fine_fettle.app.AppController;
import com.example.surya.fine_fettle.app.Root;
import com.example.surya.fine_fettle.helper.SQLiteHandler;
import com.example.surya.fine_fettle.helper.SessionManager;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


public class Doctorreg extends AppCompatActivity {
    //Declaring views
    private Button buttonChoose,buttonUpload;
    private EditText editText;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    private static final String TAG = "Doctorreg";
    ProgressDialog progressDialog;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    Spinner spinner;
    private EditText name1, hospital1, user1, pass1, email1, mobile1, dob1, doctor_id,age,exp,blood,addr,city,pin;
    private Button signup, cancel;
    private RadioGroup genderRadioGroup;
    private RadioButton male,female;
    String gender;
    String d_special;
    private Calendar newCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorreg);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        //requestStoragePermission();


        name1 = findViewById(R.id.dname);
        hospital1 = findViewById(R.id.dhos);
        user1 = findViewById(R.id.d1user);
        pass1 = findViewById(R.id.d1pass);
        email1 = findViewById(R.id.demail);
        mobile1 = findViewById(R.id.dmob);
        dob1 = findViewById(R.id.ddate);
        doctor_id = findViewById(R.id.doc_id);
        male = findViewById(R.id.radioButton1);
        female = findViewById(R.id.radioButton2);
        age = findViewById(R.id.dage);
        exp = findViewById(R.id.dexp);
        blood = findViewById(R.id.dblood);
        addr = findViewById(R.id.daddr);
        city = findViewById(R.id.dcity);
        pin = findViewById(R.id.dpincode);
        newCalendar = Calendar.getInstance();
        spinner = findViewById(R.id.dspecial);
        signup = findViewById(R.id.dsin);
        cancel = findViewById(R.id.dcan);
        addListenerOnSpinnerItemSelection();
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        editText = (EditText) findViewById(R.id.editTextName);
        //Setting clicklistener
       buttonUpload=findViewById(R.id.buttonUpload);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFromdatePicker();
            }
        });
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        gender = "male";
                        break;
                    case R.id.radioButton2:
                        gender = "female";
                        break;
                }
            }
        });
        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Doctorreg.this,
                    Doctorhome.class);
            startActivity(intent);
            finish();
        }
    }
        private void addListenerOnSpinnerItemSelection() {
            spinner = findViewById(R.id.dspecial);
            spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = name1.getText().toString().trim();
                    String hospitalname = hospital1.getText().toString().trim();
                    String username = user1.getText().toString().trim();
                    String password = pass1.getText().toString().trim();
                    String email = email1.getText().toString().trim();
                    String mobile = mobile1.getText().toString().trim();
                    String dob = dob1.getText().toString().trim();
                    String doc_id = doctor_id.getText().toString().trim();
                    String d_age = age.getText().toString().trim();
                    String d_exp = exp.getText().toString().trim();
                    String d_blood = blood.getText().toString().trim();
                    String d_addr = addr.getText().toString().trim();
                    String d_city = city.getText().toString().trim();
                    String d_pin = pin.getText().toString().trim();
                    System.out.println("name"+name+"hos"+hospitalname+"uname"+username+"pass"+password+"email"+email+"mob"+mobile+"dob"+dob+"addr"+d_addr+
                            "docid"+doc_id+"dage"+d_age+"dexp"+d_exp+"blood"+d_blood+"city"+d_city+"pin"+d_pin);


                    if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !hospitalname.isEmpty() && !username.isEmpty() && !mobile.isEmpty() && !dob.isEmpty()
                            && !d_age.isEmpty() && !d_exp.isEmpty() && !d_blood.isEmpty() && !d_addr.isEmpty() &&!doc_id.isEmpty()&& !d_city.isEmpty() && !d_pin.isEmpty()) {
                        registerUser(name, hospitalname, username, password, email, mobile, gender, dob, doc_id, d_age, d_exp, d_addr, d_city, d_pin, d_blood);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Please enter your details!", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    onBackPressed();
                    finish();
                }
            });

        }
   /* public void uploadMultipart() {
        //getting name for the image
        String name = editText.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, AppConfig.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadMultipart();
        }
    }
*/
    private void mFromdatePicker() {
        android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(Doctorreg.this, new android.app.DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dob1.setText(df.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(this, R.color.White));
        fromDatePickerDialog.show();

    }


    public void  registerUser(final String name, final String hospitalname, final String username,
                              final String paswword, final String email, final String mobile,
                              final String gender,final String  dob,final String doc_id,final String d_age,
                              final String d_exp,final String d_addr,final String d_city,
                              final String d_pin,final String d_blood)
    {
        showDialog();
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                Root.URL+ AppConfig.Register, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                hideDialog();

                Get_Customer_status(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
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
                params.put("h_id",doc_id);
                params.put("d_name",name);
                params.put("d_hospital",hospitalname);
                params.put("username",username);
                params.put("password",paswword);
                params.put("d_email",email);
                params.put("gender",gender);
                params.put("d_mobile",mobile);
                params.put("dob",dob);
                params.put("age",d_age);
                params.put("d_specialization",d_special);
                params.put("d_experience",d_exp);
                params.put("bloodgroup",d_blood);
                params.put("address",d_addr);
                params.put("city",d_city);
                params.put("pincode",d_pin);

                Log.e("Params",""+params);
                return params;
            }
        };
        // Adding request to request queue
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(postRequest);

        //  AppController.getInstance().addToRequestQueue(postRequest);

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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            d_special = String.valueOf(spinner.getSelectedItem());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}