package com.example.havadurumu.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m")
    suspend fun getWeatherData(
        @Query("latitude") lat :Double,
        @Query("longitude") long:Double
    ):WeatherDto
}