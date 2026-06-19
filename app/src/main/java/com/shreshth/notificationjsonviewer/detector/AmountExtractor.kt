package com.shreshth.notificationjsonviewer.detector

object AmountExtractor {

    private val amountRegex =
        Regex(
            "(?:₹|rs\\.?|inr)\\s?([\\d,]+(?:\\.\\d{1,2})?)",
            RegexOption.IGNORE_CASE
        )

    fun extract(
        text: String?
    ): Double? {

        if (text == null) return null

        val match =
            amountRegex.find(text)
                ?: return null

        return match
            .groupValues[1]
            .replace(",", "")
            .toDoubleOrNull()
    }
}