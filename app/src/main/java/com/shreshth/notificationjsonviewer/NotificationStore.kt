package com.shreshth.notificationjsonviewer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NotificationStore {

    private val _notifications =
        MutableStateFlow<List<NotificationData>>(emptyList())

    val notifications: StateFlow<List<NotificationData>>
        get() = _notifications

    fun addNotification(notification: NotificationData) {

        val current = _notifications.value

        _notifications.value =
            listOf(notification) + current.take(49)

    }
}