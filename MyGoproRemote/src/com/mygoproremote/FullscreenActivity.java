package com.mygoproremote;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gopro.core.GoProHelper;
import org.gopro.core.model.BacPacStatus;
import org.gopro.core.model.BackPack;
import org.gopro.core.model.CamFields;
import org.gopro.main.GoProApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FullscreenActivity extends Activity {

	public static String GoproPassword = "";
	public int refresh_status = 5;
	private static String URL = "http://10.5.5.9/bacpac/sd";
	String libFov[] = {"W","M","N"};
	String libPhotoRes[] = {"","","","5MP / M","7MP / W","12MP / W","7MP / M"};
	String libBurstRate[] = {"3/1 SEC","5/1 SEC","10/1 SEC","10/2 SEC","30/1 SEC","30/2 SEC","30/3 SEC"};
	String libVidres[] = {"WVGA","720","960","1080","1440","2.7K","4K","2.7K Cin","4K Cin"};
	String libFps[]={"12","15","24","25","30","48","50","60","100","120","240" };
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
	    final Button button_Record = (Button) findViewById(R.id.Record);
	    final Button button_Mode = (Button) findViewById(R.id.Mode);
	    final Button button_Preview = (Button) findViewById(R.id.Preview);
		TextView Status1 = (TextView) findViewById(R.id.status_ligne1);
		TextView Status4 = (TextView) findViewById(R.id.status_ligne4);
		TextView Status2 = (TextView) findViewById(R.id.status_ligne2);
		TextView tv = (TextView) findViewById(R.id.status_ligne3);
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ladyic.ttf");
	    tv.setTypeface(tf);
	    tv.setTextSize(68);
	    checkGopro();
	    GoproPassword = getPassword();
	    if (GoproPassword!="") {
//		    System.out.println("button_mode set clickable" );
	    	button_Mode.setFocusable(true);
		    final Timer timer = new Timer();
	        TimerTask updateProfile = new CustomTimerTask(FullscreenActivity.this);
	        timer.scheduleAtFixedRate(updateProfile, 0, refresh_status*1000);
	    	
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
	}
	 	
	
	//
	//  BOUCLE DE STATUS
	//
	//   
	public class CustomTimerTask extends TimerTask {
	   private Context context;
	   private Handler mHandler = new Handler();
	   // Write Custom Constructor to pass Context
	   public CustomTimerTask(Context con) {
	       this.context = con;
	   }
	   @Override
	   public void run() {
	       new Thread(new Runnable() {
	           @Override
	           public void run() {
	               mHandler.post(new Runnable() {
	                   @Override
	                   public void run() {
		                   	//
		                   	// Boucle de status
		                   	//
		       				GoProApi gopro = new GoProApi(GoproPassword);
		           			GoProHelper helper = gopro.getHelper();
		           			TextView Status1 = (TextView) findViewById(R.id.status_ligne1);
		           			TextView Status4 = (TextView) findViewById(R.id.status_ligne4);
		           			TextView Status2 = (TextView) findViewById(R.id.status_ligne2);
		           			TextView Status3 = (TextView) findViewById(R.id.status_ligne3);
		           			Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ladyic.ttf");
		           		    Status3.setTypeface(tf);
		           		    Status3.setTextSize(68);
	        				ImageView BatteryLeft = (ImageView) findViewById(R.id.image_battery);
	        				ImageView wifistatus = (ImageView) findViewById(R.id.Image_wifi);

		        		    System.out.println("Refresh status..." );
		        		    try {
	            				BackPack bacpack = helper.getBackPackInfo(); //bacpac/cv
	            				BacPacStatus bacpacStatus = helper.getBacpacStatus();
	            				CamFields camFields;
	            				Resources res = getResources();
	            				Drawable drawPower = res.getDrawable(R.drawable.iconepower4);
	            				Drawable drawwifi = res.getDrawable(R.drawable.iconwifi0);
	            				BatteryLeft.setImageDrawable(drawPower);
	            				int wifiLevel = bacpacStatus.getRSSI();
	            				if (bacpacStatus.isCameraPowerOn()) {
	            					
	            				} else {
			            			// affiche la batterie grise et vide le %
		            				BatteryLeft.setImageDrawable(drawPower);

	            				}
		            			switch (wifiLevel) {
	            				case 0 : drawwifi = res.getDrawable(R.drawable.iconwifi0); break;
	            				case 1 : drawwifi = res.getDrawable(R.drawable.iconwifi1); break;
	            				case 2 : drawwifi = res.getDrawable(R.drawable.iconwifi2); break;
	            				case 3 : drawwifi = res.getDrawable(R.drawable.iconwifi3); break;
	            				case 4 : drawwifi = res.getDrawable(R.drawable.iconwifi4); break;
	            				}
	            				wifistatus.setImageDrawable(drawwifi);

		        		    } catch (Exception e) {
								// TODO Auto-generated catch block
	            				System.out.println("Erreur reading settings from Gopro...");
	            				Status2.setText("Communication error");
								e.printStackTrace();	
		        		    }
	                   }
	           			
	               });
	           }
	       }).start();
	   }
	}

//	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onStop();
		System.exit(0);
	}
	
	public String getPassword() {
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
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(FullscreenActivity.this, "GoPro not found", Toast.LENGTH_LONG).show();
			System.out.println("GoPro not found..." );
			TextView Status2 = (TextView) findViewById(R.id.status_ligne2);
			Status2.setText("GoPro not found");
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
    public static boolean isPortOpen(final String ip, final int port, final int timeout) {
    	InetAddress in =null;
        try {
            in = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
        	e.printStackTrace();
        }
        try {
            if (in.isReachable(timeout)) { return true; } else { return false; }
        } catch (IOException e) {
            // TODO Auto-generated catch block
    		return false;
        }
    }
    public void checkGopro() {
    	boolean opened = false;
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		opened=isPortOpen("10.5.5.9",80,4000);
		if (opened) {
			System.out.println("Check gopro : found");
		} else { 
			System.out.println("Check gopro : not found");
		}
    }
    
}
