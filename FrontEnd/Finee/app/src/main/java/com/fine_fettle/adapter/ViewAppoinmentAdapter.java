package com.fine_fettle.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fine_fettle.Appointment;
import com.fine_fettle.CreateUpdateAppointment;
import com.fine_fettle.PostRequestHandler;
import com.fine_fettle.R;
import com.fine_fettle.models.HospitalModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by murugappanviswanathan on 05/08/18.
 */


public class ViewAppoinmentAdapter  extends RecyclerView.Adapter<ViewAppoinmentAdapter.AppointmentViewHolder> {

    ArrayList<HashMap<String, String>> mAppointmnetList;
    Context mContext;
    private LayoutInflater inflater;
    private PopupWindow mPopupWindow;
    RecyclerView mListView;


    public ViewAppoinmentAdapter(Context context, int resource, ArrayList<HashMap<String, String>> tipsList, RecyclerView mListView) {
        mAppointmnetList = tipsList;
        mContext = context;
        this.mListView = mListView;
        if (mContext != null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    public void updateList(ArrayList<HashMap<String, String>> tipsList){
        mAppointmnetList = tipsList;
        notifyDataSetChanged();
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
        String status = "PENDING";
        if(!tip.get("status").equals("null")) {
            status =Integer.parseInt(tip.get("status")) == 0 ? "PENDING" : Integer.parseInt(tip.get("status")) == 1 ? "ACCEPTED" : "CANCELLED";
            if(Integer.parseInt(tip.get("status")) != 0) {
                holder.edit.setVisibility(View.INVISIBLE);
                holder.cancel.setVisibility(View.INVISIBLE);
            }
        }
        holder.mStatus.setText("Status  : " + status);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            EditText dates, slots;
             Calendar newCalendar;
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View customView = inflater.inflate(R.layout.update,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                TextView tv = (TextView) customView.findViewById(R.id.msg);
                dates = customView.findViewById(R.id.date);
                slots = customView.findViewById(R.id.slot);
                newCalendar = Calendar.getInstance();
                dates.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFromdatePicker();
                    }
                });
                tv.setText("Update Appointment FFA0000" + tip.get("id"));
                Button cancelB = (Button) customView.findViewById(R.id.cancel);
                cancelB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
//                        new ((Appointment)mContext).AppointmentGetter().execute();
                        HashMap<String, String> params = new HashMap<>();
                        params.put("p_id", tip.get("id"));
                         String date = dates.getText().toString().trim();
                         String slot = slots.getText().toString().trim();
                        params.put("date", date);
                        params.put("slot", slot);
                        PostRequestHandler postRequestHandler = new PostRequestHandler("http://35.204.108.96/update_docapp.php", params);
                        postRequestHandler.execute();
                        ((Appointment)mContext).exe();
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mListView, Gravity.CENTER,0,0);

                Button closeButton = (Button) customView.findViewById(R.id.close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mListView, Gravity.CENTER,0,0);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();
            }

            private void mFromdatePicker() {
                android.app.DatePickerDialog fromDatePickerDialog = new android.app.DatePickerDialog(mContext, new android.app.DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        dates.setText(df.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.getDatePicker().setBackgroundColor(ContextCompat.getColor(mContext, R.color.White));
                fromDatePickerDialog.show();

            }

        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View customView = inflater.inflate(R.layout.cancel,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                TextView tv = (TextView) customView.findViewById(R.id.msg);
                tv.setText("Are you sure to cancel the appoinment FFA0000" + tip.get("id") + "? ");
                Button cancelB = (Button) customView.findViewById(R.id.cancel);
                cancelB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
//                        new ((Appointment)mContext).AppointmentGetter().execute();
                        HashMap<String, String> params = new HashMap<>();
                        params.put("p_id", tip.get("id"));
                        PostRequestHandler postRequestHandler = new PostRequestHandler("http://35.204.108.96/cancel_docapp.php", params);
                        postRequestHandler.execute();
                        ((Appointment)mContext).exe();
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mListView, Gravity.CENTER,0,0);

                Button closeButton = (Button) customView.findViewById(R.id.close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mListView, Gravity.CENTER,0,0);
            }
        });
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
        private Button edit;
        private Button cancel;


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
            edit = (Button) itemView.findViewById(R.id.edit);
            cancel = (Button) itemView.findViewById(R.id.cancel);


        }

    }
}
