package com.javi.booksampleproject.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.javi.booksampleproject.R
import com.javi.booksampleproject.common.Util.startActivityWithDelay
import com.javi.booksampleproject.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            startActivityWithDelay(this@SplashActivity, 1000, LoginActivity::class.java)
        }
    }
}