package com.apps.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneModeChangedReceiver  : BroadcastReceiver(){

    override fun onReceive(context: Context?, intnet: Intent?) {
        val isAirPlaneModeEnabled = intnet?.getBooleanExtra("state",false)?:return
        if (isAirPlaneModeEnabled){
            Toast.makeText(context,"AirPlaneMode is Enabled",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"AirPlaneMode is Disabled",Toast.LENGTH_LONG).show()
        }
    }
}