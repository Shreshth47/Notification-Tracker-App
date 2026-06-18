package com.shreshth.notificationjsonviewer

data class NotificationData(
    val packageName: String,
    val title: String?,
    val text: String?,
    val timestamp: Long
)