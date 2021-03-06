package com.team.sear.kcpt.objects

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat

import com.team.sear.kcpt.oldclasses.Navigate
import com.team.sear.kcpt.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@Suppress("DEPRECATION")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage.notification!!.body)
    }

    private fun sendNotification(messageBody: String?) {
        val intent = Intent(this, Navigate::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val defaultBoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultBoundUri)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}
