package com.example.surya.fine_fettle.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.surya.fine_fettle.R;
import com.example.surya.fine_fettle.app.AppConfig;
import com.example.surya.fine_fettle.app.Root;
import com.example.surya.fine_fettle.holder.PatientListHolder;
import com.example.surya.fine_fettle.model.Patienthistory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PatientListAdapter extends RecyclerView.Adapter<PatientListHolder> {

    protected AlertDialog.Builder build, build1;
    AlertDialog alertDialog;
    private Context mContext;
    private ArrayList<Patienthistory> mMyAccount;
    private AlertDialog dailog, dialog1;

    public PatientListAdapter(Context context, ArrayList<Patienthistory> myPhotoList) {
        mContext = context;
        mMyAccount = myPhotoList;
        build = new AlertDialog.Builder(mContext);
        build1 = new AlertDialog.Builder(mContext);
    }

    @NonNull
    @Override
    public PatientListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PatientListHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient_list, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientListHolder holder, final int position) {
        final Patienthistory myaccount = mMyAccount.get(position);
        holder.mTextViewName.setText(myaccount.getmName());
        holder.mTextViewAge.setText(myaccount.getmAge());
        holder.mTextViewdate.setText(myaccount.getmDate());
        holder.mTextViewTime.setText(myaccount.getmTime());
        holder.mTextViewProblem.setText(myaccount.getmProblem());


        if (myaccount.getmDoc_Status().equals("0")) {
            holder.mLayout_accept.setVisibility(View.VISIBLE);
            holder.mTextViewStatus.setVisibility(View.GONE);
        } else if (myaccount.getmDoc_Status().equals("1")) {
            holder.mLayout_accept.setVisibility(View.GONE);
            holder.mTextViewStatus.setVisibility(View.VISIBLE);
            holder.mTextViewStatus.setText("Accepted");
            holder.mTextViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.green));
        } else if (myaccount.getmDoc_Status().equals("2")) {
            holder.mLayout_accept.setVisibility(View.GONE);
            holder.mTextViewStatus.setVisibility(View.VISIBLE);
            holder.mTextViewStatus.setText("Rejected");
            holder.mTextViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        }

        holder.mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build1.setMessage("Are you sure want to Accept?");
                build1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getAcceptstatus(myaccount.getmPatientID(), "1", myaccount.getmDocId());
                        holder.mLayout_accept.setVisibility(View.GONE);
                        holder.mTextViewStatus.setVisibility(View.VISIBLE);
                        holder.mTextViewStatus.setText("Accepted");
                        holder.mTextViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.green));
                        myaccount.setmDoc_Status("1");
                        dialog1.dismiss();
                    }
                });
                build1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub
                        dialog1.dismiss();
                    }
                });
                dialog1 = build1.create();
                dialog1.show();
            }
        });

        holder.mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                build.setMessage("Are you sure want to Reject?");
                build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        getAcceptstatus(myaccount.getmPatientID(), "2", myaccount.getmDocId());
                        holder.mLayout_accept.setVisibility(View.GONE);
                        holder.mTextViewStatus.setVisibility(View.VISIBLE);
                        holder.mTextViewStatus.setText("Rejected");
                        holder.mTextViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                        myaccount.setmDoc_Status("2");
                        dailog.dismiss();
                    }
                });
                build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub
                        dailog.dismiss();
                    }
                });
                dailog = build.create();
                dailog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMyAccount.size();
    }


    public void getAcceptstatus(final String patientId, final String Accept_status, final String Doc_id) {
        final ProgressDialog loading = ProgressDialog.show(mContext, "Loading", "Please wait...", false, false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Root.URL + AppConfig.Accept, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Log.e("response", "" + response);
                Get_Customer_status(response);
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(mContext, "" + error, Toast.LENGTH_SHORT).show();
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
                params.put("doc_id", Doc_id);
                params.put("accept_status", Accept_status);
                params.put("patient_id", patientId);

                Log.e("Params", "" + params);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(postRequest);

    }

    private void Get_Customer_status(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            int success = jsonObject.getInt("status");
            String message = jsonObject.getString("message");
            if (success == 200) {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

            } else if (success == 500) {
                Toast.makeText(mContext, "Server Error", Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}







