package com.shaadi


import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.shaadi.di.component.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber


class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .create(this)
            .build()
    }
}



