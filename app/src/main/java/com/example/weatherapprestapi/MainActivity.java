package com.example.weatherapprestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , WeatherDataService.CallBacks{

    Button button1 , button2 , button3;
    EditText editText;
    ListView listView;
    WeatherDataService weatherDataService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        button1 = findViewById(R.id.getIdByCityName);
        button2 = findViewById(R.id.getWeatherByCityId);
        button3 = findViewById(R.id.getWeatherByCityName);
        editText = findViewById(R.id.editTxt);
        listView = findViewById(R.id.listView);
        weatherDataService = new WeatherDataService(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String editTextValue = editText.getText().toString();
        if(editTextValue.equals("")) { Toast.makeText(this, "Empty Text", Toast.LENGTH_SHORT).show(); return; }

        switch (view.getId()){
            case R.id.getIdByCityName:
                weatherDataService.getCityId(editTextValue , this);
                break;
            case R.id.getWeatherByCityId:
                weatherDataService.getCityForecastById(editTextValue , this);
                break;
            case R.id.getWeatherByCityName:
                break;
        }
    }

    @Override
    public void gotCityId(String cityId) {

    }

    @Override
    public void gotCityName(List<WeatherReportModel> list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , list);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void gotWeatherDataById(List<WeatherReportModel> list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , list);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void gotError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}