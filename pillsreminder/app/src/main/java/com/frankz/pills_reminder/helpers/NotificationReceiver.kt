package com.frankz.pills_reminder.helpers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.frankz.pills_reminder.models.Medicament

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "treatments"

        val medicamentName = intent?.getStringExtra("medicamentName") ?: ""
        val medicamentId = intent?.getStringExtra("medicamentId") ?: ""

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Pills Reminder")
            .setContentText("Es hora de tomar ${medicamentName}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notification = builder.build()
        val notificationId = medicamentId.hashCode()
        notificationHelper.notify(notificationId, notification)
    }
}