package edu.uark.finalproject.MapViewActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast toast = Toast.makeText(context, "Geofence entered", Toast.LENGTH_LONG);
        toast.show();
    }
}