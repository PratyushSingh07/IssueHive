package com.example.findissues

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FindIssuesApp: Application() {
    companion object {
        private var instance: FindIssuesApp? = null
        operator fun get(context: Context): FindIssuesApp {
            return context.applicationContext as FindIssuesApp
        }

        val context: Context?
            get() = instance
    }
}