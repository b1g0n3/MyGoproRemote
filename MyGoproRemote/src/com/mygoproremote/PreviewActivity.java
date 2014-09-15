package com.mygoproremote;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class PreviewActivity extends Activity {
	VideoView videoview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		PlayVideo();
	};
	
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