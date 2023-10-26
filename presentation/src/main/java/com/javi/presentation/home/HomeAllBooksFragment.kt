package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.presentation.ErrorHandler
import com.javi.presentation.ErrorHandlerImpl
import com.javi.presentation.R
import com.javi.presentation.Util.setVisible
import com.javi.presentation.Util.startActivity
import com.javi.presentation.book_detail.BookDetailActivity
import com.javi.presentation.databinding.FragmentHomeAllBooksBinding
import com.javi.presentation.home.adapter.BooksAdapter
import com.javi.presentation.home.viewmodel.AllBooksUiState
import com.javi.presentation.home.viewmodel.HomeUiEvents
import com.javi.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeAllBooksFragment : Fragment(R.layout.fragment_home_all_books),
    ErrorHandler by ErrorHandlerImpl() {

    private var _binding: FragmentHomeAllBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BooksAdapter

    private val homeViewModel: HomeViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAllBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        homeViewModel.onEvent(HomeUiEvents.GetAllBooks)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .allBooksUiState
                    .collect {
                        renderUi(it)
                    }
            }
        }
    }

    private fun renderUi(uiState: AllBooksUiState) {
        binding.progressLoader.setVisible(uiState.isLoading)

        if (uiState.hasBooks) {
            bookAdapter.setData(uiState.books)
        }

        if (uiState.selectedBook != null) {
            requireContext().startActivity(BookDetailActivity::class.java)
            homeViewModel.bookWasSelected()
        }

        uiState.error?.let {
            onError(it, binding.root)
        }
    }

    private fun setupAdapter() {
        binding.homeBooksList.apply {
            this.layoutManager = LinearLayoutManager(context)
            bookAdapter = BooksAdapter { book ->
                homeViewModel.onEvent(HomeUiEvents.OnBookClicked(book))
            }
            this.adapter = bookAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}