package com.shreshth.notificationjsonviewer

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import android.view.View
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var notificationAdapter: NotificationAdapter

    private lateinit var transactionAdapter:
            TransactionCandidateAdapter
    private var currentTab = "notifications"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        DatasetStore.initialize(this)

        val btnEnableAccess =
            findViewById<Button>(R.id.btnEnableAccess)
        val recyclerView =
            findViewById<RecyclerView>(R.id.rvNotifications)
        val emptyState =
            findViewById<TextView>(R.id.tvEmptyState)
        val btnNotifications =
            findViewById<Button>(
                R.id.btnNotifications
            )

        val btnTransactions =
            findViewById<Button>(
                R.id.btnTransactions
            )
        val btnExportDataset =
            findViewById<Button>(
                R.id.btnExportDataset
            )

        notificationAdapter =
            NotificationAdapter()

        transactionAdapter =
            TransactionCandidateAdapter()

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            notificationAdapter

        observeNotifications(emptyState)

        btnEnableAccess.setOnClickListener {

            val intent =
                Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)

            startActivity(intent)
        }
        btnNotifications.setOnClickListener {

            currentTab = "notifications"

            recyclerView.adapter =
                notificationAdapter

            notificationAdapter.submitList(
                NotificationStore.notifications.value
            )

            emptyState.text =
                "🔔\n\nWaiting for notifications..."
        }

        btnTransactions.setOnClickListener {

            currentTab = "transactions"

            recyclerView.adapter =
                transactionAdapter

            transactionAdapter.submitList(
                TransactionStore.transactions.value
            )

            emptyState.text =
                "💸\n\nWaiting for transactions..."
        }

        btnExportDataset.setOnClickListener {

            DatasetExporter.export(this)

        }

    }
    private fun observeNotifications(
        emptyState: TextView
    ) {

        lifecycleScope.launch {

            NotificationStore.notifications.collect { list ->

                if (currentTab == "notifications") {

                    notificationAdapter.submitList(list)

                    emptyState.visibility =
                        if (list.isEmpty())
                            View.VISIBLE
                        else
                            View.GONE
                }
            }
        }

        lifecycleScope.launch {

            TransactionStore.transactions.collect { list ->

                if (currentTab == "transactions") {

                    transactionAdapter.submitList(list)

                    emptyState.visibility =
                        if (list.isEmpty())
                            View.VISIBLE
                        else
                            View.GONE
                }
            }
        }
    }

    private fun observeTransactions(
        emptyState: TextView
    ) {

        lifecycleScope.launch {

            TransactionStore.transactions.collect { list ->

                transactionAdapter.submitList(list)

                emptyState.visibility =
                    if (list.isEmpty())
                        View.VISIBLE
                    else
                        View.GONE
            }
        }
    }

}