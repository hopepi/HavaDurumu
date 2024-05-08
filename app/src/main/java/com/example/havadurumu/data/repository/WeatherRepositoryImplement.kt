package com.example.havadurumu.data.repository

import com.example.havadurumu.data.mappers.toWeatherInfo
import com.example.havadurumu.data.remote.WeatherApi
import com.example.havadurumu.domain.repository.WeatherRepository
import com.example.havadurumu.domain.util.Resource
import com.example.havadurumu.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImplement@Inject constructor(
    private val api:WeatherApi
):WeatherRepository{
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat=lat,
                    long=long
                ).toWeatherInfo()
            )
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"Error")
        }
    }

}