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
import com.fine_fettle.models.HelpModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by murugappanviswanathan on 11/08/18.
 */

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpViewHolder> {

    ArrayList<HelpModel> mAppointmnetList;
    Context mContext;
    private LayoutInflater inflater;


    public HelpAdapter(Context context, int resource, ArrayList<HelpModel> tipsList) {
        mAppointmnetList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @NonNull
    @Override
    public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.help_item_layout, parent, false);
        HelpViewHolder holder = new HelpViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {
        final HelpModel tip = mAppointmnetList.get(position);
            holder.msg.setText(tip.getMessage());
            holder.patient.setText("Patient      : " + tip.getP_name());
            holder.opr.setText("Operation : " + tip.getOperation());
            holder.date.setText("Date           : " + tip.getO_date());
            holder.amt.setText("Rs. " + tip.getO_fees());
            holder.doc.setText("Doc. " + tip.getD_name());
            holder.hos.setText("Hos. " +tip.getH_name());
            holder.b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+ tip.getPhone()));
                    mContext.startActivity(intent);
                }
            });

//        holder.mPatient.setText("Patient : " + tip.get("p_name"));
//        holder.mAppNo.setText("App.no : FFA0000" + tip.get("id"));
//        holder.mIssue.setText("Issue    : " + tip.get("h_issue"));
//        holder.mDocHos.setText(tip.get("doc_name"));
//        holder.mHos.setText(tip.get("hos_name"));
//        holder.mDate.setText(tip.get("date").substring(8,10) + "-" +tip.get("date").substring(5,7) + "-" + tip.get("date").substring(0,4) );
//        holder.mSlot.setText(tip.get("slot"));
//        String status = "PENDING";
//        if(!tip.get("status").equals("null")) {
//            status =Integer.parseInt(tip.get("status")) == 0 ? "PENDING" : Integer.parseInt(tip.get("status")) == 1 ? "ACCEPTED" : "REJECTED";
//        }
//        holder.mStatus.setText("Status  : " + status);
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


    public class HelpViewHolder extends RecyclerView.ViewHolder {
        private TextView msg;
        private TextView patient;
        private TextView opr;
        private TextView date;
        private TextView amt;
        private TextView doc;
        private TextView hos;
        private Button b;


        public HelpViewHolder(View itemView) {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.msg);
            patient = (TextView) itemView.findViewById(R.id.patient);
            opr = (TextView) itemView.findViewById(R.id.opr);
            date = (TextView) itemView.findViewById(R.id.date);
            amt = (TextView) itemView.findViewById(R.id.amt);
            doc = (TextView) itemView.findViewById(R.id.doc);
            hos = (TextView) itemView.findViewById(R.id.hos);
            b = (Button) itemView.findViewById(R.id.help);


        }

    }
}
