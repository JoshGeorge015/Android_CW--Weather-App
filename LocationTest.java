package com.jg00989.android_cw;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class LocationTest extends AppCompatActivity {


    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    private TextView txtLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = (TextView) findViewById(R.id.tv);

        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }
//    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.tv);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

//    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

//    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

//    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}


//    private static final int REQUEST_CHECK_SETTINGS = 1;
//    private FusedLocationProviderClient fusedLocationClient;
//    private LocationRequest locationRequest;
//    private Task<Location> location;
//    private LocationManager locationManager;
//
//// ..
//
//    @SuppressLint("MissingPermission")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_please_work_for_once);
//
//        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        else{
//            ActivityCompat.requestPermissions(this, new String[]{permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION},0);
//        }
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//
//                    @Override
//                    public void onSuccess(Location location) {
//                        location=fusedLocationClient.getLastLocation().getResult();
//                    }
//
////                    @Override
////                    public void onSuccess(location) {
////                        // Got last known location. In some rare situations this can be null.
//
//
//                });
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//        SettingsClient client = LocationServices.getSettingsClient(this);
//        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
//
//
//    }
//
//
//    @SuppressLint("MissingPermission")
//    public Task<Location> getLastLocation() {
//        return fusedLocationClient.getLastLocation();
//    }
//
//    protected void createLocationRequest() {
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(5000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        Task<Location> locationTask;
//        OnSuccessListener<LocationSettingsResponse> osl = new OnSuccessListener<LocationSettingsResponse>() {
//            @Override
//            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//
//            }
//        };
//
//        locationTask = getLastLocation().addOnSuccessListener(osl.onSuccess().;) {
//            public void onSuccess (osl) {
//                    // All location settings are satisfied. The client can initialize
//                    // location requests here.
//                    // ...
//                    location=fusedLocationClient.getLastLocation();
//            }
//        }
//        @Override
//        protected void onResume () {
//            super.onResume();
//            boolean requestingLocationUpdates = false;
//            if (requestingLocationUpdates) {
//                startLocationUpdates();
//            }
//        }
//
//        private void startLocationUpdates(){
//            if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            LocationCallback locationCallback = new LocationCallback();
//            fusedLocationClient.requestLocationUpdates(locationRequest,
//                    locationCallback,
//                    null /* Looper */);
//        }
//
//        @Override
//        protected void onPause(){
//            super.onPause();
//            stopLocationUpdates();
//        }
//
//        private void stopLocationUpdates() {
//            fusedLocationClient.removeLocationUpdates(locationCallback);
//        }
//
//        @Override
//        protected void onResume() {
//            super.onResume();
//            if (requestingLocationUpdates) {
//                startLocationUpdates();
//            }
//        }
//
//
//
//        locationTask.addOnFailureListener(this, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                if (e instanceof ResolvableApiException) {
//                    // Location settings are not satisfied, but this can be fixed
//                    // by showing the user a dialog.
//                    try {
//                        // Show the dialog by calling startResolutionForResult(),
//                        // and check the result in onActivityResult().
//                        ResolvableApiException resolvable = (ResolvableApiException) e;
//                        resolvable.startResolutionForResult(LocationTest.this,
//                                REQUEST_CHECK_SETTINGS);
//                    } catch (IntentSender.SendIntentException sendEx) {
//                        // Ignore the error.
//                    }
//                }
//            }
//        });
//
//        private void stopLocationUpdates() {
//            Task<Void> task;
//            task = fusedLocationClient.removeLocationUpdates(LocationCallback, getMainLooper());
//        }
//
//        private void addLocationUpdates() {
//            fusedLocationClient.requestLocationUpdates(LocationCallback, getMainLooper());
//        }
//
//    }


//}
