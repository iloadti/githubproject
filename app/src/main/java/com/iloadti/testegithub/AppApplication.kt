package com.iloadti.testegithub

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.iloadti.testegithub.di.AppModule

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule().init(this)
        Fresco.initialize(this)
    }


}