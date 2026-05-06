package com.example.catalogoitalikacrm;
import android.app.*;import android.content.*;import androidx.core.app.NotificationCompat;
public class NotificationReceiver extends BroadcastReceiver{
 public void onReceive(Context c,Intent i){String t=i.getStringExtra("title"),m=i.getStringExtra("message");Intent o=new Intent(c,MainActivity.class);o.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
 PendingIntent pi=PendingIntent.getActivity(c,0,o,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
 NotificationCompat.Builder b=new NotificationCompat.Builder(c,MainActivity.CHANNEL_ID).setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle(t==null?"Recordatorio Italika":t).setContentText(m==null?"Tienes un recordatorio":m).setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true).setContentIntent(pi);
 ((NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE)).notify((int)System.currentTimeMillis(),b.build());}
}
