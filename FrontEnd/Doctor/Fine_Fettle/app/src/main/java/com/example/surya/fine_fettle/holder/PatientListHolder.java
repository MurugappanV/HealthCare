package com.example.surya.fine_fettle.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.surya.fine_fettle.R;


public class PatientListHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewName, mTextViewAge, mTextViewdate, mTextViewTime, mTextViewProblem,mTextViewStatus;
    public Button mAccept, mReject;
    public LinearLayout mLayout_accept;

    public PatientListHolder(View itemView) {
        super(itemView);
        mTextViewName = itemView.findViewById(R.id.txt_name);
        mTextViewAge = itemView.findViewById(R.id.txt_age);
        mTextViewTime = itemView.findViewById(R.id.txt_time);
        mTextViewdate = itemView.findViewById(R.id.txt_date);
        mTextViewProblem = itemView.findViewById(R.id.txt_problem);
        mAccept = itemView.findViewById(R.id.btn_Accept);
        mLayout_accept = itemView.findViewById(R.id.layout_accept);
        mReject = itemView.findViewById(R.id.btn_Reject);
        mTextViewStatus = itemView.findViewById(R.id.txt_status);


    }

}
