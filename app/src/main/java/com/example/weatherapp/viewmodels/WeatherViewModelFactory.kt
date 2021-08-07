package com.example.weatherapp.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class WeatherViewModelFactory(private val context: Context?, private val activity:
Activity?) :
    ViewModelProvider
.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            if (context!= null) {
                if (activity != null) {
                    return WeatherViewModel(context, activity) as T
                }
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}