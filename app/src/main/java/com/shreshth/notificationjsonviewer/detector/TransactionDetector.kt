package com.shreshth.notificationjsonviewer.detector

object TransactionDetector {

    fun detect(
        title: String?,
        text: String?
    ): DetectionResult {

        val content =
            "${title ?: ""} ${text ?: ""}"
                .lowercase()

        // Expense
        if (
            content.contains("debited") ||
            content.contains("paid") ||
            content.contains("spent")
        ) {

            return DetectionResult(
                TransactionType.EXPENSE
            )
        }

        // Income
        if (
            content.contains("credited") ||
            content.contains("received") ||
            content.contains("sent ₹") ||
            content.contains("sent rs")
        ) {

            return DetectionResult(
                TransactionType.INCOME
            )
        }

        // Promotions
        if (
            content.contains("sale") ||
            content.contains("offer") ||
            content.contains("discount") ||
            content.contains("gift") ||
            content.contains("coupon") ||
            content.contains("meal")
        ) {

            return DetectionResult(
                TransactionType.PROMOTION
            )
        }

        if(
            content.contains("android")
        ) {
            return DetectionResult(TransactionType.SYSTEM)
        }

        return DetectionResult(
            TransactionType.UNKNOWN
        )
    }
}