package com.javi.testproject.ui.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.common.Util
import com.javi.testproject.common.Util.startActivity
import com.javi.testproject.common.Util.startActivityWithDelay
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.databinding.ActivityHomeBinding
import com.javi.testproject.databinding.FragmentLoginNewUserBinding
import com.javi.testproject.ui.book_detail.BookDetailActivity
import com.javi.testproject.ui.book_detail.viewmodel.BookDetailViewModel
import com.javi.testproject.ui.home.adapter.FavouriteBooksAdapter
import com.javi.testproject.ui.home.viewmodel.HomeViewModel
import com.javi.testproject.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var bookAdapter: FavouriteBooksAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel.getFavouriteBooks()
        binding.homeBooksList.apply {
            this.layoutManager = LinearLayoutManager(context)
            bookAdapter = FavouriteBooksAdapter { book ->
                this@HomeActivity.startActivity(BookDetailActivity::class.java)
            }
            this.adapter = bookAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .uiState
                    .collect {
                        render(it)
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
                setBooksData(uiState.data as List<BookDto>)
                binding.progressLoader.visibility = View.GONE
            }

            is UiState.Error -> {
                binding.progressLoader.visibility = View.GONE
            }
        }
    }

    private fun setBooksData(books: List<BookDto>) {
        bookAdapter.setData(books)
    }
}