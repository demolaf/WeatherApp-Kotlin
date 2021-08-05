package com.example.weatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("coord")
    @Expose
    private val coord: Coord,
    @SerializedName("weather")
    @Expose
    private val weather: List<Weather>,
    @SerializedName("base")
    @Expose
    private val base: String,
    @SerializedName("main")
    @Expose
    private val main: Main,
    @SerializedName("visibility")
    @Expose
    private val visibility: Int,
    @SerializedName("wind")
    @Expose
    private val wind: Wind,
    @SerializedName("clouds")
    @Expose
    private val clouds: Clouds,
    @SerializedName("dt")
    @Expose
    private val dt: Int,
    @SerializedName("sys")
    @Expose
    private val sys: Sys,
    @SerializedName("timezone")
    @Expose
    private val timezone: Int,
    @SerializedName("id")
    @Expose
    private val id: Int,
    @SerializedName("name")
    @Expose
    private val name: String,
    @SerializedName("cod")
    @Expose
    private val cod: Int,
)
class Main(
    @SerializedName("temp")
    @Expose
    private val temp: Double,
    @SerializedName("feels_like")
    @Expose
    private val feelsLike: Double,
    @SerializedName("temp_min")
    @Expose
    private val tempMin: Double,
    @SerializedName("temp_max")
    @Expose
    private val tempMax: Double,
    @SerializedName("pressure")
    @Expose
    private val pressure: Int,
    @SerializedName("humidity")
    @Expose
    private val humidity: Int,
)
class Clouds(
    @SerializedName("all")
    @Expose
    private val all: Int,
)
class Coord(
    @SerializedName("lon")
    @Expose
    private val lon: Double,
    @SerializedName("lat")
    @Expose
    private val lat: Double,
)
class Sys(
    @SerializedName("type")
    @Expose
    private val type: Int,
    @SerializedName("id")
    @Expose
    private val id: Int,
    @SerializedName("country")
    @Expose
    private val country: String,
    @SerializedName("sunrise")
    @Expose
    private val sunrise: Int,
    @SerializedName("sunset")
    @Expose
    private val sunset: Int,
)
class Weather(
    @SerializedName("id")
    @Expose
    private val id: Int,
    @SerializedName("main")
    @Expose
    private val main: String,
    @SerializedName("description")
    @Expose
    private val description: String,
    @SerializedName("icon")
    @Expose
    private val icon: String,
)
class Wind(
    @SerializedName("speed")
    @Expose
    private val speed: Double,
    @SerializedName("deg")
    @Expose
    private val deg: Int,
)