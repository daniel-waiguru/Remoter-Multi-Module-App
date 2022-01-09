package com.danielwaiguru.remoter


import timber.log.Timber


object Logger {
    val timberLogger by lazy { TimberLogging() }
    fun initLogger() {
        Timber.plant(timberLogger)
    }
}