package com.fine_fettle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fine_fettle.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by murugappanviswanathan on 05/08/18.
 */


public class ViewAppoinmentAdapter  extends RecyclerView.Adapter<ViewAppoinmentAdapter.AppointmentViewHolder> {

    ArrayList<HashMap<String, String>> mAppointmnetList;
    Context mContext;
    private LayoutInflater inflater;


    public ViewAppoinmentAdapter(Context context, int resource, ArrayList<HashMap<String, String>> tipsList) {
        mAppointmnetList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.appointment_item_layout, parent, false);
        AppointmentViewHolder holder = new AppointmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        final HashMap<String, String> tip = mAppointmnetList.get(position);


        holder.mPatient.setText("Patient : " + tip.get("p_name"));
        holder.mAppNo.setText("App.no : FFA0000" + tip.get("id"));
        holder.mIssue.setText("Issue    : " + tip.get("h_issue"));
        holder.mDocHos.setText(tip.get("doc_name"));
        holder.mHos.setText(tip.get("hos_name"));
        holder.mDate.setText(tip.get("date").substring(8,10) + "-" +tip.get("date").substring(5,7) + "-" + tip.get("date").substring(0,4) );
        holder.mSlot.setText(tip.get("slot"));
        holder.mStatus.setText(Integer.parseInt(tip.get("status")) == 0 ? "PENDING" : Integer.parseInt(tip.get("status")) == 1 ? "ACCEPTED" : "REJECTED");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext,TipsDetailActivity.class);
//                intent.putExtra("tip_model",  tip);
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


    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private TextView mPatient;
        private TextView mAppNo;
        private TextView mIssue;
        private TextView mDate;
        private TextView mDocHos;
        private TextView mSlot;
        private TextView mStatus;
        private TextView mHos;


        public AppointmentViewHolder(View itemView) {
            super(itemView);
            mPatient = (TextView) itemView.findViewById(R.id.patient);
            mAppNo = (TextView) itemView.findViewById(R.id.appoinmntNo);
            mIssue = (TextView) itemView.findViewById(R.id.issue);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mDocHos = (TextView) itemView.findViewById(R.id.docHos);
            mSlot = (TextView) itemView.findViewById(R.id.time);
            mStatus = (TextView) itemView.findViewById(R.id.status);
            mHos = (TextView) itemView.findViewById(R.id.hos);


        }

    }
}
