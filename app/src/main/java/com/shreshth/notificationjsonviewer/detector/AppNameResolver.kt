package com.shreshth.notificationjsonviewer.detector

object AppNameResolver {

    fun resolve(
        packageName: String
    ): String {

        return when {

            packageName.contains(
                "phonepe",
                true
            ) -> "PhonePe"

            packageName.contains(
                "gpay",
                true
            ) -> "Google Pay"

            packageName.contains(
                "paytm",
                true
            ) -> "Paytm"

            packageName.contains(
                "myntra",
                true
            ) -> "Myntra"

            packageName.contains(
                "messages",
                true
            ) -> "SMS"

            else ->
                packageName
        }
    }
}