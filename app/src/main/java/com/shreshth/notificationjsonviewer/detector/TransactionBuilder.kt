package com.shreshth.notificationjsonviewer.detector

import com.shreshth.notificationjsonviewer.NotificationData
import com.shreshth.notificationjsonviewer.model.TransactionCandidate

object TransactionBuilder {

    fun build(
        notification: NotificationData
    ): TransactionCandidate {

        val type =
            TransactionDetector
                .detect(
                    notification.title,
                    notification.text
                )
                .type

        val amount =
            AmountExtractor
                .extract(
                    notification.text
                )

        val counterparty =
            CounterpartyExtractor
                .extract(
                    notification.text
                )
        val category =
            CategoryDetector.detect(
                counterparty
            )
        val confidence =
            when {

                amount != null &&
                        counterparty != null &&
                        type != TransactionType.UNKNOWN ->

                    ParseConfidence.HIGH

                amount != null &&
                        type != TransactionType.UNKNOWN ->

                    ParseConfidence.MEDIUM

                else ->

                    ParseConfidence.LOW
            }


        return TransactionCandidate(
            type = type,

            amount = amount,

            counterparty = counterparty,

            sourceApp =
                notification.packageName,
            sourceAppDisplayName =
                AppNameResolver.resolve(
                    notification.packageName
                ),

            timestamp =
                notification.timestamp,

            originalTitle =
                notification.title,

            originalText =
                notification.text,
            category =
                CategoryDetector.detect(
                    counterparty
                ) ,
            confidence = confidence
        )
    }
}