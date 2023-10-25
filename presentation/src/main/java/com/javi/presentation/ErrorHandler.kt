package com.javi.presentation

import android.view.View
import com.google.android.material.snackbar.Snackbar

interface ErrorHandler {
    fun onError(e: Exception, view: View)
}

class ErrorHandlerImpl : ErrorHandler {
    override fun onError(e: Exception, view: View) {
        e.printStackTrace()
        Snackbar.make(view, e.localizedMessage, Snackbar.LENGTH_LONG).show()
    }
}