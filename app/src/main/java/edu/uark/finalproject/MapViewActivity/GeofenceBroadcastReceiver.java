package edu.uark.finalproject.MapViewActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

import androidx.annotation.Nullable;
import edu.uark.finalproject.R;
import edu.uark.finalproject.ViewQueueActivity.ViewQueueActivity;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // grabbing the geofencing event that is passed from onReceive method
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        // if theres an error while getting the geofence event
        if (geofencingEvent.hasError()){
            Log.d("GeofenceBroadcastReceiver: ", "Error grabbing geofence event");
            return;
        }

        // get list of geofence triggering event
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();

        // loop through event list and display toast to distinguish which was triggered
        for(Geofence geofence: geofenceList) {
            Log.d("GeofenceBroadcastReceiver: ", geofence.getRequestId());

            int transitionType = geofencingEvent.getGeofenceTransition();

            switch (transitionType) {
                case Geofence.GEOFENCE_TRANSITION_ENTER:
                    return;
                case Geofence.GEOFENCE_TRANSITION_DWELL:
                    return;
                case Geofence.GEOFENCE_TRANSITION_EXIT:
                    return;
            }
        }
    }

}