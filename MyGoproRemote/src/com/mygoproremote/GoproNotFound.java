package com.mygoproremote;

import android.app.Activity;
import android.os.Bundle;

public class GoproNotFound extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.gopronotfound);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onStop();
		System.exit(0);
	}

}
