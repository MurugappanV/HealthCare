package com.fine_fettle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fine_fettle.Laboratory;
import com.fine_fettle.MakeAppointment;
import com.fine_fettle.R;
import com.fine_fettle.models.DoctorModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by murugappanviswanathan on 06/08/18.
 */

public class DoctorAdapter  extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    ArrayList<DoctorModal> mTipsList;
    Context mContext;
    private LayoutInflater inflater;


    public DoctorAdapter(Context context, int resource, ArrayList<DoctorModal> tipsList) {
        mTipsList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @NonNull
    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_item_layout, parent, false);
        DoctorAdapter.DoctorViewHolder holder = new DoctorAdapter.DoctorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.DoctorViewHolder holder, int position) {
        final DoctorModal tip = mTipsList.get(position);

        Picasso.get()
                .load(tip.getGender().equals("male") ? R.drawable.male_doctor : R.drawable.female_doctor)
                .placeholder(R.drawable.male_doctor)
                .error(R.drawable.male_doctor)
                .into(holder.mIcon);

        holder.mTitle.setText(tip.getName() + ", " + tip.getHospital_name());
        holder.mDescription.setText(tip.getSpecialization());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext,Laboratory.class);
//                intent.putExtra("id",  ((MakeAppointment)mContext).id);
//                intent.putExtra("hospital_name", tip.getHospital_name() );
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mTipsList.size()>0) {
            return mTipsList.size();
        }else{
            return 0;
        }
    }


    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mIcon;


        public DoctorViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.name);
            mDescription = (TextView) itemView.findViewById(R.id.specialisation);
            mIcon = (ImageView) itemView.findViewById(R.id.doc_icon);

        }

    }
}
