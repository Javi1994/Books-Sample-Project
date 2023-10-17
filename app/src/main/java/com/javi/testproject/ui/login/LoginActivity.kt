package com.javi.testproject.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.databinding.ActivityBookDetailBinding
import com.javi.testproject.databinding.ActivityLoginBinding
import com.javi.testproject.ui.book_detail.viewmodel.BookDetailViewModel
import com.javi.testproject.ui.home.adapter.FavouriteBooksAdapter
import com.javi.testproject.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}