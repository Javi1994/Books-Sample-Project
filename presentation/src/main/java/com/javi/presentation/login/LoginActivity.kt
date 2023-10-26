package com.javi.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.javi.presentation.BaseActivity
import com.javi.presentation.R
import com.javi.presentation.databinding.ActivityLoginBinding
import com.javi.presentation.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel
                    .userFromPreferences
                    .collect {
                        if (it != null) {
                            binding.navHostFragment.findNavController()
                                .navigate(R.id.login_saved_user_fragment)
                        } else {
                            binding.navHostFragment.findNavController()
                                .navigate(R.id.login_new_user_fragment)
                        }
                    }
            }
        }
    }

    override fun toolbarTitle(): String = getString(R.string.login_toolbar_title)
}