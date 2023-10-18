package com.javi.testproject.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.Util.startActivityWithDelay
import com.javi.testproject.presentation.login.LoginActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            startActivityWithDelay(this@SplashActivity, 1000, LoginActivity::class.java)
        }
    }
}