package com.javi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle()
    }

    private fun setToolbarTitle() {
        supportActionBar?.title = toolbarTitle()
    }

    abstract fun toolbarTitle(): String
}