package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.models.WeatherService
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.security.auth.callback.Callback


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
        /*try {
            val result = weatherService.getWeatherAsync(6.50543, 3.3874)
            result.enqueue(object: Callback<WeatherData> {

            })
        } catch (e: Exception) {

        }*/
    }
}