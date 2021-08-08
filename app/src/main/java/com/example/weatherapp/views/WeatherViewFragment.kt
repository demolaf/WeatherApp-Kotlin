package com.example.weatherapp.views

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherViewBinding
import com.example.weatherapp.viewmodels.WeatherViewModel
import com.example.weatherapp.viewmodels.WeatherViewModelFactory

class WeatherViewFragment : Fragment() {

    lateinit var weatherViewModel: WeatherViewModel
    lateinit var binding: FragmentWeatherViewBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R
                .layout.fragment_weather_view, container, false
        )

        val viewModelFactory = WeatherViewModelFactory(context, activity)

        weatherViewModel =
            ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

        binding.weatherViewModel = weatherViewModel

        observeViewModel()
        binding.swipeToRefresh.setOnRefreshListener {
            Log.i("WeatherViewFragment : ", "onRefresh called from SwipeRefreshLayout")
            weatherViewModel.load()
            binding.swipeToRefresh.isRefreshing = false
        }
        return binding.root
    }

    private fun observeViewModel() {
        weatherViewModel.temp.observe(viewLifecycleOwner, {
            temp ->
            binding.weatherTemperatureText.text = temp
        })

        weatherViewModel.countryName.observe(viewLifecycleOwner, {
            countryName ->
            binding.weatherLocationText.text = countryName
        })

        weatherViewModel.description.observe(viewLifecycleOwner, {
            description ->
            binding.weatherDescriptionText.text = description
        })

        weatherViewModel.humidity.observe(viewLifecycleOwner, {
            humidity ->
            binding.humidityText.text = humidity
        })

        weatherViewModel.visibility.observe(viewLifecycleOwner, {
            visibility ->
            binding.visibilityText.text = visibility
        })
    }
}