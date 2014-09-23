package com.mygoproremote;

import android.app.Application;

public class GlobalClass extends Application {
    private String password;
    private int actual_mode;
     
 
    public String getpassword() {
         
        return password;
    }
     
    public void setpassword(String aPassword) {
        
       password = aPassword;
         
    }
    
    public int getMode() {
         
        return actual_mode;
    }
     
    public void setMode(int aMode) {
        
      actual_mode = aMode;
    }
}
