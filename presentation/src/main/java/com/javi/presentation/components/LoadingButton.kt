package com.javi.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.javi.presentation.R
import com.javi.presentation.Util.setVisible
import com.javi.presentation.databinding.LoadingButtonBinding

class LoadingButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: LoadingButtonBinding

    private var isLoading = false

    init {
        binding = LoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)

        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LoadingButton)

            val loading = typedArray.getBoolean(R.styleable.LoadingButton_set_loading, false)
            val value = typedArray.getString(R.styleable.LoadingButton_btn_value) ?: "Button"
            val enabled = typedArray.getBoolean(R.styleable.LoadingButton_set_enabled, false)
            setupData(value, loading, enabled)
        }
    }

    private fun setupData(value: String, loading: Boolean, enabled: Boolean) {
        binding.btnText.text = value
        isLoading(loading)
        isEnabled(enabled)
    }

    fun isLoading(loading: Boolean) {
        isLoading = loading
        binding.btnLoader.setVisible(loading)
    }

    fun onClickListener(onClick: () -> Unit) {
        binding.root.setOnClickListener {
            if (!isLoading) {
                onClick()
            }
        }
    }

    fun isEnabled(enabled: Boolean) {
        binding.root.isEnabled = enabled

        if (enabled) {
            binding.btnText.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        } else {
            binding.btnText.setTextColor(
                ContextCompat.getColor(
                    context,
                    android.R.color.darker_gray
                )
            )
        }
    }
}