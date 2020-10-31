package me.rajatdhamija.rsod

import timber.log.Timber
import kotlin.system.exitProcess

internal class UncaughtExceptionHandler(private val uncaughtExceptionListener: UncaughtExceptionListener) :
    Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        try {
            uncaughtExceptionListener.onUncaughtException(t, e)
        } catch (e: Exception) {
            Timber.e("An error occurred in the uncaught exception handler")
        } finally {
            Timber.d("Red Screen of Death completed exception processing.")
            exitProcess(1)
        }
    }
}