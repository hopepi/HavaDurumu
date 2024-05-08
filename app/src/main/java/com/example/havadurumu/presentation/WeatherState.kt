package com.example.havadurumu.presentation

import com.example.havadurumu.domain.weather.WeatherInfo

class WeatherState(
    val weatherInfo: WeatherInfo?=null,
    val isLoading: Boolean=false,
    val error: String?=null
) {
}