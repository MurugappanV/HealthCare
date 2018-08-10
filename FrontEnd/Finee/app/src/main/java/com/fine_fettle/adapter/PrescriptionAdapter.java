package com.fine_fettle.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fine_fettle.Laboratory;
import com.fine_fettle.MakeAppointment;
import com.fine_fettle.R;
import com.fine_fettle.models.PrescriptionModel;


import java.util.ArrayList;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder>{
    ArrayList<PrescriptionModel> mTipsList;
    Context mContext;
    private LayoutInflater inflater;


    public PrescriptionAdapter(Context context, int resource,ArrayList<PrescriptionModel> tipsList) {
        mTipsList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    public PrescriptionAdapter.PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prescription_item_layout, parent, false);
        PrescriptionAdapter.PrescriptionViewHolder holder = new PrescriptionAdapter.PrescriptionViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(@NonNull PrescriptionAdapter.PrescriptionViewHolder  holder, int position) {
        final PrescriptionModel tip = mTipsList.get(position);

//        holder.mTitle.setText(tip.getHospital_name() + ", " + tip.getHospital_city());
//        holder.mDescription.setText(tip.getHospital_address() + ", " + tip.getHospital_pincode());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext,Laboratory.class);
//                intent.putExtra("id",  ((MakeAppointment)mContext).id);
//                intent.putExtra("hospital_name", tip.getHospital_name() );
//                mContext.startActivity(intent);
//            }
//        });

    }

    public int getItemCount() {
        if(mTipsList.size()>0) {
            return mTipsList.size();
        }else{
            return 0;
        }
    }


    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mIcon;


        public PrescriptionViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.name);
            mDescription = (TextView) itemView.findViewById(R.id.address);
            mIcon = (ImageView) itemView.findViewById(R.id.hosp_icon);

        }

    }

}
