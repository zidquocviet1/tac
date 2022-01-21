package com.mqv.realtimechatapplication.activity.br

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mqv.realtimechatapplication.dependencies.AppDependencies

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        AppDependencies.getMessageSenderProcessor().shouldRetrySeenMessages()
    }
}