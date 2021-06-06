A simple weather app where user can get the weather data of any city in the world by entering either the city Id or city name

**API's used for the data**<br>

returns the city id:  https://www.metaweather.com/api/location/search/?query=london in JSON  

    [
        {
            "title": "London",
            "location_type": "City",
            "woeid": 44418,
            "latt_long": "51.506321,-0.12714"
        }
    ]
    
returns weather data using city id: https://www.metaweather.com/api/location/44418/ in JSON

    {
    "consolidated_weather": [
        {
            "id": 6047213475069952,
            "weather_state_name": "Light Rain",
            "weather_state_abbr": "lr",
            "wind_direction_compass": "WSW",
            "created": "2021-06-06T03:32:01.968631Z",
            "applicable_date": "2021-06-06",
            "min_temp": 12.27,
            "max_temp": 21.325,
            "the_temp": 21.365000000000002,
            "wind_speed": 3.8791033732601607,
            "wind_direction": 251.1485248424343,
            "air_pressure": 1024.5,
            "humidity": 59,
            "visibility": 9.664496838463373,
            "predictability": 75
        } // 5 more
    ],
          "time": "2021-06-06T05:17:49.305988+01:00",
          "sun_rise": "2021-06-06T04:45:35.723661+01:00",
          "sun_set": "2021-06-06T21:13:24.259639+01:00",
          "timezone_name": "LMT",
          "parent": {
          "title": "England",
          "location_type": "Region / State / Province",
          "woeid": 24554868,
          "latt_long": "52.883560,-1.974060"
        },
        "sources": [
          {
            "title": "BBC",
            "slug": "bbc",
            "url": "http://www.bbc.co.uk/weather/",
            "crawl_rate": 360
          },
        {
        "title": "Forecast.io",
        "slug": "forecast-io",
        "url": "http://forecast.io/",
        "crawl_rate": 480
        } // 5 more
    }
