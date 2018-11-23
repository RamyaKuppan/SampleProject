package com.allinonesports

import android.app.Application

class AllInOneSportsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LoginInfo.init(applicationContext)
    }
}