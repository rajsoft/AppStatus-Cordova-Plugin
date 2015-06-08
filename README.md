# AppStatus-Cordova-Plugin

# com.rajsoft.cordova.appstatus

This plugin provides information about the app running status, 
There is no guarantee that the plugin returns the running status true or false

## Cordova Version
    This plugin is tested on cordova version: 3.5.0

## Installation
    cordova plugin add https://github.com/rajsoft/AppStatus-Cordova-Plugin.git

## Supported Platforms
    - Android
    
## Add this permission 
uses-permission android:name="android.permission.GET_TASKS"

## Example of Use

navigator.appstatus.getAppStatus("com.sec.android.app.camera", onLocSuccess, onLocError);

function onLocSuccess(location) {
       alert(location.status);
    }
    
function onLocError(error) {
        alert('App Error: ' + error);
}

