package me.rajatdhamija.redscreenofdeath

import android.app.Application
import me.rajatdhamija.rsod.RedScreenOfDeath

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RedScreenOfDeath.initRSOD(this)
    }
}