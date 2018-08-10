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
import com.fine_fettle.models.HospitalModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by murugappanviswanathan on 05/08/18.
 */

public class HospitalAdapter  extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    ArrayList<HospitalModel> mTipsList;
    Context mContext;
    private LayoutInflater inflater;


    public HospitalAdapter(Context context, int resource, ArrayList<HospitalModel> tipsList) {
        mTipsList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    public void updateList(ArrayList<HospitalModel> tipsList){
        mTipsList = tipsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HospitalAdapter.HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hospital_item_layout, parent, false);
        HospitalAdapter.HospitalViewHolder holder = new HospitalAdapter.HospitalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.HospitalViewHolder holder, int position) {
        final HospitalModel tip = mTipsList.get(position);

        Picasso.get()
                .load(tip.getImg())
                .placeholder(R.drawable.hospital_icon)
                .error(R.drawable.hospital_icon)
                .into(holder.mIcon);

        holder.mTitle.setText(tip.getHospital_name());
        holder.mDescription.setText(tip.getHospital_address() + ", " + tip.getHospital_pincode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Laboratory.class);
                intent.putExtra("id",  ((MakeAppointment)mContext).id);
                intent.putExtra("hospital_name", tip.getHospital_name() );
                mContext.startActivity(intent);
                ((MakeAppointment) mContext).finish();
            }
        });
        holder.distance.setText("Distance : " + tip.getDist() + " Km");
        holder.rating.setText("Rating : " + tip.getRating());

    }

    @Override
    public int getItemCount() {
        if(mTipsList.size()>0) {
            return mTipsList.size();
        }else{
            return 0;
        }
    }


    public class HospitalViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mIcon;
        private TextView distance;
        private TextView rating;


        public HospitalViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.name);
            mDescription = (TextView) itemView.findViewById(R.id.address);
            mIcon = (ImageView) itemView.findViewById(R.id.hosp_icon);
            distance = (TextView) itemView.findViewById(R.id.distance);
            rating = (TextView) itemView.findViewById(R.id.rating);

        }

    }
}
