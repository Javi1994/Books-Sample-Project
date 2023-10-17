package com.javi.testproject.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.Util.startActivityWithDelay
import com.javi.testproject.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            startActivityWithDelay(this@SplashActivity, 1000, LoginActivity::class.java)
        }
    }
}