package com.javi.testproject.presentation.book_detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.databinding.ActivityBookDetailBinding
import com.javi.testproject.domain.model.BookDetail
import com.javi.testproject.presentation.book_detail.viewmodel.BookDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    private val bookDetailViewModel: BookDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDetailViewModel.getBookDetail()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookDetailViewModel
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
                setBookDetailData(uiState.data as BookDetail)
                binding.progressLoader.visibility = View.GONE
            }

            is UiState.Error -> {
                binding.progressLoader.visibility = View.GONE
            }
        }
    }

    private fun setBookDetailData(bookDetail: BookDetail) {
        with(binding) {
            this.bookDetailTitle.text = bookDetail.title
            this.bookDetailAuthor.text = bookDetail.author
            this.bookDetailDescription.text = bookDetail.description
            this.bookDetailReadTime.text =
                resources.getString(R.string.book_read_time, bookDetail.readTime.toString())
            this.bookDetailPages.text =
                resources.getString(R.string.book_pages, bookDetail.pages.toString())
        }
    }
}