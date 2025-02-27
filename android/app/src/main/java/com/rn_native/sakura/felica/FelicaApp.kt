package com.rn_native.sakura.felica

import android.app.Application
import com.rn_native.BuildConfig
import timber.log.Timber

class FelicaApp : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
