package com.example.weatherapprestapi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    CallBacks callBacks;

    WeatherDataService(CallBacks callBacks) {
        this.callBacks = callBacks;
    }

    public void getCityId(String cityName, Context context) {
        // Instantiate the RequestQueue.
        //no need for this line now because we have created a SingleTon class for that
        //RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.metaweather.com/api/location/search/?query=" + cityName;

        // Request a json response from the provided URL.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject root = response.getJSONObject(0);
                    String cityId = root.getString("woeid");
                    Toast.makeText(context, cityId, Toast.LENGTH_LONG).show();
                    callBacks.gotCityId(cityId);
                } catch (Exception e) {
                    Toast.makeText(context, "error while parsing the jsonObject/array", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error in responce", Toast.LENGTH_SHORT).show();
                callBacks.gotError(error.toString());
            }
        });

        // Add the request to the RequestQueue.
        //queue.add(request);
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getCityForecastById(String cityId, Context context) {
        String url = "https://www.metaweather.com/api/location/" + cityId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<WeatherReportModel> listTemp = new ArrayList<>();
                try {
                    JSONArray root = response.getJSONArray("consolidated_weather");
                    for (int i = 0; i < root.length(); i++) {
                        JSONObject dataTemp = (JSONObject) root.get(i);
                        WeatherReportModel modelTemp = new WeatherReportModel();
                        modelTemp.setId(dataTemp.getInt("id"));
                        modelTemp.setWeather_state_name(dataTemp.getString("weather_state_name"));
                        modelTemp.setThe_temp(dataTemp.getDouble("the_temp"));
                        listTemp.add(modelTemp);
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                }
                callBacks.gotWeatherDataById(listTemp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBacks.gotError("Error");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getCityForecastByName(String cityName, Context context) {
        /* in order to perform this function first we are going to call getCityId method and also create an anonumous class which will send us the reponce
        back and then we will do same with the getForecastById method
         */
    }

    public interface CallBacks {
        void gotCityId(String cityId);

        void gotCityName(List<WeatherReportModel> list);

        void gotWeatherDataById(List<WeatherReportModel> list);

        void gotError(String errorMessage);
    }
}
