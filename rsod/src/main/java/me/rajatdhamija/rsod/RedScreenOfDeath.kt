package me.rajatdhamija.rsod

import android.app.Application
import android.content.Context

object RedScreenOfDeath {
    @JvmStatic
    fun initRSOD(application: Application, buildType: String) {
        if (buildType == "debug") {
            val crashListener =
                UncaughtExceptionListener { t, e -> handleUncaughtException(application, t, e) }
            val crashHandler = UncaughtExceptionHandler(crashListener)
            Thread.setDefaultUncaughtExceptionHandler(crashHandler)
        }
    }

    private fun handleUncaughtException(context: Context, thread: Thread, throwable: Throwable) {
        val appData = Utils.getAppData(context)
        val intent = RedScreenOfDeathActivity.newIntent(
            context = context,
            threadName = thread.name,
            throwable = throwable,
            appData = appData,
        )
        context.startActivity(intent)
    }
}