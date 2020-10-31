@file:Suppress("DEPRECATION")

package me.rajatdhamija.rsod

import android.content.Context

internal object Utils {

    fun prepareShareData(appData: AppData, threadName: String, throwable: Throwable): String =
        "${appData.name} Crashed Thread: $threadName thread\n" +
                "Version code: ${appData.versionCode}\n" +
                "Version name: ${appData.versionName}\n" +
                "Stack Trace:\n" +
                throwable.stackTraceToString()

    fun getAppData(context: Context): AppData {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        val appName = packageManager.getApplicationLabel(context.applicationInfo).toString()
        val appVersionName = packageInfo.versionName
        val appVersionCode =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toString()
            } else {
                packageInfo.versionCode.toString()
            }
        return AppData(name = appName, versionCode = appVersionCode, versionName = appVersionName)
    }
}
