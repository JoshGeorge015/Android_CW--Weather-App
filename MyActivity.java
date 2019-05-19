package com.jg00989.android_cw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {

    Button btnGPSShowLocation;
    Button btnShowAddress;
    TextView tvAddress;

    AppLocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        appLocationService = new AppLocationService(
                MyActivity.this);

        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Location gpsLocation = appLocationService
                        .getLocation(LocationManager.NETWORK_PROVIDER);
                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();
                    int lat= (int) latitude;
                    int lon = (int) longitude;
                    String result = "Latitude: " + lat +
                            " Longitude: " + lon;
                    tvAddress.setText(result);
                }
            }
        });

        btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location location = appLocationService
                        .getLocation(LocationManager.NETWORK_PROVIDER);

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    int lat= (int) latitude;
                    int lon = (int) longitude;
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(lat, lon,
                            getApplicationContext(), new GeocoderHandler());
                }


            }
        });

    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText(locationAddress);
        }
    }
}
