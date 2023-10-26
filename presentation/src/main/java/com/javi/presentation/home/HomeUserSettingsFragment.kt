package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.domain.model.User
import com.javi.presentation.ErrorHandler
import com.javi.presentation.ErrorHandlerImpl
import com.javi.presentation.R
import com.javi.presentation.Util.setVisible
import com.javi.presentation.Util.startActivity
import com.javi.presentation.databinding.FragmentHomeUserSettingsBinding
import com.javi.presentation.home.viewmodel.HomeUiEvents
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.javi.presentation.home.viewmodel.UserSettingsUiState
import com.javi.presentation.login.LoginActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeUserSettingsFragment : Fragment(R.layout.fragment_home_user_settings),
    ErrorHandler by ErrorHandlerImpl() {

    private var _binding: FragmentHomeUserSettingsBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModel()

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

        homeViewModel.onEvent(HomeUiEvents.GetUserSettings)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .userSettingsUiState
                    .collect {
                        renderUi(it)
                    }
            }
        }
    }

    private fun renderUi(uiState: UserSettingsUiState) {
        binding.progressLoader.setVisible(uiState.isLoading)
        binding.btnLogout.isLoading(uiState.isLogoutLoading)

        if (uiState.logoutSuccess) {
            requireContext().startActivity(LoginActivity::class.java)
            requireActivity().finish()
        }

        uiState.user?.let {
            setUserData(it)
        }

        uiState.error?.let {
            onError(it, binding.root)
        }
    }

    private fun setUserData(user: User) {
        with(binding) {
            txtUserUsername.text = user.username
            txtUserFullname.text = user.name + " " + user.lastName
            txtUserEmail.text = user.email

            btnLogout.setVisible(true)
            btnLogout.onClickListener {
                homeViewModel.onEvent(HomeUiEvents.Logout)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}