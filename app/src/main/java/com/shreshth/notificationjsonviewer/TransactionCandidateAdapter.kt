package com.shreshth.notificationjsonviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shreshth.notificationjsonviewer.detector.TransactionType
import com.shreshth.notificationjsonviewer.model.TransactionCandidate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionCandidateAdapter :
    RecyclerView.Adapter<TransactionCandidateAdapter.ViewHolder>() {

    private var transactions =
        listOf<TransactionCandidate>()

    fun submitList(
        newList: List<TransactionCandidate>
    ) {
        transactions = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        val tvType: TextView =
            view.findViewById(R.id.tvType)

        val tvAmount: TextView =
            view.findViewById(R.id.tvAmount)

        val tvCounterparty: TextView =
            view.findViewById(R.id.tvCounterparty)

        val tvCategory: TextView =
            view.findViewById(R.id.tvCategory)

        val tvSource: TextView =
            view.findViewById(R.id.tvSource)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_transaction,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item =
            transactions[position]

        holder.tvType.text =
            if (
                item.type ==
                TransactionType.INCOME
            ) {
                "🟢 INCOME"
            } else {
                "🔴 EXPENSE"
            }

        holder.tvAmount.text =
            "₹${item.amount ?: 0}"

        holder.tvCounterparty.text =
            item.counterparty
                ?: "Unknown"

        holder.tvCategory.text =
            item.category.name

        holder.tvSource.text =
            "${item.sourceAppDisplayName} • ${
                SimpleDateFormat(
                    "dd MMM • hh:mm a",
                    Locale.getDefault()
                ).format(
                    Date(item.timestamp)
                )
            }"
    }

    override fun getItemCount() =
        transactions.size
}