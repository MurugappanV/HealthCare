package com.tinmegali.mylocation;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;


public class GeofenceTrasitionService extends IntentService {

    private static final String TAG = GeofenceTrasitionService.class.getSimpleName();

    public static final int GEOFENCE_NOTIFICATION_ID = 0;

    public GeofenceTrasitionService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        // Handling errors
        if ( geofencingEvent.hasError() ) {
            String errorMsg = getErrorString(geofencingEvent.getErrorCode() );
            Log.e( TAG, errorMsg );
            return;
        }

        int geoFenceTransition = geofencingEvent.getGeofenceTransition();
        // Check if the transition type is of interest
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT ) {
            // Get the geofence that were triggered
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String geofenceTransitionDetails = getGeofenceTrasitionDetails(geoFenceTransition, triggeringGeofences );

            // Send notification details as a String
            sendNotification( geofenceTransitionDetails);
        }
    }
    private String getGeofenceTrasitionDetails(int geoFenceTransition, List<Geofence> triggeringGeofences) {
        // get the ID of each geofence triggered
        ArrayList<String> triggeringGeofencesList = new ArrayList<>();
        for ( Geofence geofence : triggeringGeofences ) {
            triggeringGeofencesList.add( geofence.getRequestId() );
        }

        String status = null;
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER )
            status = "Entering ";
        else if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT )
            status = "Exiting ";
        return status + TextUtils.join( ", ",triggeringGeofencesList);
    }

    private void sendNotification( String msg ) {
        Log.i(TAG, "sendNotification: " + msg );

        // Intent to start the main Activity
        Intent notificationIntent = MainActivity.makeNotificationIntent(
                getApplicationContext(), msg
        );

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        // Creating and sending Notification
        NotificationManager notificatioMng =
                (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        notificatioMng.notify(
                GEOFENCE_NOTIFICATION_ID,
                createNotification(msg, notificationPendingIntent));

    }

    // Create notification
    private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.drawable.ic_action_location)
                .setColor(Color.RED)
                .setContentTitle(msg)
                .setContentText("Geofence Notification!")
                .setContentIntent(notificationPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);
        return notificationBuilder.build();
    }
    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";
        }
    }
}
//    public static final String TAG = GeofenceTransitionReceiver.class.getSimpleName();
//
//    private Context context;
//
//    public GeofenceTransitionReceiver() {
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.v(TAG, "onReceive(context, intent)");
//        this.context = context;
//        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
//        if(event != null){
//            if(event.hasError()){
//                onError(event.getErrorCode());
//            } else {
//                int transition = event.getGeofenceTransition();
//                if(transition == Geofence.GEOFENCE_TRANSITION_ENTER || transition == Geofence.GEOFENCE_TRANSITION_DWELL || transition == Geofence.GEOFENCE_TRANSITION_EXIT){
//                    String[] geofenceIds = new String[event.getTriggeringGeofences().size()];
//                    for (int index = 0; index < event.getTriggeringGeofences().size(); index++) {
//                        geofenceIds[index] = event.getTriggeringGeofences().get(index).getRequestId();
//                    }
//                    if (transition == Geofence.GEOFENCE_TRANSITION_ENTER || transition == Geofence.GEOFENCE_TRANSITION_DWELL) {
//                        onEnteredGeofences(geofenceIds);
//                    } else {
//                        onExitedGeofences(geofenceIds);
//                    }
//                }
//            }
//        }
//    }
//
//    protected void onEnteredGeofences(String[] geofenceIds) {
//        for (String fenceId : geofenceIds) {
//            Toast.makeText(context, String.format("Entered this fence: %1$s", fenceId), Toast.LENGTH_SHORT).show();
//            Log.i(TAG, String.format("Entered this fence: %1$s", fenceId));
//            createNotification(fenceId, "Entered the location");
//        }
//    }
//
//    protected void onExitedGeofences(String[] geofenceIds){
//        for (String fenceId : geofenceIds) {
//            Toast.makeText(context, String.format("Exited this fence: %1$s", fenceId), Toast.LENGTH_SHORT).show();
//            Log.i(TAG, String.format("Exited this fence: %1$s", fenceId));
//            createNotification(fenceId, "Exited the location");
//        }
//    }
//
//    protected void onError(int errorCode){
//        Toast.makeText(context, String.format("onError(%1$d)", errorCode), Toast.LENGTH_SHORT).show();
//        Log.e(TAG, String.format("onError(%1$d)", errorCode));
//    }
//
//    /**
//     * Create our notification.
//     *
//     * @param fenceId the name of the Geofence
//     * @param fenceState Entered, Exited or Dwell
//     */
//    private void createNotification(String fenceId, String fenceState) {
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
//        notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);
//        notificationBuilder
//                .setContentText(fenceId)
//                .setContentTitle(String.format("Fence %1$s", fenceState))
//                .setSmallIcon(R.drawable.ic_stat_action_room)
//                .setColor(Color.argb(0x55, 0x00, 0x00, 0xff))
//                .setTicker(String.format("%1$s Fence: %2$s", fenceState, fenceId));
//        Intent notificationIntent = new Intent(context, MapsActivity.class);
//        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        notificationIntent.setAction(Intent.ACTION_MAIN);
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
//        notificationBuilder.setContentIntent(pendingIntent);
//        notificationManager.notify(R.id.notification, notificationBuilder.build());
//    }
//
//}
