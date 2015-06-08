package com.rajsoft.cordova.appstatus;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

import java.util.List;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class AppStatus extends CordovaPlugin
{
		
	CallbackContext callback;
	
	boolean runningStatus = false;
	String appname="";

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException 
	{
		callback = callbackContext;

		if(action.equals("checkAppStatus"))
		{
			this.appname = args.getString(0);
			this.checkRunningApp(this.appname);			
			return true;
		}
		else
		{
			callback.error("checkAppStatus." + action + " is not a supported function. Did you mean 'checkAppStatus'?");
			return false;
		}
	}

	private void checkRunningApp(String appname)
	{
		final   ActivityManager activityManager = (ActivityManager) cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
		final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
		
		JSONObject myloc = new JSONObject();

	    for (int i = 0; i < recentTasks.size(); i++) 
	    {	    	
	    	if(recentTasks.get(i).baseActivity.getPackageName().equals(appname)) {
    		     //Toast.makeText(getApplicationContext(), "Camera App is running", Toast.LENGTH_LONG).show();
    			runningStatus=true;
	    	}	    	
	    }

	    try {
			myloc.put("status", runningStatus);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

	    callback.success(myloc);
	}
}