package com.javi.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.presentation.ErrorHandler
import com.javi.presentation.ErrorHandlerImpl
import com.javi.presentation.R
import com.javi.presentation.Util.startActivity
import com.javi.presentation.databinding.FragmentLoginSavedUserBinding
import com.javi.presentation.home.HomeActivity
import com.javi.presentation.login.viewmodel.LoginUiEvent
import com.javi.presentation.login.viewmodel.LoginUiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginSavedUserFragment : Fragment(R.layout.fragment_login_saved_user),
    ErrorHandler by ErrorHandlerImpl() {

    private var _binding: FragmentLoginSavedUserBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels()

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
                loginViewModel
                    .uiState
                    .collect {
                        renderUi(it)
                    }
            }
        }

        binding.btnLogin.onClickListener {
            loginViewModel.onEvent(LoginUiEvent.LoginWithPassword)
        }

        binding.inputPassword.addTextChangedListener {
            loginViewModel.onEvent(LoginUiEvent.UpdatePassword(it.toString()))
        }
    }

    private fun renderUi(uiState: LoginUiState) {
        if (uiState.loginSuccess) {
            requireContext().startActivity(HomeActivity::class.java)
            requireActivity().finish()
        }

        // If viewmodel password is not empty and inputPassword is empty it means that
        // the activity is coming through process death so we restore the previous value
        if (uiState.password.isNotEmpty() && binding.inputPassword.text.toString().isNullOrEmpty()) {
            binding.inputPassword.setText(uiState.password)
        }

        uiState.passwordError?.let {
            binding.inputPassword.error = getString(it)
        }

        binding.btnLogin.isEnabled(uiState.canEnableLoginButtonFromPassword)
        binding.btnLogin.isLoading(uiState.isLoadingLogin)

        binding.txtWelcomeBack.text =
            resources.getString(R.string.login_welcome_back, uiState.userFromPreferences?.username)

        uiState.requestError?.let {
            onError(it, binding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}