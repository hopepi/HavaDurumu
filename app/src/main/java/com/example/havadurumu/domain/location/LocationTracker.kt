package com.example.havadurumu.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation():Location?
}