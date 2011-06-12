package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.MAX_ERRORS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.MT_GOX_PUBLIC;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_LABELS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_URLS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.SEKRET_MTGOX_KEY;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.davidgreene.minerstatus.beans.Mergable;
import me.davidgreene.minerstatus.beans.MtGox;
import me.davidgreene.minerstatus.beans.Result;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;
import me.davidgreene.minerstatus.util.StatusObjectFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import com.google.gson.Gson;

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
	        AsynchMinerUpdateTask updateTask = new AsynchMinerUpdateTask();
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
		        	AsynchMinerUpdateTask updateTask = new AsynchMinerUpdateTask();
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
		TableLayout mainTableLayout = (TableLayout) findViewById(R.id.statusLayout);
		mainTableLayout.removeAllViews();
		Boolean showMtGox = Boolean.valueOf(configService.getConfigValue("show.mtgox"));
		TableLayout mtGoxLayout = (TableLayout) findViewById(R.id.mtGoxLayout);
		if (showMtGox){
			mtGoxLayout.removeAllViews();
			mtGoxLayout.addView(createNewRow(new String[] {"Mt. Gox:"}, Boolean.TRUE));
			mtGoxLayout.addView(createNewRow(new String[] {"Vol","Last", "High","Low","Buy","Sell"}, Boolean.FALSE));
			try{
				Gson gson = new Gson();
				List<Result> mtGoxResult = minerService.readJsonData(SEKRET_MTGOX_KEY);
				MtGox mtGox = gson.fromJson(mtGoxResult.get(0).getData(), MtGox.class);
				mtGoxLayout.addView(createNewRow(new String[] {mtGox.getTicker().getVol().toString(),
						mtGox.getTicker().getLast().toString(), 
						mtGox.getTicker().getHigh().toString(), 
						mtGox.getTicker().getLow().toString(),
						mtGox.getTicker().getBuy().toString(),
						mtGox.getTicker().getSell().toString()
						}, Boolean.FALSE));				
			} catch (Exception e){
				mtGoxLayout.addView(createNewRow(new String[] {"Unable to connect..."}, Boolean.TRUE));
			}			
		} else {
			mtGoxLayout.setVisibility(TableLayout.INVISIBLE);
		}
		Cursor poolCursor = minerService.getPools();
		while(poolCursor.moveToNext()){
			String pool = poolCursor.getString(0);
	        Cursor cursor = minerService.getMiners(poolCursor.getString(0));
	        Boolean foundActiveRow = Boolean.FALSE;
	        List<Result> minerResultList = null;
	        
			while(cursor.moveToNext()) {
				Integer errors = cursor.getInt(1);
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
							try {
								((Mergable) status).mergeWith((Mergable) StatusObjectFactory.getStatusObject(minerResult.getData(), pool));
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
						status.setApiKey(apiKey);
					}
				} catch (Exception e){
					minerService.updateErrorCount(apiKey, (errors+1));
					if (errors >= MAX_ERRORS){					
						minerService.deleteMiner(apiKey);
						Toast.makeText(getApplicationContext(), "Miner ("+apiKey+") does not exist for pool("+pool+") or there was no response from the server.",
								Toast.LENGTH_LONG).show();	
						Toast.makeText(getApplicationContext(), "Max error count hit.  Miner removed: "+apiKey,
								Toast.LENGTH_LONG).show();					
						continue;
					} else {
						Toast.makeText(getApplicationContext(), "Miner ("+apiKey+") does not exist for pool("+pool+") or there was no response from the server.",
								Toast.LENGTH_LONG).show();	
						continue;
					}
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
			if (minerResultList != null && !minerResultList.isEmpty()){
		        mainTableLayout.addView(createNewRow(new String[] {DateFormat.getTimeInstance(DateFormat.MEDIUM).format(minerResultList.get(0).getDate())}, Boolean.TRUE));
			}
			mainTableLayout.addView(createNewRow(new String[] {""}, Boolean.FALSE));
		}
		if (poolCursor != null && !poolCursor.isClosed()) {
			poolCursor.close();
		}	
		try{
			this.setTitle("Miner Status - "+DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date(Long.valueOf(configService.getConfigValue("last.updated")))));
		} catch (Exception e) {
			this.setTitle("Miner Status");
		}

	}

	
	
	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			return true;
		}
	};
	
	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                        return new java.security.cert.X509Certificate[] {};
	                }

	                public void checkClientTrusted(X509Certificate[] chain,
	                                String authType) throws CertificateException {
	                }

	                public void checkServerTrusted(X509Certificate[] chain,
	                                String authType) throws CertificateException {
	                }
	        } };

	        // Install the all-trusting trust manager
	        try {
	                SSLContext sc = SSLContext.getInstance("TLS");
	                sc.init(null, trustAllCerts, new java.security.SecureRandom());
	                HttpsURLConnection
	                                .setDefaultSSLSocketFactory(sc.getSocketFactory());
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
	}
	
	private class AsynchMinerUpdateTask extends AsyncTask<Object, Integer, Boolean> {
		
		private final String tag = "TX_";
		
	    protected Boolean doInBackground(Object... params) {
	    	ConfigService configService = (ConfigService) params[0];
	    	MinerService minerService = (MinerService) params[1];
	    	
	    		String result="";
	    		Log.d(tag, "Status Update Start");
	    		HttpParams httpParameters = new BasicHttpParams();
	    		HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
	    		HttpConnectionParams.setSoTimeout(httpParameters, 5000);
	    		
	    		HttpClient httpClient = new DefaultHttpClient(httpParameters);
	    		HttpGet request;
	    		ResponseHandler<String> handler = new BasicResponseHandler();


	    		Boolean showMtGox = Boolean.valueOf(configService.getConfigValue("show.mtgox"));
	    		trustAllHosts();
	    		if (showMtGox){
	    			try{
	    				URL url = new URL(MT_GOX_PUBLIC);
	                    HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
	                    https.setHostnameVerifier(DO_NOT_VERIFY);
	    				https.connect();
	    				InputStream is = https.getInputStream();
	    				
	    				BufferedReader r = new BufferedReader(new InputStreamReader(is));
	    				StringBuilder httpsResponse = new StringBuilder();
	    				String line;
	    				while ((line = r.readLine()) != null) {
	    					httpsResponse.append(line);
	    				}
	    				
	    				
	    				minerService.addJsonData(SEKRET_MTGOX_KEY, httpsResponse.toString(), 0);
	    			} catch (Exception e){  
	    				Log.d(tag, e.getMessage());
	    			}			
	    		}
	    			
	    		Cursor poolCursor = minerService.getPools();
	    		while(poolCursor.moveToNext()){
	    			String pool = poolCursor.getString(0);

	    			
	    	        Cursor cursor = minerService.getMiners(poolCursor.getString(0));
	    			while(cursor.moveToNext()) {
	    				
	    				String apiKey = cursor.getString(0);
	    				List<String> poolUrls = Arrays.asList(POOL_URLS.get(pool));
	    				for(int poolIndex = 0; poolIndex< poolUrls.size(); poolIndex++){
		    				if (poolUrls.get(poolIndex).substring(0, 5).equals("https")){
		    					try {
				    				URL url = new URL(poolUrls.get(poolIndex).replace("%MINER%", apiKey));
				                    HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
				                    https.setHostnameVerifier(DO_NOT_VERIFY);
				    				https.connect();
				    				InputStream is = https.getInputStream();
				    				
				    				BufferedReader r = new BufferedReader(new InputStreamReader(is));
				    				StringBuilder httpsResponse = new StringBuilder();
				    				String line;
				    				while ((line = r.readLine()) != null) {
				    					httpsResponse.append(line);
				    				}
				    				result = httpsResponse.toString();
		    					} catch (Exception e){
		    						e.printStackTrace();
		    					}
		    				} else{
			    				request = new HttpGet(poolUrls.get(poolIndex).replace("%MINER%", apiKey));
			    	
			    				try{
			    					result = httpClient.execute(request, handler);
			    					if(result.contains("invalid") && result.contains("etcpasswd")){
			    						result = "";
			    					}
			    				} catch (Exception e){
			    					e.printStackTrace();
			    				}
		    				}
		    				minerService.addJsonData(apiKey, result, poolIndex);
	    				}
	    			}
	    			if (cursor != null && !cursor.isClosed()) {
	    				cursor.close();
	    			}		
	    		}
	    		if (poolCursor != null && !poolCursor.isClosed()) {
	    			poolCursor.close();
	    		}	
	    		httpClient.getConnectionManager().shutdown();	
	    		configService.setConfigValue("last.updated", Long.toString(System.currentTimeMillis()));
	    		return Boolean.TRUE;
	    	}
	    

        protected void onPostExecute(Boolean result) {
            getUserStatusUpdate();
        }
	}
}