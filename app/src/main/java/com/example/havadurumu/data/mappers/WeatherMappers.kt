package com.example.havadurumu.data.mappers

import com.example.havadurumu.data.remote.WeatherDataDto
import com.example.havadurumu.data.remote.WeatherDto
import com.example.havadurumu.domain.weather.WeatherData
import com.example.havadurumu.domain.weather.WeatherInfo
import com.example.havadurumu.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class IndexedWeatherData(
    val index:Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap():Map<Int,List<WeatherData>>{
    return time.mapIndexed{index, time ->
        val temperature = temperatures[index]
        val weatherCode=weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(time= LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode))
        )
        /*
        WeatherData(
            time= LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            windSpeed = windSpeed,
            pressure = pressure,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        )
         */
    }.groupBy{
        it.index/24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo():WeatherInfo{
    val weatherDataMap=weatherData.toWeatherDataMap()
    val now=LocalDateTime.now()
    val currentWeatherData=weatherDataMap[0]?.find {
        val hour = when {
            now.minute < 30 -> now.hour
            now.hour == 23 -> 12.00
            else -> now.hour + 1
        }
        it.time.hour==hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData=currentWeatherData
    )
}