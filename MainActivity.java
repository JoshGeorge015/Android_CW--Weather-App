package com.jg00989.android_cw;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static com.jg00989.android_cw.R.drawable.ic_cloud_black_24dp;

public class MainActivity extends Activity {

    ConstraintLayout Cl;
    JSONObject data = new JSONObject();
    TextView tv,conditiontv;
    ImageView condition;
    private static final String api_key = BuildConfig.API_KEY;
    private ArrayList<String> DispData = new ArrayList<String>();
    Button getLoc;
    AppLocationService appLocationService;
    String result;
    ImageView iv;
    private String coord,weather, desc,current_temp,pressure, humidity, temp_min,temp_max, visibility,wind, clouds, sunrise, sunset,cityName;
    private StringBuffer s;
    //    R.drawable.clear, R.drawable.cloudy, R.drawable.fog, R.drawable.lightning,R.drawable.rain,R.drawable.snow,R.drawable.tornado,R.drawable.wind};
    private int curImage;
    private String main;
    private String disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        Cl=(ConstraintLayout)findViewById(R.id.cl);
        getJSON(getString(R.string.city));
        getLoc=findViewById(R.id.getLoc);
        iv=findViewById(R.id.condition);
        getLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON(getString(R.string.city));
//                iv.setImageResource(R.drawable.fp);
            }
        });

        appLocationService = new AppLocationService(
                MainActivity.this);
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            int lat= (int) latitude;
            int lon = (int) longitude;

            result = "lat=" + String.valueOf(latitude) +
                    "&lon=" + String.valueOf(longitude);
            Log.e("Location values=",result);
//            tvAddress.setText(result);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void getJSON(final String city) {

        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?"+result+"&APPID=2fb8f7dc2b7ffc0d4aa76138013841d7");
                    Log.e("URL=", url.toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";

                    while((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    data = new JSONObject(json.toString());

                    if(data.getInt("cod") != 200) {
                        System.out.println("Cancelled");
                        return null;
                    }
                    coord="Coordinates: "+String.valueOf(data.getDouble("coord"));
//                    weather="Weather"+data.getString("weather");
                    main="Weather: "+data.getString("main");
                    desc="Details: " +data.getString("description");
                    current_temp= "Temp: "+String.valueOf(data.getDouble("temp"));
                    pressure="Pressure: "+String.valueOf(data.getDouble("pressure"));
                    humidity="Humidity: "+String.valueOf(data.getDouble("humidity"));
                    temp_min="Min"+String.valueOf(data.getDouble("temp_min"));
                    temp_max="Max"+String.valueOf(data.getDouble("temp_max"));
                    visibility="Visibility"+data.getString("visibility");
                    wind="Wind" +data.getString("wind");
                    clouds="Cloud" +data.getString("clouds");
                    sunrise= String.valueOf(( Instant.ofEpochSecond( data.getInt("sunrise") ) ));
                    sunset= String.valueOf(( Instant.ofEpochSecond( data.getInt("sunset") ) ));
                    cityName=String.valueOf(data.getString("name"));

//                    StringBuffer s = new StringBuffer();
                    s = new StringBuffer();
                    s.append(coord+"\n");
//                    weather="Weather"+data.getString("weather");
                    s.append(main+"\n");
                    s.append(desc+"\n");
                    s.append(current_temp+"\n");
                    s.append(pressure+"\n");
                    s.append(humidity+"\n");
                    s.append(temp_min+"\n");
                    s.append(temp_max+"\n");
                    s.append(visibility+"\n");
                    s.append(wind+"\n");
                    s.append(clouds+"\n");
                    s.append(sunrise+"\n");
                    s.append(sunset+"\n");
                    s.append(cityName+"\n");

                    disp = new String();
                    disp=s.toString();


                } catch (Exception e) {

                    System.out.println("Exception "+ e.getMessage());
                    return null;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void Void) {


                int weather_sunny = 0;
                int weather_clear = 0;
                int weather_clear_night = 0;
                int weather_cloudy = 0;
                int weather_foggy = 0;
                int weather_rainy = 0;
                int weather_snowy = 0;
                int weather_thunder = 0;
                int weather_drizzle = 0;
                int weather_windy = 0;
                if (data != null) {
//                    String dip = s.toString();

//                    String da;
//
//                    da=data.keys().toString();
//                    s.append(data.toString());
//                    String[] x=data.toString().split(",");


//                    for(int y=0; y<x.length;y++){
//                        x[y]=x[y]+"\n";
//                    }
//                    R.drawable.clear, R.drawable.cloudy, R.drawable.fog, R.drawable.lightning,R.drawable.rain,R.drawable.snow,R.drawable.tornado,R.drawable.wind};
                    tv.setText("");
//                    for(String p:s){
//                        if(p.contains("{")){
//                            p.replace("{","");
//
                    tv.append(disp);


                    if (main.contains("Clouds")) {
                        weather_cloudy++;
                        iv.setImageResource(R.drawable.ic_cloud_black_24dp);
                        Cl.setBackgroundColor(Color.GRAY);
//                            conditiontv.setText(images[1]);
//                            Layout mlayout= findViewById(R.layout.activity_main);
//// set the color
////                            mlayout.
//                            mlayout.setBackgroundColor(Color.GRAY);


                    }

                    if (main.contains("Clear")) {
                        weather_clear++;
                        iv.setImageResource(R.drawable.sunny);
//                            conditiontv.setText(images[0]);
//                            mlayout= findViewById(R.id.laidout);
// set the color
                        Cl.setBackgroundColor(Color.BLUE);
                    }
                    if (main.contains("Sunny")) {
                        weather_sunny++;
                        iv.setImageResource(R.drawable.sunny);
//                            conditiontv.setText(images[6]);
//                            mlayout= findViewById(R.id.laidout);
// set the color
                        Cl.setBackgroundColor(Color.BLUE);

                    }
                    if (main.contains("Clear Night")) {
                        weather_clear_night++;
                        iv.setImageResource(R.drawable.sunny);
//                            conditiontv.setText(images[0]);
                        Cl.setBackgroundColor(Color.BLACK);
                    }
                    if (main.contains("Fog")) {
                        weather_foggy++;

                        iv.setImageResource(R.drawable.fog);
//                            conditiontv.setText(images[2]);
                        Cl.setBackgroundColor(Color.GRAY);
                    }
                    if (main.contains("Rainy")) {
                        weather_rainy++;
                        iv.setImageResource(R.drawable.rain);
//                            conditiontv.setText(images[4]);
                        Cl.setBackgroundColor(Color.GRAY);
                    }
                    if (main.contains("Snowy")) {
                        weather_snowy++;
                        iv.setImageResource(R.drawable.snow);
//                            conditiontv.setText(images[5]);
                        Cl.setBackgroundColor(Color.WHITE);
                    }
                    if (main.contains("Thunder")) {
                        weather_thunder++;
                        iv.setImageResource(R.drawable.lightning);
//                            conditiontv.setText(images[3]);
                    }
                    if (main.contains("Drizzle")) {
                        weather_drizzle++;
                        iv.setImageResource(R.drawable.rain);
//                            conditiontv.setText(images[4]);

                    }
                    if (main.contains("Windy")) {
                        weather_windy++;
                        iv.setImageResource(R.drawable.wind);
//                            conditiontv.setText(images[8]);

                    }


                    WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
//                    setWeatherIcon();
                    Log.d("my weather received", disp);
                }
            }
            }.execute();
        }




    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getString(R.string.weather_rainy);
                    break;
            }
        }
        conditiontv.setText(icon);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);
    }
}