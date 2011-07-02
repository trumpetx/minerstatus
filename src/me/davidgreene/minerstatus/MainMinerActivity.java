package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.EXCHANGE_LABELS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.EXCHANGE_URLS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.MAX_ERRORS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_LABELS;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import me.davidgreene.minerstatus.beans.Exchange;
import me.davidgreene.minerstatus.beans.Mergable;
import me.davidgreene.minerstatus.beans.Result;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.tasks.AsynchMinerUpdateTask;
import me.davidgreene.minerstatus.util.ExchangeObjectFactory;
import me.davidgreene.minerstatus.util.StatusObjectFactory;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainMinerActivity extends AbstractMinerStatusActivity {

    private static final String tag = "TX";

	private boolean hasNetworkConnection() {
        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    HaveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    HaveConnectedMobile = true;
        }
        return HaveConnectedWifi || HaveConnectedMobile;
	}


    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        int bgColor = themeService.getTheme().getBackgroundColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.mainMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
        getUserStatusUpdate();
        if (!hasNetworkConnection()) {
        	setTitle("Miner Status - No Data Connection");
        } else{ 
	        setTitle("Miner Status - Updating...");
	        AsynchMinerUpdateTask updateTask = new MyAsynchMinerUpdateTask();
	        updateTask.execute(new Object[]{configService, minerService});
        }
    }	
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.miner_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.add_miner:
	        	if (!hasNetworkConnection()){
	        		setTitle("Miner Status - No Data Connection");
	        		Toast.makeText(this, "Please turn on 3/4G or Wifi", Toast.LENGTH_LONG).show();
	        	} else {
	        		startActivityForResult(new Intent(MainMinerActivity.this, AddMinerActivity.class), 0);
	        	}
	            break;
	        case R.id.fetch_status:   
	        	if (!hasNetworkConnection()){
	        		setTitle("Miner Status - No Data Connection");
	        		Toast.makeText(this, "Please turn on 3/4G or Wifi", Toast.LENGTH_LONG).show();
	        	} else {
		        	setTitle("Miner Status - Updating...");
		        	AsynchMinerUpdateTask updateTask = new MyAsynchMinerUpdateTask();
		            updateTask.execute(new Object[]{configService, minerService});
	        	}
	            break;
	        case R.id.options:
                startActivityForResult(new Intent(MainMinerActivity.this, OptionsActivity.class), 0);
	            break;	            
	        case R.id.about: 
	        	Toast.makeText(this, "Created by David Greene", Toast.LENGTH_LONG).show();
	            break;
	    }
	    return true;
	}
	
	private TableRow createNewRow(String[] columns, Boolean headerTextColor){
		TableRow tr = new TableRow(getApplicationContext());
		tr.setLayoutParams(new LayoutParams(
		                  LayoutParams.FILL_PARENT,
		                  LayoutParams.WRAP_CONTENT));	
		for(String str :columns){
			TextView col = new TextView(getApplicationContext());
			col.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
			col.setTextColor((headerTextColor) ? themeService.getTheme().getHeaderTextColor() : themeService.getTheme().getTextColor());
			col.setText(str);
			tr.addView(col);
			
		}
		return tr;
	}
	
	private TableRow createNewRow(final Status status){
		TableRow tr = new TableRow(getApplicationContext());
		tr.setLayoutParams(new LayoutParams(
		                  LayoutParams.FILL_PARENT,
		                  LayoutParams.WRAP_CONTENT));	
		for(String str : new String[]{status.getUsername(), status.getDisplayCol1(), status.getDisplayCol2()}){
			TextView col = new TextView(getApplicationContext());
			col.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
			col.setTextColor(themeService.getTheme().getTextColor());
			col.setText(str);
			tr.addView(col);
			
		}
		tr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("status", status);
	        	Intent intent = new Intent(MainMinerActivity.this, ViewMinerActivity.class);
				intent.putExtras(bundle);
	        	startActivityForResult(intent, 0);
			}
		});
		return tr;
	}
	

	
	private void getUserStatusUpdate(){
		Log.d(tag, "Status Update Start");
		try {
			TableLayout mainTableLayout = (TableLayout) findViewById(R.id.statusLayout);
			mainTableLayout.removeAllViews();
			TableLayout tickerLayout = (TableLayout) findViewById(R.id.tickerLayout);	
			Boolean atLeastOneExchange = Boolean.FALSE;
		    for( String key : EXCHANGE_URLS.keySet() ){
		    	if (Boolean.valueOf(configService.getConfigValue("show."+key))){
		    		try{
		    			List<Result> result = minerService.readJsonData(key);
		    			Exchange exchange = ExchangeObjectFactory.getStatusObject(result.get(0).getData(), key);
						if (!atLeastOneExchange){
							atLeastOneExchange = Boolean.TRUE;
							tickerLayout.removeAllViews();
							tickerLayout.addView(createNewRow(new String[] {"Tickers:"}, Boolean.TRUE));
							tickerLayout.addView(createNewRow(new String[] {"","Last", "High","Low","Buy","Sell"}, Boolean.FALSE));
		    			}		    			
						tickerLayout.addView(createNewRow(new String[] {
								EXCHANGE_LABELS.get(key),
								exchange.getTicker().getLastString(), 
								exchange.getTicker().getHighString(), 
								exchange.getTicker().getLowString(),
								exchange.getTicker().getBuyString(),
								exchange.getTicker().getSellString()
								}, Boolean.FALSE));	

		    		} catch (Exception e){
		    			tickerLayout.addView(createNewRow(new String[] {EXCHANGE_LABELS.get(key)+":", "Unable", "to", "connect","..."}, Boolean.FALSE));
		    		}
		    	}
		    }    	
			if (!atLeastOneExchange){
				tickerLayout.removeAllViews();
				tickerLayout.setVisibility(TableLayout.INVISIBLE);
			}
			Cursor poolCursor = minerService.getPools();
			while(poolCursor.moveToNext()){
				String pool = poolCursor.getString(0);
		        Cursor cursor = minerService.getMiners(poolCursor.getString(0));
		        Boolean foundActiveRow = Boolean.FALSE;
		        List<Result> minerResultList = null;
		        
				while(cursor.moveToNext()) {
					int errors = cursor.getInt(1);
					String apiKey = cursor.getString(0);
					
					minerResultList = minerService.readJsonData(apiKey);
					Status status = null;
					try{
						if (minerResultList == null || minerResultList.isEmpty() || minerResultList.get(0).getData().equals("")){
							throw new Exception("No JSON Data");
						}
						for(Result minerResult : minerResultList){
							if (status == null){
								status = StatusObjectFactory.getStatusObject(minerResult.getData(), pool);
							} else {
								((Mergable) status).mergeWith((Mergable) StatusObjectFactory.getStatusObject(minerResult.getData(), pool));
							}
							status.setApiKey(apiKey);
						}
					} catch (Exception e){
						minerService.updateErrorCount(apiKey, (errors+1));
			            int maxErrors;
			            try{
			            	maxErrors = Integer.valueOf(configService.getConfigValue("max.errors"));
			            } catch (NumberFormatException nfe){
			            	maxErrors = MAX_ERRORS;
			            }	
						Toast.makeText(getApplicationContext(), "Miner ("+apiKey+") does not exist for pool("+pool+") or there was no response from the server.",
								Toast.LENGTH_LONG).show();	
						if (errors >= maxErrors && maxErrors != 0){					
							minerService.deleteMiner(apiKey);
	
							Toast.makeText(getApplicationContext(), "Max error count hit("+maxErrors+").  Miner removed: "+apiKey,
									Toast.LENGTH_LONG).show();	
						}
						continue;
					}
					//reset errors after a successful fetch
					minerService.updateErrorCount(apiKey, 0);
					
					if (!foundActiveRow){
						foundActiveRow = Boolean.TRUE;
				        mainTableLayout.addView(createNewRow(new String[] {POOL_LABELS.get(pool)+":"}, Boolean.TRUE));
				        mainTableLayout.addView(createNewRow(new String[] {status.getUsernameLabel(), status.getDisplayCol1Label(), status.getDisplayCol2Label()}, Boolean.FALSE));
					}
					
					//Add to stack so we can pop off the last one if needed
					mainTableLayout.addView(createNewRow(status));
				}
				
				if (cursor != null && !cursor.isClosed()) {
					cursor.close();
				}		
				if (minerResultList != null && !minerResultList.isEmpty() && foundActiveRow){
			        mainTableLayout.addView(createNewRow(new String[] {DateFormat.getTimeInstance(DateFormat.MEDIUM).format(minerResultList.get(0).getDate())}, Boolean.TRUE));
			        mainTableLayout.addView(createNewRow(new String[] {""}, Boolean.FALSE));
				}
			}
			if (poolCursor != null && !poolCursor.isClosed()) {
				poolCursor.close();
			}	
		try{
			this.setTitle("Miner Status - "+DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date(Long.valueOf(configService.getConfigValue("last.updated")))));
		} catch (Exception e) {
			this.setTitle("Miner Status");
		}
		} catch (final RuntimeException e){
			AlertDialog.Builder alert = new AlertDialog.Builder(MainMinerActivity.this);
			alert.setTitle("MinerStatus broke something!");
			alert.setPositiveButton("Ignore & Continue", new DialogInterface.OnClickListener() {	
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getApplicationContext(), "I ate the error for you (YUM).  If you would like to help debug MinerStatus, throw the Exception once and make sure you report it.  Thanks!",
							Toast.LENGTH_LONG).show();
				}
			});		
			alert.setNegativeButton("Throw Exception", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					throw e;
				}
			});				
			alert.show();  
		}
	}
	
	private class MyAsynchMinerUpdateTask extends AsynchMinerUpdateTask{
		@Override
		protected void onPostExecute(Boolean result) {
			getUserStatusUpdate();
		}
	}
}