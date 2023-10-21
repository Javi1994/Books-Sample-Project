package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.common.Util.setVisible
import com.javi.domain.model.User
import com.javi.presentation.R
import com.javi.presentation.databinding.FragmentHomeUserSettingsBinding
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.javi.presentation.model.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeUserSettingsFragment : Fragment(R.layout.fragment_home_user_settings) {

    private var _binding: FragmentHomeUserSettingsBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeUserSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getUser()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .uiStateUser
                    .collect {
                        render(it)
                    }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .logoutSuccess
                    .onStart {
                        binding.btnLogout.isLoading(true)
                    }
                    .onCompletion {
                        binding.btnLogout.isLoading(false)
                    }
                    .collect {
                        if (it) {
                            (activity as HomeActivity).finish()
                        }
                    }
            }
        }

    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressLoader.visibility = View.VISIBLE
            }

            is UiState.Success<*> -> {
                setUserData(uiState.data as User)
                binding.progressLoader.visibility = View.GONE
            }

            is UiState.Error -> {
                binding.progressLoader.visibility = View.GONE
            }
        }
    }

    private fun setUserData(user: User) {
        with(binding) {
            txtUserUsername.text = user.username
            txtUserFullname.text = user.name + " " + user.lastName
            txtUserEmail.text = user.email

            btnLogout.setVisible(true)
            btnLogout.onClickListener {
                homeViewModel.logout()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}