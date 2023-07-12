package com.example.testpushnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testpushnotification.notification.PushService

class MainActivity : AppCompatActivity() {

    private lateinit var pushBroadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras
                Log.d("TEST", "Message received")
                extras?.keySet()?.firstOrNull { it == PushService.KEY_ACTION }?.let { key ->
                    Log.d("TEST", "Action key -> $key")
                    when (extras.getString(key)) {
                        PushService.ACTION_SHOW_MESSAGE -> {
                            extras.getString(PushService.KEY_MESSAGE)?.let { message ->
                                Log.d("TEST", "Message key -> $message")
                                Toast.makeText(
                                    applicationContext,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        else -> Log.e("TEST", "No needed key found")
                    }
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(PushService.INTENT_FILTER)

        registerReceiver(pushBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(pushBroadcastReceiver)
    }
}