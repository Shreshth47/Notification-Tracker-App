package com.shreshth.notificationjsonviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationAdapter :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private var notifications = listOf<NotificationData>()
    private fun getAppName(
        context: android.content.Context,
        packageName: String
    ): String {

        return try {

            val pm = context.packageManager

            val appInfo =
                pm.getApplicationInfo(packageName, 0)

            pm.getApplicationLabel(appInfo).toString()

        } catch (e: Exception) {

            packageName

        }
    }
    fun submitList(newList: List<NotificationData>) {
        notifications = newList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val source: TextView =
            view.findViewById(R.id.tvSource)

        val message: TextView =
            view.findViewById(R.id.tvMessage)

        val time: TextView =
            view.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_notification,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = notifications[position]

        holder.source.text =
            item.title?.takeIf { it.isNotBlank() }
                ?: getAppName(holder.itemView.context, item.packageName)

        holder.message.text =
            item.text ?: "No message content"

        holder.time.text =
            "Time: ${
                SimpleDateFormat(
                    "dd MMM • hh:mm a",
                    Locale.getDefault()
                ).format(Date(item.timestamp))
            }"
    }

    override fun getItemCount() =
        notifications.size
}