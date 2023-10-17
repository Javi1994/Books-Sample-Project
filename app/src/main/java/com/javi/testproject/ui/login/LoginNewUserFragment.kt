package com.javi.testproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.common.Util.startActivity
import com.javi.testproject.common.Util.startActivityWithDelay
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
            //TODO: improve btn activation
            it.isEnabled = false
            loginViewModel.doLogin()
        }

        //TODO: improve btn activation
        binding.inputUsername.addTextChangedListener {
            binding.btnLogin.isEnabled =
                !it.isNullOrEmpty() && !binding.inputPassword.text.isNullOrEmpty()
        }

        //TODO: improve btn activation
        binding.inputPassword.addTextChangedListener {
            binding.btnLogin.isEnabled =
                !it.isNullOrEmpty() && !binding.inputPassword.text.isNullOrEmpty()
        }

        loginViewModel.uiState
            .onEach {
                println("UiState reached loginNewUserFragment: $it")

                when (it) {
                    is UiState.Success<*> -> {
                        //TODO: improve btn activation
                        binding.btnLogin.isEnabled = true
                        requireContext().startActivity(HomeActivity::class.java)
                    }

                    is UiState.Loading -> {

                    }

                    is UiState.Error -> {
                        //TODO: improve btn activation
                        binding.btnLogin.isEnabled = true
                    }
                }
            }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}