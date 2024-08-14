package com.canerture.valorantcmp

import android.app.Application
import com.canerture.valorantcmp.di.initKoin

class ValorantCMPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
