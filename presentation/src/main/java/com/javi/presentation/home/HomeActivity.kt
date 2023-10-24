package com.javi.presentation.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.javi.presentation.R
import com.javi.presentation.components.HomeBottomNavigation
import com.javi.presentation.databinding.ActivityHomeBinding
import com.javi.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeBottomNavigation {

    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(getString(R.string.home_toolbar_title))

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeBottomNav.setListeners(this)
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

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}