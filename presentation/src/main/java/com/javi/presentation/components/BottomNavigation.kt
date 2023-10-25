package com.javi.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.javi.presentation.databinding.BottomNavigationBinding

class BottomNavigation(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: BottomNavigationBinding

    init {
        binding = BottomNavigationBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setListeners(homeBottomNavigation: HomeBottomNavigation) {
        with(binding) {

            btnFavouriteBooks.setOnClickListener {
                if (!btnFavouriteBooks.isSelected) {
                    homeBottomNavigation.onFavouritesClick()
                }
            }
            btnAllBooks.setOnClickListener {
                if (!btnAllBooks.isSelected) {
                    homeBottomNavigation.onAllBooksClick()
                }
            }
            btnUserSettings.setOnClickListener {
                if (!btnUserSettings.isSelected) {
                    homeBottomNavigation.OnUserSettingsClick()
                }
            }
        }
    }

    private fun AppCompatTextView.setSelected() {
        with(binding) {
            btnFavouriteBooks.isSelected = false
            deselect(btnFavouriteBooks)

            btnAllBooks.isSelected = false
            deselect(btnAllBooks)

            btnUserSettings.isSelected = false
            deselect(btnUserSettings)
        }

        this.isSelected = true
        select(this)
    }

    fun selectFavouriteBooks() {
        binding.btnFavouriteBooks.setSelected()
    }

    fun selectAllBooks() {
        binding.btnAllBooks.setSelected()
    }

    fun selectUserSettings() {
        binding.btnUserSettings.setSelected()
    }

    private fun select(textView: AppCompatTextView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        textView.setTextColor(ContextCompat.getColor(textView.context, android.R.color.holo_green_dark))
    }

    private fun deselect(textView: AppCompatTextView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        textView.setTextColor(ContextCompat.getColor(textView.context, android.R.color.black))
    }
}

interface HomeBottomNavigation {
    fun onFavouritesClick()
    fun onAllBooksClick()
    fun OnUserSettingsClick()
}