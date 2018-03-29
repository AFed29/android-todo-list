package com.example.alex.todolist.Notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.alex.todolist.Activities.TaskInfoActivity;
import com.example.alex.todolist.Activities.ToDoListActivity;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Utilities.ByteConverter;

import java.io.IOException;
import java.nio.channels.Channel;

/**
 * Created by Alex on 29/03/2018.
 */

public class TaskNotification {

    public static Notification notification(Context context, Task task) {
        Intent intent = new Intent(context, TaskInfoActivity.class);
                Intent mainActivityIntent = new Intent(context, ToDoListActivity.class);
                try {
                    intent.putExtra("byte", ByteConverter.serialize(task));
                } catch (IOException e) {
                    e.printStackTrace();
                }

        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                        .addNextIntentWithParentStack(mainActivityIntent)
                        .addNextIntent(intent)
                        .getPendingIntent(task.getId(), PendingIntent.FLAG_ONE_SHOT);


        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(task.getTaskName());
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) builder.setGroup("Tasks");
        builder.setPriority(Notification.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= 21) builder.setVibrate(new long[0]);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        return builder.build();

    }

}
