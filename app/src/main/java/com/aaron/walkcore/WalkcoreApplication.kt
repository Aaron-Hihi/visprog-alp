package com.aaron.walkcore

import android.app.Application
import com.aaron.walkcore.data.container.WalkcoreContainer

class WalkcoreApplication : Application() {
    lateinit var container: WalkcoreContainer

    override fun onCreate() {
        super.onCreate()
        container = WalkcoreContainer()
    }
}