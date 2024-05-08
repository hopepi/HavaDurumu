package com.example.havadurumu.DI

import com.example.havadurumu.data.location.DefaultLocationTracker
import com.example.havadurumu.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun binLocationTracker(defaultLocationTracker: DefaultLocationTracker):LocationTracker
}