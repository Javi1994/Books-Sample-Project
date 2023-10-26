package com.javi.presentation.home

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.javi.presentation.BaseActivity
import com.javi.presentation.R
import com.javi.presentation.components.HomeBottomNavigation
import com.javi.presentation.databinding.ActivityHomeBinding
import com.javi.presentation.home.viewmodel.HomeUiState
import com.javi.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), HomeBottomNavigation {

    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeBottomNav.setListeners(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .homeUiState
                    .collect {
                        renderUi(it)
                    }
            }
        }
    }

    private fun renderUi(uiState: HomeUiState) {
        if (uiState.favouritesSelected) {
            binding.homeBottomNav.selectFavouriteBooks()
        } else if (uiState.allBooksSelected) {
            binding.homeBottomNav.selectAllBooks()
        } else if (uiState.userSettingsSelected) {
            binding.homeBottomNav.selectUserSettings()
        }
    }

    override fun onFavouritesClick() {
        binding.navHostFragment.findNavController().navigate(R.id.home_favourite_books)
    }

    override fun onAllBooksClick() {
        binding.navHostFragment.findNavController().navigate(R.id.home_all_books)
    }

    override fun OnUserSettingsClick() {
        binding.navHostFragment.findNavController().navigate(R.id.home_all_users)
    }

    override fun toolbarTitle(): String = getString(R.string.home_toolbar_title)
}