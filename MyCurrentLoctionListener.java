//package com.jg00989.android_cw;
//
//import android.location.Location;
//import android.location.LocationListener;
//import android.os.Bundle;
//import android.util.Log;
//
//public class MyCurrentLoctionListener implements LocationListener {
//
//    @Override
//    public void onLocationChanged(Location location) {
//        location.getLatitude();
//        location.getLongitude();
//
//        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
//
//        //I make a log to see the results
//        Log.e("MY CURRENT LOCATION", myLocation);
//
//    }
//
//    public String abcd(Location location){
//        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
//        return myLocation;
//    }
//
//
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    public void onProviderEnabled(String s) {
//
//    }
//
//    public void onProviderDisabled(String s) {
//
//    }
//}
