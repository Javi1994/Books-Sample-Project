package com.javi.testproject.ui.book_detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.javi.testproject.R
import com.javi.testproject.common.UiState
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.databinding.ActivityBookDetailBinding
import com.javi.testproject.databinding.ActivityHomeBinding
import com.javi.testproject.ui.book_detail.viewmodel.BookDetailViewModel
import com.javi.testproject.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    private val bookDetailViewModel: BookDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDetailViewModel.getBookDetail()
        bookDetailViewModel.uiState
            .onEach {
                render(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressLoader.visibility = View.VISIBLE
            }

            is UiState.Success<*> -> {
                setBookDetailData(uiState.data as BookDetailDto)
                binding.progressLoader.visibility = View.GONE
            }

            is UiState.Error -> {
                binding.progressLoader.visibility = View.GONE
            }
        }
    }

    private fun setBookDetailData(bookDetail: BookDetailDto) {
        with(binding) {
            this.bookDetailTitle.text = bookDetail.title
            this.bookDetailAuthor.text = bookDetail.author
            this.bookDetailDescription.text = bookDetail.description
            this.bookDetailReadTime.text =
                resources.getText(R.string.book_read_time, bookDetail.readTime.toString())
            this.bookDetailPages.text =
                resources.getText(R.string.book_pages, bookDetail.pages.toString())
        }
    }
}