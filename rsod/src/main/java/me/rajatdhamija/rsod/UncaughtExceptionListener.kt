package me.rajatdhamija.rsod

internal fun interface UncaughtExceptionListener {
    fun onUncaughtException(thread: Thread, throwable: Throwable)
}
