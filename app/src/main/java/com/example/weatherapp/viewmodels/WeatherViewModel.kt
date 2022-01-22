package com.example.weatherapp.viewmodels

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.models.WeatherService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


@RequiresApi(Build.VERSION_CODES.Q)
class WeatherViewModel(context: Context, activity: Activity) : ViewModel() {

    private val LOG_TAG = "WeatherViewModel"

    private var latitude: Double = 0.0

    private var longitude: Double = 0.0

    private var locationRequestCode = 1

    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val weatherService = WeatherService()

    private var _currentWeatherData = MutableLiveData<WeatherData>()
    val currentWeatherData: LiveData<WeatherData>
        get() {
            return _currentWeatherData
        }

    init {
        getLocation(context, activity)
    }

    fun load() {
        println("load() $$$$$$$$ $latitude, $longitude")
        viewModelScope.launch(Dispatchers.Main) {
            fetchCurrentWeather(latitude, longitude)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getLocation(context: Context, activity: Activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission
                    .ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission
                    .ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission
                    .ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest
                        .permission.ACCESS_FINE_LOCATION,
                    Manifest.permission
                        .ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                locationRequestCode,
            )
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener(activity) { location ->
            if (location != null) {
                lastLocation = location
                latitude = location.latitude
                longitude = location.longitude
                println("$$$$$$$$$$$$$$$ $latitude   $longitude")
                viewModelScope.launch {
                    fetchCurrentWeather(latitude, longitude)
                }
            }
        }
    }

    private suspend fun fetchCurrentWeather(lat: Double, lon: Double) {
        val response: Response<WeatherData> = weatherService.getWeatherAsync(lat, lon)
        try {
            if (response.isSuccessful) {
                println(response.body())
                val data: WeatherData = response.body()!!
                println("This is temp: ${data.getTemp()}")
                _currentWeatherData.value = data
            }
        } catch (e: Exception) {
            Log.i(LOG_TAG, e.toString())
        }
    }
}



