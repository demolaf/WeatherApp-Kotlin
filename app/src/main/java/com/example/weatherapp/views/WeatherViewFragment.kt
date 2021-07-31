package com.example.weatherapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherViewBinding
import com.example.weatherapp.viewmodels.WeatherViewModel
import com.example.weatherapp.viewmodels.WeatherViewModelFactory

class WeatherViewFragment : Fragment() {

    lateinit var weatherViewModel: WeatherViewModel
    lateinit var binding: FragmentWeatherViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R
                .layout.fragment_weather_view, container, false
        )

        val viewModelFactory = WeatherViewModelFactory()

        weatherViewModel =
            ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

        binding.weatherViewModel = weatherViewModel

        weatherViewModel.load()

        return binding.root
    }

    private fun observeViewModel() {
    }
}