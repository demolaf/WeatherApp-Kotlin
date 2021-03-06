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
) {
    fun getTemp(): String {
        return this.main.getTemp()
    }

    fun getCountryName(): String {
        return "${this.name} ${this.sys.getCountryName()}"
    }

    fun getDescription(): String {
        return this.weather[0].getDescription()
    }

    fun getHumidity(): String {
        return "${this.main.getHumidity()}%"
    }

    fun getVisibility(): String {
        return this.visibility.toString()
    }
}

data class Main(
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
) {
    fun getTemp(): String {
        return this.temp.toString()
    }

    fun getHumidity(): String {
        return this.humidity.toString()
    }
}

data class Clouds(
    @SerializedName("all")
    @Expose
    private val all: Int,
)

data class Coord(
    @SerializedName("lon")
    @Expose
    private val lon: Double,
    @SerializedName("lat")
    @Expose
    private val lat: Double,
)

data class Sys(
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
) {
    fun getCountryName(): String {
        return this.country
    }
}

data class Weather(
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
) {
    fun getDescription(): String {
        return this.description
    }
}

data class Wind(
    @SerializedName("speed")
    @Expose
    private val speed: Double,
    @SerializedName("deg")
    @Expose
    private val deg: Int,
)