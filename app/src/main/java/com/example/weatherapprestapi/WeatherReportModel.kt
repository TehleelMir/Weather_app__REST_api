package com.example.weatherapprestapi

data class WeatherReportModel(
        var id: Int = 0,
        var weather_state_name: String = "",
        var weather_state_abbr: String = "",
        var wind_direction_compass: String = "",
        var created: String = "",
        var applicable_date: String = "",
        var min_temp: Double = 0.0,
        var max_temp: Double = 0.0,
        var the_temp: Double = 0.0,
        var wind_speed: Double = 0.0,
        var air_pressure: Double = 0.0,
        var humidity: Int = 0,
        var visibility: Double = 0.0,
        var predictability: Int = 0,
){
        override fun toString(): String = "id = $id  state name = $weather_state_name and the temp = $the_temp"
}
