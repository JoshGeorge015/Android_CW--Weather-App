package com.jg00989.android_cw;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.common.api.Response;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetData extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        String result = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ URLEncoder.encode(params[0], "UTF-8")+"&APPID=ea574594b9d36ab688642d5fbeab847e");
            conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                    result += line;
            }
            in.close();
            return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(conn!=null)
                conn.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    public Context getApplicationContext() {
        return getApplicationContext();
    }


//    val apiService =
//            API.getInstance().retrofit.create(MyApiEndpointInterface::class.java)
//
//    private HashMap params;
//    params = HashMap<String, String>();
//    params["q"] =  "London,uk"
//    params["APPID"] = "b6907d289e10d714a6e88b30761fae22"
//
//    private call = apiService.getUser(params);
//
//    call.enqueue(object : SortedList.Callback<WeatherResponse>
//
//    {
//        override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
//            Log.e("Error:::","Error "+t!!.message)
//        }
//
//        override fun onResponse(call: Call<WeatherResponse>?, response:
//    Response<WeatherResponse>?) {
//            if (response != null && response.isSuccessful && response.body() != null) {
//                Log.e("SUCCESS:::","Response "+ response.body()!!.main.temp)
//
//                // val tempCel = ((response.body()!!.main.temp - 32)*5)/9
//                val tempCel = (response.body()!!.main.temp  - 273.15f)
//
//                temperature.setText("${tempCel.roundToInt()}°C")
//
//            }
//        }
//
//    })
}
