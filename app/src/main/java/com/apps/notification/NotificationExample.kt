package com.apps.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.apps.todolistapp.R
import com.apps.todolistapp.databinding.ActivityNotificationExampleBinding

class NotificationExample : AppCompatActivity() {
    val CHANNEL_ID = "notificationExampleChannelID"
    val CHANNEL_NAME = "notificationExampleChannelName"
    val NOTIFICATION_ID = 0

    private lateinit var binding: ActivityNotificationExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationExampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createNotificationChannel()
//
//        val intent = Intent(this, NotificationExample::class.java)
//        val pendingIntent = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(intent)
//            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
//        }


        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Notification Example")
            .setContentText("This is an Example of a Notification")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
            .build()

        val notificationManger = NotificationManagerCompat.from(this)
        binding.btnShowNotification.setOnClickListener {
            notificationManger.notify(NOTIFICATION_ID,notification)
        }
    }

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val channel = NotificationChannel(
            CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            lightColor = Color.RED
            enableLights(true)
        }
           val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}