package com.rajsoft.cordova.appstatus;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Bundle;

public class AppStatus extends CordovaPlugin
{
		
	CallbackContext callback;
	
	boolean runningStatus = false;
	String appname="";

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException 
	{
		callback = callbackContext;
		if (locationManager == null) {
			locationManager = (LocationManager) cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
		}

		if(action.equals("checkAppStatus"))
		{
			this.appname = args.getBoolean(0);
			
			//this.processLocation();
			this.checkRunningApp(appname)
			
			return true;
		}
		else
		{
			callback.error("checkAppStatus." + action + " is not a supported function. Did you mean 'getLocation'?");
			return false;
		}
	}

	private void checkRunningApp(String appname)
	{
		final   ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
		
	    for (int i = 0; i < recentTasks.size(); i++) 
	    {
	    	
	    	if(recentTasks.get(i).baseActivity.getPackageName().equals(appname)) {
    		     //Toast.makeText(getApplicationContext(), "Camera App is running", Toast.LENGTH_LONG).show();
    			runningStatus=true;
	    	}	    	
	    }

	    callback.success(runningStatus);
	}


	

}