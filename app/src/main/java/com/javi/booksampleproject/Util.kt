package com.javi.booksampleproject

import android.content.Context
import android.content.Intent
import android.view.View
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