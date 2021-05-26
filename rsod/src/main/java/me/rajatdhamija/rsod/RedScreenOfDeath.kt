package me.rajatdhamija.rsod

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast

object RedScreenOfDeath {
    @JvmStatic
    fun initRSOD(application: Application, buildType: String) {
        if (buildType == "debug") {
            val crashListener =
                UncaughtExceptionListener { t, e -> handleUncaughtException(application, t, e) }
            val crashHandler = UncaughtExceptionHandler(crashListener)
            Thread.setDefaultUncaughtExceptionHandler(crashHandler)
            application.getString(R.string.share_error)
            Toast.makeText(application,"Testing private Key ${application.getString(R.string.share_error)}",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(application,"Testing private Key ${application.getString(R.string.share_error)}",Toast.LENGTH_SHORT).show()
            Log.d("Release","Release")
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