package com.example.havadurumu.DI

import com.example.havadurumu.data.repository.WeatherRepositoryImplement
import com.example.havadurumu.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun binWeatherRepository(
        weatherRepositoryImplement: WeatherRepositoryImplement
    ):WeatherRepository
}