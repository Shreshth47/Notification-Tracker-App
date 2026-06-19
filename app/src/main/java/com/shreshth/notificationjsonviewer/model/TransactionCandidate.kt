package com.shreshth.notificationjsonviewer.model

import com.shreshth.notificationjsonviewer.detector.TransactionType
import com.shreshth.notificationjsonviewer.detector.TransactionCategory

data class TransactionCandidate(
    val type: TransactionType,

    val amount: Double?,

    val counterparty: String?,

    val sourceApp: String,
    val sourceAppDisplayName: String,

    val timestamp: Long,

    val originalTitle: String?,

    val originalText: String?,
    val category: TransactionCategory,
)