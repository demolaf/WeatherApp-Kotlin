package com.example.weatherapp.models

import kotlinx.coroutines.Deferred

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherService {

    private val baseURL: String = "https://api.openweathermap.org/"
    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun getWeatherAsync(lat: Double, lon: Double): Call<WeatherData> {
        return api.getWeatherAsync(lat, lon)
    }

}