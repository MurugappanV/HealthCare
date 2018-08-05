package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.fine_fettle.models.TipsModel;
import com.squareup.picasso.Picasso;

/**
 * Created by murugappanviswanathan on 05/08/18.
 */

public class TipsDetailActivity extends AppCompatActivity {
    ImageView mImage;
    TextView mContent;
    TextView mTitile;
    public TipsModel tipsModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);
        initViews();
        getData();
        setData();
    }

    public void initViews(){
        mTitile = (TextView) findViewById(R.id.title);
        mContent = (TextView) findViewById(R.id.content);
        mImage = (ImageView) findViewById(R.id.expandedImage);
    }

    public void getData(){
       Intent intent =  getIntent();
       if(intent!=null){
           tipsModel = (TipsModel) intent.getSerializableExtra("tip_model");
       }
    }

    public void setData(){
        if(tipsModel!=null){
            Picasso.get()
                    .load(tipsModel.getmImageUrl())
                    .placeholder(R.drawable.tips_icon)
                    .error(R.drawable.tips_icon)
                    .into(mImage);        }
        mTitile.setText(tipsModel.getmTitle());
        mContent.setText(tipsModel.getmContent().replace("\n", ""));
//        mI.setText(tipsModel.getmContent().replace("\n", ""));
    }
}
