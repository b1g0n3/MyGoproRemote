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
import android.graphics.drawable.AnimationDrawable;
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
	String libPhotoRes[] = {"","","","5MP","7MP","12MP","7MP"};
	String libPhotoAngle[] = {"","","","M","W","W","M"};
	String libBurstRate[] = {"3/1 SEC","5/1 SEC","10/1 SEC","10/2 SEC","30/1 SEC","30/2 SEC","30/3 SEC"};
	String libVidres[] = {"WVGA","720","960","1080","1440","2.7K","4K","2.7K Cin","4K Cin"};
	String libFps[]={"12","15","24","25","30","48","50","60","100","120","240" };
	public String camname,version;
	public int Model=0,lastmode=0,cammode=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
	    Button button_Record = (Button) findViewById(R.id.Record);
	    Button button_Mode = (Button) findViewById(R.id.Mode);
	    Button button_Preview = (Button) findViewById(R.id.Preview);
		TextView Status1 = (TextView) findViewById(R.id.status_ligne1);
		TextView Status4 = (TextView) findViewById(R.id.status_ligne4);
		TextView Status2 = (TextView) findViewById(R.id.status_ligne2);
		TextView tv = (TextView) findViewById(R.id.status_ligne3);
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ladyic.ttf");
	    tv.setTypeface(tf);
	    tv.setTextSize(68);
	//	ImageView red_poweron = (ImageView)findViewById(R.id.red);
	//	red_poweron.setBackgroundResource(R.drawable.led_red_poweron);
	//	final AnimationDrawable poweronAnimation = (AnimationDrawable) red_poweron.getBackground();
	//	ImageView red_poweroff = (ImageView)findViewById(R.id.red);
	//	red_poweroff.setImageResource(R.drawable.led_red_poweroff);
	//	final AnimationDrawable poweroffAnimation = (AnimationDrawable) red_poweroff.getDrawable();
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
									
							// la camera est on, donc on l'eteind
						//	poweroffAnimation.start();
							gopro.getHelper().turnOffCamera();
							System.out.println("turnOff");
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
						//			poweronAnimation.start();
									System.out.println("turnOn");
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
		           		    Button button_Record = (Button) findViewById(R.id.Record);
		           		    Button button_Mode = (Button) findViewById(R.id.Mode);
		           		    Button button_Preview = (Button) findViewById(R.id.Preview);
		           			Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ladyic.ttf");
		           		    Status3.setTypeface(tf);
		           		    Status3.setTextSize(68);
	        				ImageView BatteryLeft = (ImageView) findViewById(R.id.image_battery);
	        				ImageView wifistatus = (ImageView) findViewById(R.id.Image_wifi);
	        				ImageView image_mode = (ImageView) findViewById(R.id.image_mode);
	        				ImageView led_red = (ImageView) findViewById(R.id.red);
	        			//	ImageView red_record = (ImageView)findViewById(R.id.red);
	        			//	red_record.setImageResource(R.drawable.led_red_record);
	        			//	AnimationDrawable recordAnimation = (AnimationDrawable) red_record.getBackground();
		        		 //   System.out.println("Refresh status..." );
		        		    try {
	            				BackPack bacpack = helper.getBackPackInfo(); //bacpac/cv
	            				BacPacStatus bacpacStatus = helper.getBacpacStatus();
	            				CamFields camFields;
	            				Resources res = getResources();

	            				Drawable drawwifi = res.getDrawable(R.drawable.iconwifi0);
	            				//BatteryLeft.setImageDrawable(drawPower);
	            				int wifiLevel = bacpacStatus.getRSSI();
	            				if (bacpacStatus.isCameraPowerOn()) {
	            			//		System.out.println("isCameraPowerOn(Yes)" );
	            					try {
	         							camFields=helper.getCameraInfo();
										Model = camFields.getModel();
										version = camFields.getVersion();
										camname = camFields.getCamname();
									} catch (Exception e2) { e2.printStackTrace(); 	}
									camFields = helper.getCameraSettings();
									int getBattery = camFields.getBattery();
		            				camFields = helper.getCameraSettingsExtended();
		            				int BatteryOn = camFields.getBattery();
		            				int currentMode = camFields.getMode();
		            				int currentAngle = camFields.getFieldOfView();
		            				int currentPhotoRes = camFields.getPhotoResolution();
		            				int currentBurstRate = camFields.getBurstRate();
		            				int currentVideoRes =camFields.getVidres();
		            				int currentfps = camFields.getFramesPerSecond();
		            				int currentTimelapse = camFields.getTimeLapse();
		            				long currentNbrePhotos = 0; String ScurrentNbrePhotos;
		            				currentNbrePhotos = camFields.getPhotosOncard();
		            				if (currentNbrePhotos<10) { ScurrentNbrePhotos="0"+String.valueOf(currentNbrePhotos); }
		            				else { ScurrentNbrePhotos=String.valueOf(currentNbrePhotos); }
		            				long currentNbreVideos = 0; String ScurrentNbreVideos;
		            				currentNbreVideos = camFields.getVideoOncard();
		            				if (currentNbreVideos<10) { ScurrentNbreVideos="0"+String.valueOf(currentNbreVideos); }
		            				else { ScurrentNbreVideos=String.valueOf(currentNbreVideos); }
		            				long FreePhotos = camFields.getPhotosAvailable();
		            				long FreeVideos = camFields.getVideoAvailable();
		            				long hours = FreeVideos / 60; //since both are ints, you get an int
		            				long minutes = FreeVideos % 60;
		            				String FreeTime = String.format("%d H %02d", hours, minutes);
		            				long Recording = camFields.getPlaybackPos();
		            				String libRecording = getTwoDecimalsValue(Recording/3600) + ":" + getTwoDecimalsValue(Recording/60) + ":" +getTwoDecimalsValue(Recording%60);
		            				String libTimelapse;
		            				if (currentTimelapse == 0) { libTimelapse="0.5"; } else { libTimelapse = String.valueOf(currentTimelapse); }
		            				Drawable drawPower = res.getDrawable(R.drawable.iconepower4);
		            				if (isBetween(getBattery, 75, 100))  drawPower = res.getDrawable(R.drawable.iconepower3); 
		            				if (isBetween(getBattery, 50, 74))   drawPower = res.getDrawable(R.drawable.iconepower2); 
		            				if (isBetween(getBattery, 25, 49))   drawPower = res.getDrawable(R.drawable.iconepower1); 
		            				if (isBetween(getBattery, 10, 24))   drawPower = res.getDrawable(R.drawable.iconepower0); 
		            				if (isBetween(getBattery, 0, 9))     drawPower = res.getDrawable(R.drawable.iconepower00);
		            				if (BatteryOn==4) drawPower = res.getDrawable(R.drawable.iconepowerac);
		            		//		battery_value.setText(getBattery+"%");
		            				BatteryLeft.setImageDrawable(drawPower);
		            				// affiche le bouton bleu
		            		//		button_power.setBackgroundResource(R.drawable.iconpoweron);
			            			// bouton preview est OFF
			            		//	toggle_Preview .setBackgroundResource(R.drawable.icontools_off);
		            				button_Preview.setClickable(true);
			            			// bouton tools est ON
			            			button_Record.setBackgroundResource(R.drawable.iconrecord);
			            			button_Record.setClickable(true);
			            			// bouton mode est ON		            			
			            			lastmode=currentMode;
			            			switch (currentMode) { 
			            				case 0 :  	image_mode.setBackgroundResource(R.drawable.mode1);
			            							if (Recording==0) { Status2.setText(libVidres[currentVideoRes]+" / "+libFps[currentfps]); 
			            								Status1.setText(libFov[currentAngle]);
			            								Drawable drawPower = res.getDrawable(R.drawable.iconepower4);
			            							//	if (recordAnimation.isRunning()) { recordAnimation.stop(); }
			            								Status3.setText(ScurrentNbreVideos); }
			            							else { Status2.setText(libVidres[currentVideoRes]+" / "+libFps[currentfps]+" / "+libFov[currentAngle]); 
			            								Status2.setText("RECORDING...");
			            							//	if (!recordAnimation.isRunning()) { recordAnimation.start(); }
			            								Status3.setText(libRecording); }
			            							Status4.setText(FreeTime);
			            							break;  
			            				case 1 :  	image_mode.setBackgroundResource(R.drawable.mode2);
			            							Status1.setText(libPhotoAngle[currentPhotoRes]);
			            							Status2.setText(libPhotoRes[currentPhotoRes]);
			            							Status3.setText(ScurrentNbrePhotos);
			            							Status4.setText(String.valueOf(FreePhotos));
			            							break;  
			            				case 2 :  	image_mode.setBackgroundResource(R.drawable.mode3); 
			            							Status1.setText(libPhotoAngle[currentPhotoRes]);
			            							Status2.setText(libPhotoRes[currentPhotoRes]+"  -  "+libBurstRate[currentBurstRate]); 
			            							Status3.setText(ScurrentNbrePhotos);
			            							Status4.setText(String.valueOf(FreePhotos));
			            							break;  
			            				case 3 :  	image_mode.setBackgroundResource(R.drawable.mode4); 
			            							Status1.setText(libPhotoAngle[currentPhotoRes]);			
			            							Status2.setText(libPhotoRes[currentPhotoRes]+"  -  "+libTimelapse+" SEC");
			            							Status3.setText(String.valueOf(currentNbrePhotos));
			            							Status4.setText(String.valueOf(FreePhotos));
			            							break;  
			            			}
			            			if (currentNbreVideos==65535) { Status3.setText(""); }
			            			BatteryLeft.setVisibility(View.VISIBLE);
			            			wifistatus.setVisibility(View.VISIBLE);
			            			Status1.setVisibility(View.VISIBLE);
			            			Status2.setVisibility(View.VISIBLE);
			            			Status3.setVisibility(View.VISIBLE);
			            			Status4.setVisibility(View.VISIBLE);
			            			image_mode.setVisibility(View.VISIBLE);

	            				} else {
	            					wifistatus.setVisibility(View.INVISIBLE);
	            					BatteryLeft.setVisibility(View.INVISIBLE);
			            			Status1.setVisibility(View.INVISIBLE);
			            			Status2.setVisibility(View.INVISIBLE);
			            			Status3.setVisibility(View.INVISIBLE);
			            			Status4.setVisibility(View.INVISIBLE);
			            			image_mode.setVisibility(View.INVISIBLE);
	            			//		System.out.println("isCameraPowerOn(No)" );
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
