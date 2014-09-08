package com.mygoproremote;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gopro.core.GoProHelper;
import org.gopro.core.model.BacPacStatus;
import org.gopro.main.GoProApi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class FullscreenActivity extends Activity {

	public static String GoproPassword = "";
	private static final String URL = "http://10.5.5.9/bacpac/sd";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
	    GoproPassword = getPassword();

	    final Button button_Record = (Button) findViewById(R.id.Record);
	    final Button button_Mode = (Button) findViewById(R.id.Mode);
	    final Button button_Preview = (Button) findViewById(R.id.Preview);
	    button_Preview.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		startActivity(new Intent(FullscreenActivity.this, PreviewActivity.class));    
        	}
		});
	    
	    button_Mode.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
    			GoProApi gopro = new GoProApi(GoproPassword);
    			GoProHelper helper = gopro.getHelper();
				try {
					BacPacStatus bacpacStatus = helper.getBacpacStatus();
					if (bacpacStatus.isCameraPowerOn()) {
								
						// la camera est on, donc on change l'eteind
						
						gopro.getHelper().turnOffCamera();
					}
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		        		System.out.println("exception1");
				}
				return false;
				}
		});
	    
	    button_Mode.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
    			GoProApi gopro = new GoProApi(GoproPassword);
    			GoProHelper helper = gopro.getHelper();
				try {
					BacPacStatus bacpacStatus = helper.getBacpacStatus();
					if (bacpacStatus.isCameraPowerOn()) {
							try {
								
								// la camera est on, donc on change le Mode
								
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
				        		System.out.println("exception1");
							}
					} else {
							try {
								gopro.getHelper().turnOnCamera();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
				        		System.out.println("exception2");
							}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
	    });
	}
	 

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onStop();
		System.exit(0);
	}

	public static String getPassword() {
		String  localObject = "", localObject1 = "";
		try {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response = null;
			response = httpclient.execute(new HttpGet(URL));
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				localObject1 = out.toString();
			}
			localObject=localObject1.substring(2);
		} catch (Exception localException) {
			System.out.println("erreur de traitement..." );
		}
		return (String) localObject;
	}
    public static boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
		}
    public static String getTwoDecimalsValue(long value)
    {
        if(value>=0 && value<=9) { return "0"+value; } else { return value+"";}
    }
	
}
