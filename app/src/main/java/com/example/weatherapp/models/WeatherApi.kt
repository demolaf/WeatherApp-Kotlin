package com.example.weatherapp.models

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherApi {
    @GET("data/2.5/weather?")
    suspend fun getWeatherAsync(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") app_id : String = "0ad6c44ae789e6d849cae722ca00bba6"
    ) : Response<WeatherData>
}
