package com.javi.testproject.common

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.delay

object Util {
    suspend fun startActivityWithDelay(context: Context, delayTime: Long, activity: Class<*>) {
        delay(delayTime)
        context.startActivity(Intent(context, activity))
    }

    fun Context.startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }
}