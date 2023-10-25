package com.javi.presentation.book_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javi.domain.model.BookDetail
import com.javi.presentation.ErrorHandler
import com.javi.presentation.ErrorHandlerImpl
import com.javi.presentation.R
import com.javi.presentation.Util.setVisible
import com.javi.presentation.book_detail.viewmodel.BookDetailUiEvents
import com.javi.presentation.book_detail.viewmodel.BookDetailUiState
import com.javi.presentation.book_detail.viewmodel.BookDetailViewModel
import com.javi.presentation.databinding.ActivityBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailActivity : AppCompatActivity(),
    ErrorHandler by ErrorHandlerImpl() {

    private lateinit var binding: ActivityBookDetailBinding

    private val bookDetailViewModel: BookDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(getString(R.string.book_detail_toolbar_title))

        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDetailViewModel.onEvent(BookDetailUiEvents.GetBookDetail)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookDetailViewModel
                    .uiState
                    .collect {
                        renderUi(it)
                    }
            }
        }
    }

    private fun renderUi(uiState: BookDetailUiState) {
        binding.progressLoader.setVisible(uiState.isLoading)

        uiState.bookDetail?.let {
            setBookDetailData(it)
        }

        uiState.error?.let {
            onError(it, binding.root)
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

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}