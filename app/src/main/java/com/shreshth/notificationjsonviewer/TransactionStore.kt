package com.shreshth.notificationjsonviewer

import com.shreshth.notificationjsonviewer.model.TransactionCandidate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object TransactionStore {

    private val _transactions =
        MutableStateFlow<List<TransactionCandidate>>(emptyList())

    val transactions: StateFlow<List<TransactionCandidate>>
        get() = _transactions

    fun addTransaction(
        transaction: TransactionCandidate
    ) {

        val current =
            _transactions.value

        _transactions.value =
            listOf(transaction) +
                    current.take(49)
    }
}