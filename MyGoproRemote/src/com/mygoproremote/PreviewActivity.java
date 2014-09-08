package com.mygoproremote;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PreviewActivity extends Activity {
	VideoView videoview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.videoView1);
        // Execute StreamVideo AsyncTask
 
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoview);
        Uri video=Uri.parse("http://10.5.5.9:8080/live/amba.m3u8");
        videoview.setMediaController(mediaController);
        videoview.setVideoURI(video);
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	        public void onCompletion(MediaPlayer mp) {	
	        	videoview.requestFocus();
	        	videoview.start();
	        }
        });
	}
}