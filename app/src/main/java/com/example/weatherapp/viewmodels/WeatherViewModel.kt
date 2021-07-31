package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.models.WeatherService
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.await
import retrofit2.awaitResponse
import java.lang.Exception


class WeatherViewModel : ViewModel() {

    private val weatherService = WeatherService()

    private var _temp = MutableLiveData<Double>()
    val temp: LiveData<Double>
        get() = _temp

    private var _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private var _countryName = MutableLiveData<String>()
    val countryName: LiveData<String>
        get() = _countryName

    init {
        _temp.value = 0.0
        _description.value = ""
        _countryName.value = ""
    }

    fun load() {
        viewModelScope.launch {
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather() {
        try {
            val currentWeatherResponse = weatherService.getWeatherAsync(6.50543, 3.3874)
            if (currentWeatherResponse.isSuccessful) {
                println(currentWeatherResponse.body())
            }
        } catch (e: Exception) {

        }
    }
}