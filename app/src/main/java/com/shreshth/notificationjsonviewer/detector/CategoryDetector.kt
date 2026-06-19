package com.shreshth.notificationjsonviewer.detector

object CategoryDetector {

    fun detect(
        counterparty: String?
    ): TransactionCategory {

        val name =
            counterparty
                ?.lowercase()
                ?: return TransactionCategory.OTHER

        return when {

            name.contains("swiggy") ||
                    name.contains("zomato") ||
                    name.contains("domino") ||
                    name.contains("pizza") ->

                TransactionCategory.FOOD

            name.contains("uber") ||
                    name.contains("ola") ->

                TransactionCategory.TRANSPORT

            name.contains("amazon") ||
                    name.contains("flipkart") ||
                    name.contains("myntra") ->

                TransactionCategory.SHOPPING

            else ->

                TransactionCategory.TRANSFER
        }
    }
}