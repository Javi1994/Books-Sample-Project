package com.javi.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.presentation.R
import com.javi.presentation.Util.startActivity
import com.javi.presentation.databinding.FragmentLoginSavedUserBinding
import com.javi.presentation.home.HomeActivity
import com.javi.presentation.login.viewmodel.LoginEvent
import com.javi.presentation.login.viewmodel.LoginUiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginSavedUserFragment : Fragment(R.layout.fragment_login_saved_user) {

    private var _binding: FragmentLoginSavedUserBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginSavedUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiState
                    .collect {
                        renderUi(it)
                    }
            }
        }

        binding.btnLogin.onClickListener {
            loginViewModel.onEvent(LoginEvent.LoginWithPassword)
        }

        binding.inputPassword.addTextChangedListener {
            loginViewModel.onEvent(LoginEvent.UpdatePassword(it.toString()))
        }
    }

    private fun renderUi(uiState: LoginUiState) {
        if (uiState.loginSuccess) {
            requireContext().startActivity(HomeActivity::class.java)
        }

        binding.btnLogin.isEnabled(uiState.canEnableLoginButtonFromPassword)
        binding.btnLogin.isLoading(uiState.isLoading)

        binding.txtWelcomeBack.text =
            resources.getString(R.string.login_welcome_back, uiState.userFromPreferences?.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}