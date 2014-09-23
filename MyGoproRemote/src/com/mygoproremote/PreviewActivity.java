package com.mygoproremote;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gopro.main.GoProApi;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class PreviewActivity extends Activity {
	VideoView videoview;
	private static String URL = "http://10.5.5.9/bacpac/sd";
	public String GoproPassword; 
	public int currentshutter=0,actual_mode=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        // Get name and email from global/application context
        GoproPassword  = globalVariable.getpassword();
        actual_mode = globalVariable.getMode();
		
		PlayVideo();
	};
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("Reyevent1:"+keyCode);
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_DPAD_CENTER :
	        {
	        	GoProApi gopro = new GoProApi(GoproPassword);
	        	if (actual_mode==0) {
	        	
	    			if (currentshutter==0) {
		        		try {
			        		System.out.println("Cam Record On");
		        			gopro.getHelper().startRecord();
		        			currentshutter=1;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error in /Cam Record On/");
						}
	    			} else {
		        		try {
			        		System.out.println("Cam Record Off");
		        			gopro.getHelper().stopRecord();
		        			currentshutter=0;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error in /Cam Record Off/");
						}
	    			}
	        	} else {
	        		try {
		        		System.out.println("Photo Record On");
	        			gopro.getHelper().startRecord();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error in /Photo Record On/");
					}
	        		
	        	}
	            return true;
	        }
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	private void PlayVideo()
	 {
	  try
	       {  
	        final VideoView videoView = (VideoView) findViewById(R.id.videoView1);
	        MediaController mediaController = new MediaController(this);
	        mediaController.setAnchorView(videoView);
	        Uri video=Uri.parse("http://10.5.5.9:8080/live/amba.m3u8"); 
	        videoView.setMediaController(mediaController);
	        videoView.setVideoURI(video);
	        videoView.setMediaController(null);
	        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	            public void onCompletion(MediaPlayer mp) {	           		
	            	 mp.stop();
	            	 PlayVideo();
	            }
	        });	
	        videoView.setOnErrorListener(new OnErrorListener() {
	            public boolean onError(MediaPlayer mp, int what, int extra) {	                              
	                return true;
	            }
	        });
	        videoView.setOnPreparedListener(new OnPreparedListener() {
	            public void onPrepared(MediaPlayer mp) {
	            	videoView.start();
	            }
	        });
	        
	            }
	       catch(Exception e)
	       {	              
	                System.out.println("Video Play Error :"+e.toString());
	                finish();
	       }   

	 }
}