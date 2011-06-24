package me.davidgreene.minerstatus.tasks;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

public abstract class AsynchMinerUpdateTask extends AsyncTask<Object, Integer, Boolean> {
	
	private final String tag = "MinerUpdate_";
	private MinerService minerService;
	private ConfigService configService;
	
	
    protected abstract void onPostExecute(Boolean result);
	
    protected Boolean doInBackground(Object... params) {
    	configService = (ConfigService) params[0];
    	minerService = (MinerService) params[1];
        Integer connectionTimeout;
        try{
        	connectionTimeout = Integer.valueOf(configService.getConfigValue("connection.timeout"));
        } catch (NumberFormatException e){
        	connectionTimeout = 3000;
        }
		String result="";
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_TIMEOUT);
		
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpGet request;
		ResponseHandler<String> handler = new BasicResponseHandler();


		trustAllHosts();
	    for( String key : EXCHANGE_URLS.keySet() ){
	    	String value = EXCHANGE_URLS.get(key);
	    	if (Boolean.valueOf(configService.getConfigValue("show."+key))){
	    		fetchHttpsData(EXCHANGE_URLS.get(key), key, 0);
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
		                    https.setConnectTimeout(connectionTimeout);
		                    int tries = 0;
		    	            while (https.getResponseCode() < 0 && tries < 15){
		    	            	https.connect();
		    	            	tries++;
		    	            }
		    				InputStream is = https.getInputStream();
		    				
		    				BufferedReader r = new BufferedReader(new InputStreamReader(is), 1024);
		    				StringBuilder httpsResponse = new StringBuilder();
		    				String line;
		    				while ((line = r.readLine()) != null) {
		    					httpsResponse.append(line);
		    				}
		    				result = httpsResponse.toString();
		    				is.close();
		    				https.disconnect();
    					} catch (Exception e){
    						Log.d(tag, e.toString());
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

    private String fetchHttpsData(String urlString, String key, Integer poolIndex){
    	try{
			URL url = new URL(urlString);
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);
            Integer connectionTimeout;
            try{
            	connectionTimeout = Integer.valueOf(configService.getConfigValue("connection.timeout"));
            } catch (NumberFormatException e){
            	connectionTimeout = CONNECTION_TIMEOUT;
            }
            https.setConnectTimeout(connectionTimeout);
            https.connect();
            
			InputStream is = https.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(is), 1024);
			StringBuilder httpsResponse = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null) {
				httpsResponse.append(line);
			}
			minerService.addJsonData(key, httpsResponse.toString(), poolIndex);
			is.close();
			https.disconnect();
			return httpsResponse.toString();
			
		} catch (Exception e){  
			Log.d(tag, e.toString());
			return "";
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
    
}
