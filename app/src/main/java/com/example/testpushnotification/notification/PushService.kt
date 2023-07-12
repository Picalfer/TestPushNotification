package com.example.testpushnotification.notification

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushService : FirebaseMessagingService() {

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.d("TEST", "token: $newToken")

        // send registration token to server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(INTENT_FILTER)
        message.data.forEach {entity ->
            intent.putExtra(entity.key, entity.value)
        }

        sendBroadcast(intent)
    }

    companion object {
        const val INTENT_FILTER = "PUSH_EVENT"
        const val KEY_ACTION = "action"
        const val KEY_MESSAGE = "message"

        const val ACTION_SHOW_MESSAGE = "show_message"
    }
}