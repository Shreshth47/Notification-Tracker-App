package com.shreshth.notificationjsonviewer

import android.content.Context
import com.shreshth.notificationjsonviewer.model.DatasetEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object DatasetStore {

    private lateinit var appContext: Context

    private val _entries =
        MutableStateFlow<List<DatasetEntry>>(emptyList())

    val entries: StateFlow<List<DatasetEntry>>
        get() = _entries

    fun initialize(
        context: Context
    ) {

        appContext =
            context.applicationContext

        _entries.value =
            DatasetPersistence
                .loadDataset(
                    appContext
                )
    }

    fun addEntry(
        entry: DatasetEntry
    ) {

        _entries.value =
            listOf(entry) +
                    _entries.value

        DatasetPersistence
            .saveDataset(
                appContext,
                _entries.value
            )
    }
}