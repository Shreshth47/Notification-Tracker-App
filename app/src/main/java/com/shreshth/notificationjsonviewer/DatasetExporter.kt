package com.shreshth.notificationjsonviewer

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.google.gson.GsonBuilder
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DatasetExporter {

    fun export(
        context: Context
    ) {

        val gson =
            GsonBuilder()
                .setPrettyPrinting()
                .create()

        val json =
            gson.toJson(
                DatasetStore.entries.value
            )

        val fileName =
            "dataset_${
                SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm",
                    Locale.getDefault()
                ).format(Date())
            }.json"

        val file =
            File(
                context.cacheDir,
                fileName
            )

        file.writeText(json)

        val uri =
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

        val shareIntent =
            Intent(Intent.ACTION_SEND).apply {

                type = "application/json"

                putExtra(
                    Intent.EXTRA_STREAM,
                    uri
                )

                addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

        context.startActivity(
            Intent.createChooser(
                shareIntent,
                "Export Dataset"
            )
        )
    }
}