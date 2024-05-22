package com.dicoding.todoapp.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import com.dicoding.todoapp.setting.SettingsActivity
import com.dicoding.todoapp.ui.detail.DetailTaskActivity
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.NOTIFICATION_CHANNEL_ID
import com.dicoding.todoapp.utils.NOTIFICATION_ID
import com.dicoding.todoapp.utils.TASK_ID

class NotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private val channelName = inputData.getString(NOTIFICATION_CHANNEL_ID)

    private fun getPendingIntent(task: Task): PendingIntent? {
        val intent = Intent(applicationContext, DetailTaskActivity::class.java).apply {
            putExtra(TASK_ID, task.id)
        }
        return TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    override fun doWork(): Result {
        //TODO 14 : If notification preference on, get nearest active task from repository and show notification with pending intent
        val task = TaskRepository.getInstance(applicationContext).getNearestActiveTask()
        val message = applicationContext.resources.getString(R.string.notify_content, DateConverter.convertMillisToString(task.dueDateMillis))
        val title = task.title
        val notificationIntent = Intent(applicationContext, TaskActivity::class.java)

        val taskStackBuilder = android.app.TaskStackBuilder.create(applicationContext)
        taskStackBuilder.addParentStack(SettingsActivity::class.java)
        taskStackBuilder.addNextIntent(notificationIntent)

        val pendingIntent = getPendingIntent(task)
        val notificationManagerCompat = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notifications)
            .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_notifications))
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notification.flags = Notification.FLAG_AUTO_CANCEL or Notification.FLAG_ONGOING_EVENT
        notificationManagerCompat.notify(NOTIFICATION_ID, notification)
        return Result.success()
    }
}
