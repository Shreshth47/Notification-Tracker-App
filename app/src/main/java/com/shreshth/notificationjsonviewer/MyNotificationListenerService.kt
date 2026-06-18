package com.shreshth.notificationjsonviewer

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.gson.Gson
import android.content.Intent

class MyNotificationListenerService : NotificationListenerService() {
    private val gson = Gson()

    override fun onListenerConnected() {
        super.onListenerConnected()

        Log.d("NotifListener", "Listener Connected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)

        val notification = sbn.notification
        val extras = notification.extras

        val title =
            extras.getCharSequence(android.app.Notification.EXTRA_TITLE)?.toString()

        val text =
            extras.getCharSequence(android.app.Notification.EXTRA_TEXT)?.toString()

        val notificationData = NotificationData(
            packageName = sbn.packageName,
            title = title,
            text = text,
            timestamp = sbn.postTime
        )

        val json = gson.toJson(notificationData)
        NotificationStore.addNotification(notificationData)
        val intent = Intent(Constants.ACTION_NEW_NOTIFICATION)


        Log.d("NotifJSON", json)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)

        Log.d(
            "NotifListener",
            "Notification removed from ${sbn.packageName}"
        )
    }
}