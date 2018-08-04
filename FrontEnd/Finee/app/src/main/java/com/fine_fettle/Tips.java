package com.fine_fettle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.fine_fettle.adapter.TipsAdapter;
import com.fine_fettle.models.TipsModel;

import java.util.ArrayList;

/**
 * Created by Priyadharshini on 05-Jul-18.
 */

public class Tips extends AppCompatActivity{
    private ArrayList<TipsModel> tipsList = new ArrayList<>();
    private RecyclerView mListView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_lyt);
        mListView = (RecyclerView)findViewById(R.id.tips_list) ;
        prepareTipsList();
        setAdapter();
    }

    public void prepareTipsList(){
        //TipsModel tipsModel = new TipsModel();
        tipsList.add(new TipsModel("Bharadvajasana ","This gentle twist is a tonic for the spine and the abdominal organs",R.drawable.ambulance_icon));
        tipsList.add(new TipsModel("Padangusthasana ","This pose gently lengthens and strengthens even stubbornly tight hamstrings",R.drawable.appoinment_icon));
        tipsList.add(new TipsModel("Paripurna Navasana ","An ab and deep hip flexor strengthener, Paripurna Navasana requires you to balance on " +
                "the tripod of your sitting bones and tailbone",R.drawable.addicon));
        tipsList.add(new TipsModel("Baddha Konasana ","One of the best hip openers around, Bound Angle " +
                "Pose counteracts chair- and cardio-crunched hips.",R.drawable.blood_bank));
        tipsList.add(new TipsModel("Dhanurasana ","Bend back into the shape of a bow to feel " +
                "energetically locked, loaded, and ready to take aim",R.drawable.bmi_icon));
        tipsList.add(new TipsModel("Setu Bandha Sarvangasana ","Bridge Pose can be whatever " +
                "you need—energizing, rejuvenating, or luxuriously restorative",R.drawable.chat_icon));
        tipsList.add(new TipsModel("Ustrasana ","Bump up your energy by bending back into Camel Pose.",R.drawable.logo));
        tipsList.add(new TipsModel("Marjaryasana ","This pose provides a gentle massage to the spine and belly organs.",R.drawable.appointment));


    }

    private void setAdapter(){
        TipsAdapter adapter = new TipsAdapter(this,R.layout.tips_item_lyt,tipsList);
        LinearLayoutManager horizontalLinearLytmanager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(horizontalLinearLytmanager);
        mListView.setAdapter(adapter);

    }
}
