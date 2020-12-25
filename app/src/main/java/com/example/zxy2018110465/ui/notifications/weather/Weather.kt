package com.example.zxy2018110465.ui.notifications.weather

import com.example.zxy2018110465.ui.notifications.weather.CityInfo
import com.example.zxy2018110465.ui.notifications.weather.Data

data class Weather(
    val cityInfo: CityInfo,
    val `data`: Data,
    val date: String,
    val message: String,
    val status: Int,
    val time: String
)