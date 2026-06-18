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

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val btnEnableAccess =
            findViewById<Button>(R.id.btnEnableAccess)
        val recyclerView =
            findViewById<RecyclerView>(R.id.rvNotifications)
        val emptyState =
            findViewById<TextView>(R.id.tvEmptyState)

        adapter = NotificationAdapter()

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        lifecycleScope.launch {

            NotificationStore.notifications.collect { list ->

                adapter.submitList(list)

                if (list.isEmpty()) {
                    emptyState.visibility = View.VISIBLE
                } else {
                    emptyState.visibility = View.GONE
                }

            }
        }

        btnEnableAccess.setOnClickListener {

            val intent =
                Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)

            startActivity(intent)
        }

    }

}