package com.javi.presentation

import android.view.View

interface ErrorHandler {
    fun onError(e: Exception, view: View)
}

class ErrorHandlerImpl : ErrorHandler {
    override fun onError(e: Exception, view: View) {
        e.printStackTrace()
    }
}