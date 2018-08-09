package com.fine_fettle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fine_fettle.R;
import com.fine_fettle.TipsDetailActivity;
import com.fine_fettle.models.TipsModel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by karthik on 4/8/18.
 */

public class TipsAdapter  extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    ArrayList<TipsModel> mTipsList;
    Context mContext;
    private LayoutInflater inflater;


    public TipsAdapter(Context context, int resource, ArrayList<TipsModel> tipsList) {
        mTipsList = tipsList;
        mContext = context;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tips_item_lyt, parent, false);
        TipsViewHolder holder = new TipsViewHolder(view);
        return holder;
    }

    public void updateList(ArrayList<TipsModel> tipsList){
        mTipsList = tipsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        final TipsModel tip = mTipsList.get(position);

        Picasso.get()
                .load(tip.getmImageUrl())
                .placeholder(R.drawable.tips_icon)
                .error(R.drawable.tips_icon)
                .into(holder.mIcon);

        holder.mTitle.setText(tip.getmTitle());
        holder.mDescription.setText(tip.getmDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,TipsDetailActivity.class);
                intent.putExtra("tip_model",  tip);
                mContext.startActivity(intent);
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
//    @NonNull
//    @Override
//    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//
//        View view = layoutInflater.inflate(mResource, null, false);
//
//        ImageView imageView = view.findViewById(R.id.yoga_icon);
//        TextView textViewName = view.findViewById(R.id.title);
//        TextView desc = view.findViewById(R.id.desc);
//
//        TipsModel tip = mTipsList.get(position);
//
//        //adding values to the list item
//        imageView.setImageDrawable(mContext.getResources().getDrawable(tip.getmImageUrl()));
//        textViewName.setText(tip.getmTitle());
//        desc.setText(tip.getmDescription());
//
//
//        return view;
//    }


    public class TipsViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mIcon;


        public TipsViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.desc);
            mIcon = (ImageView) itemView.findViewById(R.id.yoga_icon);

        }

    }
}
