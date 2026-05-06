package com.example.catalogoitalikacrm;
import android.Manifest;import android.app.*;import android.content.*;import android.content.pm.PackageManager;import android.os.*;import android.webkit.*;
public class MainActivity extends Activity{
 public static final String CHANNEL_ID="italika_crm_channel"; WebView webView;
 public void onCreate(Bundle b){super.onCreate(b);channel();perm();webView=new WebView(this);setContentView(webView);
 webView.getSettings().setJavaScriptEnabled(true);webView.getSettings().setDomStorageEnabled(true);webView.getSettings().setAllowFileAccess(true);webView.getSettings().setAllowContentAccess(true);
 webView.addJavascriptInterface(new Bridge(this),"AndroidBridge");webView.setWebChromeClient(new WebChromeClient());webView.setWebViewClient(new WebViewClient());webView.loadUrl("file:///android_asset/index.html");}
 void perm(){if(Build.VERSION.SDK_INT>=33&&checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},7);}
 void channel(){if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel(CHANNEL_ID,"Recordatorios Italika",NotificationManager.IMPORTANCE_HIGH);((NotificationManager)getSystemService(NotificationManager.class)).createNotificationChannel(c);}}
 public void onBackPressed(){if(webView!=null&&webView.canGoBack())webView.goBack();else super.onBackPressed();}
 public static class Bridge{Context c;Bridge(Context c){this.c=c;}@JavascriptInterface public void scheduleNotification(String id,String title,String msg,long time){
 if(time<=System.currentTimeMillis())return;Intent i=new Intent(c,NotificationReceiver.class);i.putExtra("title",title);i.putExtra("message",msg);
 PendingIntent p=PendingIntent.getBroadcast(c,Math.abs(id.hashCode()),i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
 AlarmManager a=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);if(a!=null){if(Build.VERSION.SDK_INT>=23)a.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,time,p);else a.setExact(AlarmManager.RTC_WAKEUP,time,p);}}}
}
