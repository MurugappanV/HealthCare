package com.example.surya.fine_fettle.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.surya.fine_fettle.R;
import com.example.surya.fine_fettle.holder.DoctorAlaramHolder;
import com.example.surya.fine_fettle.model.AlaramMessage;

import java.util.ArrayList;


public class DoctorAlaramAdapter extends RecyclerView.Adapter<DoctorAlaramHolder> {

    private Context mContext;
    private ArrayList<AlaramMessage> mMyAccount;

    public DoctorAlaramAdapter(Context context, ArrayList<AlaramMessage> myPhotoList) {
        mContext = context;
        mMyAccount = myPhotoList;

    }

    @NonNull
    @Override
    public DoctorAlaramHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DoctorAlaramHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alaram, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAlaramHolder holder, final int position) {
        AlaramMessage myaccount = mMyAccount.get(position);

        holder.mTextViewMessage.setText(myaccount.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMyAccount.size();
    }


}







