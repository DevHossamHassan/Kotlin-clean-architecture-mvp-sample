package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample

import android.app.Application
import android.util.Log

/**
 * @author hossam.
 */
class KoltincleanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Hoss","Application started")
    }
}