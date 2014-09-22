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

	static String GoproPassword = "";
	public int refresh_status = 2;
	public int timeout = 2;
	
	private static String URL = "http://10.5.5.9/bacpac/sd";
	String libFov[] = {"W","M","N"};
	String libPhotoRes[] = {"11MP","8MP","5MP","5MP","7MP","12MP","7MP"};
	String libPhotoAngle[] = {"W","M","W","M","W","W","M"};
	String libBurstRate[] = {"3/1 SEC","5/1 SEC","10/1 SEC","10/2 SEC","30/1 SEC","30/2 SEC","30/3 SEC"};
	String libVidres[] = {"WVGA","720","960","1080","1440","2.7K","4K","2.7K Cin","4K Cin","1080Super","720Super"};
	String libFps[]={"12","15","24","25","30","48","50","60","100","120","240" };
	public String camname,version;
	public int Model=0,lastmode=0,cammode=0,currentshutter=0,busy_error=0;
	Drawable ledred,ledblue,exposure,balence;
	Handler mHandler;
	Timer timer;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
	    final Button button_Record = (Button) findViewById(R.id.Record);
	    final Button button_Mode = (Button) findViewById(R.id.Mode);
	    final Button button_Preview = (Button) findViewById(R.id.Preview);
    	button_Preview.setEnabled(false);
    	button_Preview.setClickable(false);
    	button_Preview.setFocusable(false);
    	button_Record.setEnabled(false);
    	button_Record.setClickable(false);
    	button_Record.setFocusable(false);
    	button_Mode.setEnabled(false);
    	button_Mode.setClickable(false);
    	button_Mode.setFocusable(false);
		TextView tv = (TextView) findViewById(R.id.status_ligne3);
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ladyic.ttf");
	    tv.setTypeface(tf);
	    tv.setTextSize(100);
		checkGopro();
    	GoproPassword = getPassword();
	    if (GoproPassword!="") {
	    	button_Mode.setEnabled(true);
	    	button_Mode.setClickable(true);
	    	button_Mode.setFocusable(true);
	    	ImageView led_blue = (ImageView) findViewById(R.id.blue);
	    	Resources res = getResources();
	    	ledblue = res.getDrawable(R.drawable.led_blue_on);
			led_blue.setImageDrawable(ledblue);

		    button_Preview.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		System.out.println("Preview On");
	        		timer.cancel();
	        		Intent i = new Intent(getApplicationContext(), PreviewActivity.class);
	        		i.putExtra("busy_activite",1);
	        		startActivity(i);
	        	}
			});

		    button_Record.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	    			GoProApi gopro = new GoProApi(GoproPassword);
	    			if (currentshutter==0) {
		        		try {
			        		System.out.println("Record On");
		        			gopro.getHelper().startRecord();
		        	    	button_Preview.setEnabled(false);
		        	    	button_Preview.setClickable(false);
		        	    	button_Preview.setFocusable(false);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error in /Record On/");
						}
	    			} else {
		        		try {
			        		System.out.println("Record Off");
		        			gopro.getHelper().stopRecord();
		        	    	button_Preview.setEnabled(true);
		        	    	button_Preview.setClickable(true);
		        	    	button_Preview.setFocusable(true);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error in /Record Off/");
						}
	    				
	    			}
	        	}
			});
		    
		    button_Mode.setOnLongClickListener(new View.OnLongClickListener() {			
				@Override
				public boolean onLongClick(View v) {
					// TODO auto-generated method stub
	    			GoProApi gopro = new GoProApi(GoproPassword);
	    			GoProHelper helper = gopro.getHelper();
					try {
						BacPacStatus bacpacStatus = helper.getBacpacStatus();
						if (bacpacStatus.isCameraPowerOn()) {
							// la camera est on, donc on l'eteind
							gopro.getHelper().turnOffCamera();
							System.out.println("Power Off");
						}
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
			        		System.out.println("Error in /Power Off/");
					}
					return true;
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
									System.out.println("Mode suivant");
									// la camera est on, donc on change le Mode
									switch (lastmode) {
									case 0 : gopro.getHelper().modePhoto(); 
											break;
									case 1 : gopro.getHelper().modeBurst();
										break;
									case 2 : gopro.getHelper().timelapse1();
										break;
									default: gopro.getHelper().modeCamera();
										break;
									}	
									
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
					        		System.out.println("exception1");
							}
						} else {
								try {
									gopro.getHelper().turnOnCamera();
									System.out.println("Power On");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
					        		System.out.println("Error in /Power On/");
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
	@Override
    public void onResume()
    {
        super.onResume();
	    timer = new Timer();
        TimerTask updateProfile = new CustomTimerTask(FullscreenActivity.this);
        timer.scheduleAtFixedRate(updateProfile, 0, refresh_status*1000);
    }
	
	//
	//  BOUCLE DE STATUS
	//
	//   

	class CustomTimerTask extends TimerTask {
	   private Context context;
	   private Handler mHandler = new Handler();
	   public CustomTimerTask(Context con) {
	       this.context = con;
	   }

//	Runnable runnable = new Runnable() {

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
		           		    Status3.setTextSize(72);
	        				ImageView BatteryLeft = (ImageView) findViewById(R.id.image_battery);
	        				ImageView wifistatus = (ImageView) findViewById(R.id.Image_wifi);
	        				ImageView image_mode = (ImageView) findViewById(R.id.image_mode);
	        				ImageView image_expo = (ImageView) findViewById(R.id.image_expo);
	        				ImageView image_balence = (ImageView) findViewById(R.id.image_balence);
	        				ImageView led_red = (ImageView) findViewById(R.id.red);
	        				ImageView led_blue = (ImageView) findViewById(R.id.blue);
	        				Resources res = getResources();
            				Drawable drawwifi = res.getDrawable(R.drawable.iconwifi0);
            				ledblue = res.getDrawable(R.drawable.led_blue_on);
            				led_blue.setImageDrawable(ledblue);
        			    	button_Mode.setEnabled(true);
        			    	button_Mode.setClickable(true);
        			    	button_Mode.setFocusable(true);
	            			image_expo.setVisibility(View.INVISIBLE);
	            			image_balence.setVisibility(View.INVISIBLE);
		        		    try {
	            				BacPacStatus bacpacStatus = helper.getBacpacStatus();
	            				CamFields camFields;
	            				int wifiLevel = bacpacStatus.getRSSI();
	            				if (bacpacStatus.isCameraPowerOn()) {
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
		            				int currentProtuneEnabled=camFields.getProtuneEnabled();
		            				String libProtune;
		            				if (currentProtuneEnabled==6) libProtune=" Pt"; else libProtune=" ";
		            				int currentProtuneSetting=camFields.getProtuneSetting();
		            					
		            				int currentExposure = camFields.getExposure();
		            				if (currentExposure==1) {
		            					exposure = res.getDrawable(R.drawable.exposure);
			            				image_expo.setImageDrawable(exposure);
			            				image_expo.setVisibility(View.VISIBLE); }
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
		            				long hours, minutes;
		            				if (FreeVideos!=65535) {
			            				hours = FreeVideos / 60; //since both are ints, you get an int
			            				minutes = FreeVideos % 60;
		            				} else { hours = 0; minutes = 0; }	
		            				String FreeTime = String.format("%d H %02d", hours, minutes);
		            				currentshutter = camFields.getShutter();
		            				int RecordingSec = camFields.getPlaybackSec();
		            				int RecordingMin = camFields.getPlaybackMin();
		            				String libTimelapse;
		            				if (currentTimelapse == 0) { libTimelapse="0.5"; } else { libTimelapse = String.valueOf(currentTimelapse); }
		            				Drawable drawPower = res.getDrawable(R.drawable.iconepower4);
		            				if (isBetween(getBattery, 72, 100))  drawPower = res.getDrawable(R.drawable.iconepower3); 
		            				if (isBetween(getBattery, 42, 71))   drawPower = res.getDrawable(R.drawable.iconepower2); 
		            				if (isBetween(getBattery, 12, 41))   drawPower = res.getDrawable(R.drawable.iconepower1); 
		            				if (isBetween(getBattery, 0, 11))     drawPower = res.getDrawable(R.drawable.iconepower00);
		            				if (BatteryOn==4) drawPower = res.getDrawable(R.drawable.iconepowerac);
		            				BatteryLeft.setImageDrawable(drawPower);
			            			lastmode=currentMode;
			            			switch (currentMode) { 
			            				case 0 :  	image_mode.setBackgroundResource(R.drawable.mode1);
						            				if (currentProtuneSetting>0) {
						            					balence = res.getDrawable(R.drawable.whitebalance);
							            				image_balence.setImageDrawable(balence);
							            				image_balence.setVisibility(View.VISIBLE); }
			            							Status1.setText(libFov[currentAngle]+libProtune);
			            							if (currentshutter==0) { Status2.setText(libVidres[currentVideoRes]+" / "+libFps[currentfps]); 
			            								ledred = res.getDrawable(R.drawable.led_red_off);
			            								led_red.setImageDrawable(ledred);
			            								Status3.setText(ScurrentNbreVideos);
			            								Status4.setText(FreeTime);
			        	            			    	button_Preview.setEnabled(true);
			        	            			    	button_Preview.setClickable(true);
			        	            			    	button_Preview.setFocusable(true);
			            								}
			            							else { Status2.setText(libVidres[currentVideoRes]+" / "+libFps[currentfps]);
			            								String libRecording;
			            								if (RecordingMin<10) { libRecording="0"+RecordingMin+":"; } else { libRecording=RecordingMin+":"; }
			            								if (RecordingSec<10) { libRecording+="0"+RecordingSec; } else { libRecording+=RecordingSec; }
			            								ledred = res.getDrawable(R.drawable.led_red_on);
			            								led_red.setImageDrawable(ledred);
			            								Status3.setText(libRecording);
			            								Status4.setText(FreeTime);
			        	            			    	button_Preview.setEnabled(false);
			        	            			    	button_Preview.setClickable(false);
			        	            			    	button_Preview.setFocusable(false);
			            								}
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
		            				ledblue = res.getDrawable(R.drawable.led_blue_on);
									led_blue.setImageDrawable(ledblue);
	            			    	button_Record.setEnabled(true);
	            			    	button_Record.setClickable(true);
	            			    	button_Record.setFocusable(true);
	            			    	switch (wifiLevel) {
		            				case 0 : drawwifi = res.getDrawable(R.drawable.iconwifi0); break;
		            				case 1 : drawwifi = res.getDrawable(R.drawable.iconwifi1); break;
		            				case 2 : drawwifi = res.getDrawable(R.drawable.iconwifi2); break;
		            				case 3 : drawwifi = res.getDrawable(R.drawable.iconwifi3); break;
		            				case 4 : drawwifi = res.getDrawable(R.drawable.iconwifi4); break;
		            				}
		            				wifistatus.setImageDrawable(drawwifi);
	            				} else {
	            					wifistatus.setVisibility(View.INVISIBLE);
	            					BatteryLeft.setVisibility(View.INVISIBLE);
			            			Status1.setVisibility(View.INVISIBLE);
			            			Status2.setVisibility(View.INVISIBLE);
			            			Status3.setVisibility(View.INVISIBLE);
			            			Status4.setVisibility(View.INVISIBLE);
			            			image_mode.setVisibility(View.INVISIBLE);
	            			    	button_Preview.setEnabled(false);
	            			    	button_Preview.setClickable(false);
	            			    	button_Preview.setFocusable(false);
	            			    	button_Record.setEnabled(false);
	            			    	button_Record.setClickable(false);
	            			    	button_Record.setFocusable(false);
	            				}
		            			busy_error=0;

		        		    } catch (Exception e) {
								// TODO Auto-generated catch block
		        		    	busy_error++;
		        		    	if (busy_error==timeout)
		        		    	{
		            				System.out.println("Erreur reading settings from Gopro...");
		            				Status2.setText("GoPro not found");
	            					wifistatus.setVisibility(View.INVISIBLE);
	            					BatteryLeft.setVisibility(View.INVISIBLE);
			            			Status1.setVisibility(View.INVISIBLE);
			            			Status3.setVisibility(View.INVISIBLE);
			            			Status4.setVisibility(View.INVISIBLE);
			            			image_mode.setVisibility(View.INVISIBLE);
		            				Status2.setVisibility(View.VISIBLE);
	            			    	button_Preview.setEnabled(false);
	            			    	button_Preview.setClickable(false);
	            			    	button_Preview.setFocusable(false);
	            			    	button_Record.setEnabled(false);
	            			    	button_Record.setClickable(false);
	            			    	button_Record.setFocusable(false);
	            			    	button_Mode.setEnabled(false);
	            			    	button_Mode.setClickable(false);
	            			    	button_Mode.setFocusable(false);
		            				ledblue = res.getDrawable(R.drawable.led_blue_off);
									led_blue.setImageDrawable(ledblue);
		        		    	}
								e.printStackTrace();
		        		    }
	                   }
	           			
	               });
	           }
	       }).start();
	   }
	};

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
			startActivity(new Intent(FullscreenActivity.this, GoproNotFound.class));
		}
    }
}
