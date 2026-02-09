package com.mayada.playscope

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlayScopeApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}