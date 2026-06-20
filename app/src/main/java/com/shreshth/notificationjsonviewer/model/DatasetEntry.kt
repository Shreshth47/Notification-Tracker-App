package com.shreshth.notificationjsonviewer.model

import com.shreshth.notificationjsonviewer.NotificationData

data class DatasetEntry(
    val notification: NotificationData,
    val transaction: TransactionCandidate
)