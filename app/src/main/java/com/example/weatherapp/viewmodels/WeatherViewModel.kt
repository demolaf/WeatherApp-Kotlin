package com.example.weatherapp.viewmodels

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
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


@RequiresApi(Build.VERSION_CODES.Q)
class WeatherViewModel(context: Context, activity: Activity) : ViewModel() {

    private var latitude: Double = 0.0

    private var longitude: Double = 0.0

    private var locationRequestCode = 1

    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val weatherService = WeatherService()

    private var _temp = MutableLiveData<String>()
    val temp: LiveData<String>
        get() = _temp

    private var _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private var _countryName = MutableLiveData<String>()
    val countryName: LiveData<String>
        get() = _countryName

    private var _humidity = MutableLiveData<String>()
    val humidity: LiveData<String>
        get() = _humidity

    private var _visibility = MutableLiveData<String>()
    val visibility: LiveData<String>
        get() = _visibility

    init {
        getLocation(context, activity)
        _temp.value = ""
        _description.value = ""
        _countryName.value = ""
        _humidity.value = ""
        _visibility.value = ""
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
        val response = weatherService.getWeatherAsync(lat, lon)
        try {
            if (response.isSuccessful) {
                println(response.body())
                val data: WeatherData = response.body()!!
                println("This is temp: ${data.getTemp()}")
                _temp.value = data.getTemp()
                _countryName.value = data.getCountryName()
                _description.value = data.getDescription()
                _visibility.value = data.getVisibility()
                _humidity.value = data.getHumidity()
            }
        } catch (e: Exception) {

        }
    }
}



