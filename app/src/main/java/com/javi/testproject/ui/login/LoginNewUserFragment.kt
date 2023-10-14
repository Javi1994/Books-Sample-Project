package com.javi.testproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.databinding.FragmentLoginNewUserBinding
import com.javi.testproject.ui.home.HomeActivity
import com.javi.testproject.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginNewUserFragment : Fragment(R.layout.fragment_login_new_user) {

    private var _binding: FragmentLoginNewUserBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginViewModel.doLogin()
        }

        loginViewModel.uiState
            .onEach {
                println("UiState reached loginNewUserFragment: $it")

                when (it) {
                    is UiState.Success<*> -> {
                        startActivityWithDelay(1000, HomeActivity::class.java)
                    }

                    is UiState.Loading -> {

                    }

                    is UiState.Error -> {

                    }
                }
            }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun startActivityWithDelay(delayTime: Long, activity: Class<*>) {
        delay(delayTime)
        startActivity(Intent(requireContext(), activity))
    }
}