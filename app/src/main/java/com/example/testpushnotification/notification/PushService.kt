package com.example.testpushnotification.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class PushService : FirebaseMessagingService() {

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.d("TEST", "token: $newToken")
    }
}