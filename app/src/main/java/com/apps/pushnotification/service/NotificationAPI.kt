package com.apps.pushnotification.service
import com.apps.pushnotification.model.PushNotificationData
import com.apps.pushnotification.service.Constants.Companion.SERVER_KEY
import com.apps.pushnotification.service.Constants.Companion.CONTENT_TYPE
import com.apps.pushnotification.ui.PushNotification
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {
    @Headers("Authorization: key =$SERVER_KEY","Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body
        notification: PushNotificationData
    ) : Response<ResponseBody>
}