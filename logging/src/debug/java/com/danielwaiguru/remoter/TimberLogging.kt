package com.danielwaiguru.remoter

import timber.log.Timber

/**
 * override createStackElementTag method to
 * create a more debugging friendly stack trace message
 */
class TimberLogging: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}