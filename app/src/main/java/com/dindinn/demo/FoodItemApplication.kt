package com.dindinn.demo

import android.app.Application
import com.airbnb.mvrx.Mavericks

class FoodItemApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(debugMode = true)
    }
}