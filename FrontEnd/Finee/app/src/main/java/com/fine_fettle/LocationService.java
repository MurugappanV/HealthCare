package com.fine_fettle;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;



public class LocationService extends Service implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String string, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String string) {

    }

    @Override
    public void onProviderDisabled(String string) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
