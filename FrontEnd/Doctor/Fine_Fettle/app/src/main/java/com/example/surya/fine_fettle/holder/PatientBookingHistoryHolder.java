package com.example.surya.fine_fettle.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.surya.fine_fettle.R;


public class PatientBookingHistoryHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewName, mTextViewAge, mTextViewdate, mTextViewTime, mTextViewProblem, mTextViewStatus;

    public PatientBookingHistoryHolder(View itemView) {
        super(itemView);
        mTextViewName = itemView.findViewById(R.id.txt_name);
        mTextViewAge = itemView.findViewById(R.id.txt_age);
        mTextViewTime = itemView.findViewById(R.id.txt_time);
        mTextViewdate = itemView.findViewById(R.id.txt_date);
        mTextViewProblem = itemView.findViewById(R.id.txt_problem);
        mTextViewStatus = itemView.findViewById(R.id.txt_Status);

    }

}
