package com.example.surya.fine_fettle.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.surya.fine_fettle.R;
import com.example.surya.fine_fettle.holder.PatientBookingHistoryHolder;
import com.example.surya.fine_fettle.model.Patienthistory;

import java.util.ArrayList;


public class PatientBookingHistoryAdapter extends RecyclerView.Adapter<PatientBookingHistoryHolder> {

    private Context mContext;
    private ArrayList<Patienthistory> mMyAccount;

    public PatientBookingHistoryAdapter(Context context, ArrayList<Patienthistory> myPhotoList) {
        mContext = context;
        mMyAccount = myPhotoList;

    }

    @NonNull
    @Override
    public PatientBookingHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PatientBookingHistoryHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient_booking_history, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientBookingHistoryHolder holder, final int position) {
        Patienthistory myaccount = mMyAccount.get(position);

        holder.mTextViewName.setText(myaccount.getmName());
        holder.mTextViewAge.setText(myaccount.getmAge());
        holder.mTextViewdate.setText(myaccount.getmDate());
        holder.mTextViewTime.setText(myaccount.getmTime());
        holder.mTextViewProblem.setText(myaccount.getmProblem());



        if(myaccount.getmStatus().equals("1"))
        {
            holder.mTextViewStatus.setText("Completed");
            holder.mTextViewStatus.setTextColor(ContextCompat.getColor(mContext,R.color.green));
        }


    }

    @Override
    public int getItemCount() {
        return mMyAccount.size();
    }


}







