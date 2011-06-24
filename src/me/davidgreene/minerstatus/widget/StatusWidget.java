package me.davidgreene.minerstatus.widget;

import me.davidgreene.minerstatus.tasks.AsynchMinerUpdateTask;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class StatusWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, UpdateService.class));
    }

    public static class UpdateService extends Service {
        @Override
        public void onStart(Intent intent, int startId) {
        	RemoteViews updateViews = buildUpdate(this);
            ComponentName thisWidget = new ComponentName(this, StatusWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, updateViews);
        }

        public RemoteViews buildUpdate(Context context) {

            return null;
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
    
	private class MyAsynchMinerUpdateTask extends AsynchMinerUpdateTask{
		@Override
		protected void onPostExecute(Boolean result) {
			//
		}
	}
}