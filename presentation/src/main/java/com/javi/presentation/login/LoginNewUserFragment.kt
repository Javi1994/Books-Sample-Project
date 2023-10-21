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
import com.javi.presentation.R
import com.javi.presentation.databinding.FragmentLoginNewUserBinding
import com.javi.presentation.home.HomeActivity
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.javi.presentation.model.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiState
                    .onStart {
                        binding.btnLogin.isLoading(true)
                    }
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
            loginViewModel.doLogin()
        }

        //TODO: improve btn activation
        binding.inputUsername.addTextChangedListener {
            binding.btnLogin.isEnabled(!it.isNullOrEmpty() && !binding.inputPassword.text.isNullOrEmpty())
        }

        //TODO: improve btn activation
        binding.inputPassword.addTextChangedListener {
            binding.btnLogin.isEnabled(!it.isNullOrEmpty() && !binding.inputPassword.text.isNullOrEmpty())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}