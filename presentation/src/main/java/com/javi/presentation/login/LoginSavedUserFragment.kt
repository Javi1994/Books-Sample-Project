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
import com.javi.common.Util.startActivity
import com.javi.domain.model.User
import com.javi.presentation.R
import com.javi.presentation.databinding.FragmentLoginSavedUserBinding
import com.javi.presentation.home.HomeActivity
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.javi.presentation.model.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
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
                loginViewModel.user
                    .onEach {
                        setData(it)
                    }.collect()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiState
                    .onEach {
                        when (it) {
                            is UiState.Success<*> -> {
                                requireContext().startActivity(HomeActivity::class.java)
                            }
                            is UiState.Loading -> {}
                            is UiState.Error -> {}
                        }
                    }
                    .onCompletion {
                        binding.btnLogin.isLoading(false)
                    }
                    .collect()
            }
        }

        binding.btnLogin.onClickListener {
            binding.btnLogin.isLoading(true)
            loginViewModel.doLogin()
        }

        binding.inputPassword.addTextChangedListener {
            binding.btnLogin.isEnabled(!it.isNullOrEmpty())
        }
    }

    private fun setData(user: User?) {
        with(binding) {
            this.txtWelcomeBack.text =
                resources.getString(R.string.login_welcome_back, user?.username)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}