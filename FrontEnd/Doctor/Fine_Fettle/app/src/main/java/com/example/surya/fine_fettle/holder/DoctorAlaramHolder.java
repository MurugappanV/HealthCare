package com.example.surya.fine_fettle.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.surya.fine_fettle.R;


public class DoctorAlaramHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewMessage;

    public DoctorAlaramHolder(View itemView) {
        super(itemView);
        mTextViewMessage = itemView.findViewById(R.id.txt_message);


    }

}
