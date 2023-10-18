package com.javi.booksampleproject.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.javi.booksampleproject.R
import com.javi.booksampleproject.Util.startActivity
import com.javi.booksampleproject.databinding.FragmentLoginNewUserBinding
import com.javi.booksampleproject.presentation.home.HomeActivity
import com.javi.presentation.common.UiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        binding.btnLogin.onClickListener {
            binding.btnLogin.isLoading(true)
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

        loginViewModel.uiState
            .onEach {
                println("UiState reached loginNewUserFragment: $it")

                when (it) {
                    is UiState.Success<*> -> {
                        binding.btnLogin.isLoading(false)
                        requireContext().startActivity(HomeActivity::class.java)
                    }

                    is UiState.Loading -> {
                        //binding.btnLogin.isLoading(true)
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
}