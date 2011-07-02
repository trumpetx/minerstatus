package me.davidgreene.minerstatus.widget;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import me.davidgreene.minerstatus.MainMinerActivity;
import me.davidgreene.minerstatus.MinerStatusApp;
import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.beans.Mergable;
import me.davidgreene.minerstatus.beans.Result;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;
import me.davidgreene.minerstatus.tasks.AsynchMinerUpdateTask;
import me.davidgreene.minerstatus.util.MinerStatusConstants;
import me.davidgreene.minerstatus.util.StatusObjectFactory;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class StatusWidget extends AppWidgetProvider {
	
	private static final String tag = "SW";
	
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, UpdateService.class));
    }

    public static class UpdateService extends Service {
    	
        private AsynchMinerUpdateTask myAsynchMinerUpdateTask;
        private MinerService minerService;
        private ConfigService configService;
    	
        @Override
        public void onStart(Intent intent, int startId) {
        	if (minerService == null)
        		minerService = ((MinerStatusApp)this.getApplicationContext()).getMinerService();
        	if (configService == null)
        		configService = ((MinerStatusApp)this.getApplicationContext()).getConfigService();	
        	myAsynchMinerUpdateTask = new MyAsynchMinerUpdateTask();
        	myAsynchMinerUpdateTask.execute(new Object[]{configService, minerService, new String[]{configService.getConfigValue("widget.apiKey")} });
        	
        	//TODO: Figure out how to have multiple widgets!

        	
        }
        
        

        public void buildUpdate(Context context) {
        	RemoteViews updateView = new RemoteViews(context.getPackageName(), R.layout.status_message);
        	
        	try {
	        	String apiKey = configService.getConfigValue("widget.apiKey");
	        	if (apiKey.equals("none") || apiKey.equals("")){
	        		throw new Exception("No apiKey");
	        	}
	        	String pool = minerService.getPoolForMiner(apiKey);
	        	if (pool == null || pool.equals("")){
	        		throw new Exception("No pool");
	        	}
	        	List<Result> minerResultList = minerService.readJsonData(apiKey);
	        	Status status = null;
				for(Result minerResult : minerResultList){
					if (status == null){
						status = StatusObjectFactory.getStatusObject(minerResult.getData(), pool);
					} else {
						((Mergable) status).mergeWith((Mergable) StatusObjectFactory.getStatusObject(minerResult.getData(), pool));
					}
					status.setApiKey(apiKey);
					updateView.setTextViewText(R.id.pool, MinerStatusConstants.POOL_LABELS.get(pool));
					updateView.setTextViewText(R.id.col1label, status.getDisplayCol1Label()+ ": ");
		        	updateView.setTextViewText(R.id.col1, status.getDisplayCol1());
					updateView.setTextViewText(R.id.col2label, status.getDisplayCol2Label()+ ": ");
					updateView.setTextViewText(R.id.col2, status.getDisplayCol2());
					updateView.setTextViewText(R.id.updated, DateFormat.getTimeInstance(DateFormat.SHORT).format(minerResultList.get(0).getDate()));
				}
				sendNotification(status);
					
        	} catch (Exception e){
        		Log.d(tag, e.toString());
        	}        
        	
            ComponentName thisWidget = new ComponentName(this, StatusWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, updateView);
            
        }
        
        private static final int MINER_NOTIFICATION_ID = 309392;
        
        private void sendNotification(Status status){
			String lowHashrateNotificationConfig = configService.getConfigValue("low.hashrate.notification");
			if (!lowHashrateNotificationConfig.toLowerCase().equals("-1")){
				BigDecimal lowHashrateNotification = BigDecimal.ZERO;
				try{
					lowHashrateNotification = new BigDecimal(lowHashrateNotificationConfig);
				} catch (Exception e){
					//Leave value at 0
				}
				if (status.getTotalHashrate().compareTo(lowHashrateNotification) != 1){
					//send notification
					NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					
					int icon = R.drawable.icon;
					CharSequence tickerText = "Hashrate: "+status.getTotalHashrate().toString();
					long when = System.currentTimeMillis();

					Notification notification = new Notification(icon, tickerText, when);
					
					Context context = getApplicationContext();
					CharSequence contentTitle = "Hashrate Alert";
					CharSequence contentText = "Your Hashrate has dropped to: "+status.getTotalHashrate().toString();
					Intent notificationIntent = new Intent(this, MainMinerActivity.class);
					PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
					notification.defaults |= Notification.DEFAULT_VIBRATE;
					notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
					
					mNotificationManager.notify(MINER_NOTIFICATION_ID, notification);
					
				} else {
					//good to go, reset notification count TODO
				}
			}
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
        
    	private class MyAsynchMinerUpdateTask extends AsynchMinerUpdateTask{
    		@Override
    		protected void onPostExecute(Boolean result) {
    			buildUpdate(UpdateService.this);
    		}
    	}

		@Override
		public void onDestroy() {
			super.onDestroy();
			this.myAsynchMinerUpdateTask.cancel(true);
			this.myAsynchMinerUpdateTask = null;
		}
    }
    

}