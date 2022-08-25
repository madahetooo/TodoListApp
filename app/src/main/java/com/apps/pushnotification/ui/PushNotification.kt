package com.apps.pushnotification.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.pushnotification.model.NotificationData
import com.apps.pushnotification.model.PushNotificationData
import com.apps.pushnotification.service.FirebaseService
import com.apps.pushnotification.service.RetrofitInstance
import com.apps.todolistapp.databinding.ActivityPushNotificationBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PushNotification : AppCompatActivity() {
    private lateinit var binding: ActivityPushNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseService.sharedPref = getSharedPreferences("sharedPrefFile",0)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
            binding.etToken.setText(it)
        }

        binding.btnSendNotification.setOnClickListener {
          val notificationTitle =  binding.etNotificationTitle.text.toString()
          val notificationMessage =  binding.etNotificationMessage.text.toString()
          val recipientToken =  binding.etToken.text.toString()
            if (notificationTitle.isNotEmpty() && notificationMessage.isNotEmpty() && recipientToken.isNotEmpty()){
                PushNotificationData(NotificationData(notificationTitle,notificationMessage),recipientToken).also {
                    sendNotification(it)
                }
            }
        }

    }

    private fun sendNotification(notification: PushNotificationData) =
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful){
//                    Log.d("PushNotification","${Gson().toJson(response)}")
                }else{
                    Log.d("PushNotification",response.errorBody().toString())
                }
            } catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@PushNotification,e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
}