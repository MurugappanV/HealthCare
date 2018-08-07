package com.fine_fettle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Priyadharshini on 05-Jul-18.
 */

public class Ambulance extends AppCompatActivity implements LocationListener, OnMapReadyCallback {
    private GoogleMap googleMap;
    String radius;
    String deviceId = "EADEH000001455"; // show hospital drop down , and driver can pick up a hospital
    // on selecting hospital get value from dbs
    // String device = "EADEH000001455";
    private static final int LOCATION_INTERVAL = 1;
    private static final float LOCATION_DISTANCE = 1f;
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);



        //    locationManager.requestLocationUpdates(provider, 2000,0,this);
    }

    @Override
    public void onLocationChanged (Location location){
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);

        lat = location.getLatitude();
        lon = location.getLongitude();
        String latitude = Double.toString(lat);
        String longitude = Double.toString(lon);
        LatLng latLng = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        float zoom = 14f;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        googleMap.animateCamera(cameraUpdate);
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
        radius = Float.toString(1000.0f);
        String Destination_latitude = Double.toString(13.0579556);
        String Destination_longitude = Double.toString(80.2733797);


        Log.e("radius", "" + radius);
        Log.e("Destination_latitude", "" + Destination_latitude);
        Log.e("Destination_longitude", "" + Destination_longitude);
        getlogin(latitude, longitude, deviceId, Destination_latitude, Destination_longitude, radius);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private boolean isGooglePlayServicesAvailable () {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    public void getlogin ( final String latitude, final String longitude, final String device,
    final String dest_lat, final String dest_lon, final String radius ){
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLs.URL + LocationApi.Login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("", "" + URLs.URL + LocationApi.Login);
                Log.e("response", "" + response);
                Get_Customer_status(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                } else if (error instanceof AuthFailureError) {

                } else if (error instanceof ServerError) {

                } else if (error instanceof NetworkError) {

                } else if (error instanceof ParseError) {
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                params.put("device", device);
                params.put("dest_lat", dest_lat);
                params.put("dest_lon", dest_lon);
                Log.e("dest_lon", "" + dest_lon);
                params.put("radius", radius);
                Log.e("Params", "" + params);
                return params;
            }
        };
        // Adding request to request queue
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(postRequest);
    }
    //  AppController.getInstance().addToRequestQueue(postRequest);
    public void Get_Customer_status (String response){
        try {

            JSONObject jsonObject = new JSONObject(response);


            int success = jsonObject.getInt("status");
            if (success == 100) {

                Log.e("sucess_status", "");
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

            } else if (success == 101) {
                Log.e("failure_status", "");
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                finish();
            } else if (success == 500) {
                Toast.makeText(getApplicationContext(), "Server_Error", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setMyLocationEnabled(true);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }
    }
}

