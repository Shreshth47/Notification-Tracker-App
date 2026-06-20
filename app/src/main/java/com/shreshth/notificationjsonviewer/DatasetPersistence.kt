package com.shreshth.notificationjsonviewer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shreshth.notificationjsonviewer.model.DatasetEntry

object DatasetPersistence {

    private const val FILE_NAME =
        "dataset.json"

    private val gson = Gson()

    fun saveDataset(
        context: Context,
        entries: List<DatasetEntry>
    ) {

        val json =
            gson.toJson(entries)

        context.openFileOutput(
            FILE_NAME,
            Context.MODE_PRIVATE
        ).use {

            it.write(
                json.toByteArray()
            )
        }
    }

    fun loadDataset(
        context: Context
    ): List<DatasetEntry> {

        return try {

            val json =
                context.openFileInput(
                    FILE_NAME
                ).bufferedReader()
                    .use {
                        it.readText()
                    }

            val type =
                object :
                    TypeToken<List<DatasetEntry>>() {}.type

            gson.fromJson(
                json,
                type
            ) ?: emptyList()

        } catch (e: Exception) {

            emptyList()
        }
    }
}