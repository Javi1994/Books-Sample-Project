package com.javi.booksampleproject.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.booksampleproject.common.UiState
import com.javi.booksampleproject.databinding.ActivityHomeBinding
import com.javi.booksampleproject.domain.model.Book
import com.javi.booksampleproject.presentation.book_detail.BookDetailActivity
import com.javi.booksampleproject.presentation.home.adapter.FavouriteBooksAdapter
import com.javi.booksampleproject.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.javi.booksampleproject.common.Util.startActivity
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                setBooksData(uiState.data as List<Book>)
                binding.progressLoader.visibility = View.GONE
            }

            is UiState.Error -> {
                binding.progressLoader.visibility = View.GONE
            }
        }
    }

    private fun setBooksData(books: List<Book>) {
        bookAdapter.setData(books)
    }
}