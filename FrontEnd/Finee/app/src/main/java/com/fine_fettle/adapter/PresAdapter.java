package com.fine_fettle.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fine_fettle.R;
import com.fine_fettle.models.PresModel;

import java.util.ArrayList;

/**
 * Created by murugappanviswanathan on 11/08/18.
 */

public class PresAdapter extends RecyclerView.Adapter<PresAdapter.PresViewHolder> {

    ArrayList<PresModel> mAppointmnetList;
    Context mContext;
    private LayoutInflater inflater;


    public PresAdapter(Context context, int resource, ArrayList<PresModel> tipsList) {
        mAppointmnetList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @NonNull
    @Override
    public PresAdapter.PresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pres_item_layout, parent, false);
        PresAdapter.PresViewHolder holder = new PresAdapter.PresViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PresAdapter.PresViewHolder holder, int position) {
        final PresModel tip = mAppointmnetList.get(position);
        holder.disease.setText(tip.getP_disease());
        holder.patient.setText("Patient : " + tip.getP_name());
        holder.presc.setText("Prescription : " + tip.getPrescription());
        holder.date.setText( tip.getEntry_date());
        holder.cond.setText("Condition : " + tip.getP_condition());
//        holder.doc.setText("Doc. " + tip.getD_name());
//        holder.hos.setText("Hos. " +tip.getH_name());
//        holder.b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+ tip.getPhone()));
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        if(mAppointmnetList.size()>0) {
            return mAppointmnetList.size();
        }else{
            return 0;
        }
    }


    public class PresViewHolder extends RecyclerView.ViewHolder {
        private TextView disease;
        private TextView patient;
        private TextView presc;
        private TextView date;
        private TextView cond;


        public PresViewHolder(View itemView) {
            super(itemView);
            disease = (TextView) itemView.findViewById(R.id.disease);
            patient = (TextView) itemView.findViewById(R.id.patient);
            presc = (TextView) itemView.findViewById(R.id.presc);
            date = (TextView) itemView.findViewById(R.id.date);
            cond = (TextView) itemView.findViewById(R.id.cond);


        }

    }
}
