package com.javi.testproject.common

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.View
import androidx.core.view.isVisible
import kotlinx.coroutines.delay

object Util {
    suspend fun startActivityWithDelay(context: Context, delayTime: Long, activity: Class<*>) {
        delay(delayTime)
        context.startActivity(Intent(context, activity))
    }

    fun Context.startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    fun View.setVisible(value: Boolean) {
        if (value) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}