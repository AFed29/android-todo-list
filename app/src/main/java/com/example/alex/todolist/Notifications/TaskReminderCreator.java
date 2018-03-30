package com.example.alex.todolist.Notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.alex.todolist.Models.Task;

public class TaskReminderCreator {
    private TaskReminderCreator() {}

    public static void createReminder(Context context , Task task) {
        Notification notification = TaskNotification.notification(context, task);
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, task.getId());
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, task.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) (context.getSystemService(Context.ALARM_SERVICE));

        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, task.getReminderDateTime(), pendingIntent);
}

}
