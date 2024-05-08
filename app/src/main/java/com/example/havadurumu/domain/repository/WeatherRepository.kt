package com.example.havadurumu.domain.repository

import com.example.havadurumu.domain.util.Resource
import com.example.havadurumu.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat:Double,long: Double):Resource<WeatherInfo>
}