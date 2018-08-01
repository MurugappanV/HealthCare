package com.fine_fettle;

import android.os.AsyncTask;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by Priyadharshini on 14-Jul-18.
 */

public class PostRequestHandler extends AsyncTask<Void, Void, String> {
    // Request URL
    private String url;
    // Key, Value pair
    private HashMap<String, String> params;

    PostRequestHandler(String url, HashMap<String, String> params){
        this.url = url;
        this.params = params;
        // Log.d("Input Box", designation);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {

        // Now Send a post request
        BackgroundWorker backgroundWorker = new BackgroundWorker();
        try {
            //                    Log.d("HashMap--------", requestedParams.get("salary"));
//                    Log.d("Results------", s.toString());
//                    Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG).show();
            return backgroundWorker.postRequestHandler(url,params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //progressBar.setVisibility(GONE);
        //Toast.makeText(getApplicationContext, "Result : " + s, Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
    }
}
