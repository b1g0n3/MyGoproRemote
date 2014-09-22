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
	//public static String GoproPassword = "";
	public int currentshutter=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		GoproPassword = getPassword();
		PlayVideo();
	};
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("Reyevent1:"+keyCode);
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_DPAD_CENTER :
	        {
	        	GoProApi gopro = new GoProApi(GoproPassword);
    			if (currentshutter==0) {
	        		try {
		        		System.out.println("Record On");
	        			gopro.getHelper().startRecord();
	        			currentshutter=1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error in /Record On/");
					}
    			} else {
	        		try {
		        		System.out.println("Record Off");
	        			gopro.getHelper().stopRecord();
	        			currentshutter=0;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error in /Record Off/");
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
			Toast.makeText(this, "GoPro not found", Toast.LENGTH_LONG).show();
			System.out.println("GoPro not found..." );
			TextView Status2 = (TextView) findViewById(R.id.status_ligne2);
			Status2.setText("GoPro not found");
		}
		return (String) localObject;
	}
}