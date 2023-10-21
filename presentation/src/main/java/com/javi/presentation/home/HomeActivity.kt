package com.javi.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.javi.presentation.R
import com.javi.presentation.components.HomeBottomNavigation
import com.javi.presentation.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeBottomNavigation {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onAllUsersClick() {
        binding.navHostFragment.findNavController().navigate(R.id.home_all_users)
    }
}