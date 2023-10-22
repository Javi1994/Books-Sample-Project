package com.javi.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.javi.presentation.databinding.BottomNavigationBinding

class BottomNavigation(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: BottomNavigationBinding

    init {
        binding = BottomNavigationBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setListeners(homeBottomNavigation: HomeBottomNavigation) {
        with(binding) {

            //Default select since the activity will show the first fragment at start
            btnFavouriteBooks.setSelected()

            btnFavouriteBooks.setOnClickListener {
                if (!btnFavouriteBooks.isSelected) {
                    btnFavouriteBooks.setSelected()
                    homeBottomNavigation.onFavouritesClick()
                }
            }
            btnAllBooks.setOnClickListener {
                if (!btnAllBooks.isSelected) {
                    btnAllBooks.setSelected()
                    homeBottomNavigation.onAllBooksClick()
                }
            }
            btnAllUsers.setOnClickListener {
                if (!btnAllUsers.isSelected) {
                    btnAllUsers.setSelected()
                    homeBottomNavigation.OnUserSettingsClick()
                }
            }
        }
    }

    private fun AppCompatTextView.setSelected() {
        with(binding) {
            btnFavouriteBooks.isSelected = false
            btnAllBooks.isSelected = false
            btnAllUsers.isSelected = false
        }
        this.isSelected = true
    }
}

interface HomeBottomNavigation {
    fun onFavouritesClick()
    fun onAllBooksClick()
    fun OnUserSettingsClick()
}