package com.example.havadurumu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havadurumu.domain.location.LocationTracker
import com.example.havadurumu.domain.repository.WeatherRepository
import com.example.havadurumu.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel(){

    var state by mutableStateOf(WeatherState())
        private set
    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = WeatherState(
                //state.copy şuan kullanılmıyor
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = WeatherState(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = WeatherState(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = WeatherState(
                    isLoading = false,
                    error = "Konum alınamadı. İzin verildiğinden ve GPS'in etkin olduğundan emin olun."
                )
            }
        }
    }
}