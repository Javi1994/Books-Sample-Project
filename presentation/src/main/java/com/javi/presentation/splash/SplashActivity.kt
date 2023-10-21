package com.javi.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.presentation.R
import com.javi.presentation.Util.startActivityWithDelay
import com.javi.presentation.databinding.ActivitySplashBinding
import com.javi.presentation.login.LoginActivity
import com.javi.presentation.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.booksState
                    .onEach {
                        if (it.isNotEmpty()) {
                            startActivityWithDelay(
                                this@SplashActivity,
                                1000,
                                LoginActivity::class.java
                            )
                            finish()
                        }
                    }.collect()
            }
        }
    }
}