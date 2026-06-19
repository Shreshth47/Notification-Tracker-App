package com.shreshth.notificationjsonviewer.detector

object CounterpartyExtractor {

    fun extract(
        text: String?
    ): String? {

        if (text == null) return null

        // sent to X
        Regex(
            "sent to\\s+(.+)",
            RegexOption.IGNORE_CASE
        )
            .find(text)
            ?.let {
                return it.groupValues[1]
                    .trim()
            }

        // X sent ₹
        Regex(
            "^(.+?)\\s+sent\\s+₹",
            RegexOption.IGNORE_CASE
        )
            .find(text)
            ?.let {
                return it.groupValues[1]
                    .trim()
            }

        return null
    }
}