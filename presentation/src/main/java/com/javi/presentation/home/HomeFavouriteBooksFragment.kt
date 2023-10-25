package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.presentation.R
import com.javi.presentation.Util.setVisible
import com.javi.presentation.Util.startActivity
import com.javi.presentation.book_detail.BookDetailActivity
import com.javi.presentation.databinding.FragmentHomeFavouriteBooksBinding
import com.javi.presentation.home.adapter.BooksAdapter
import com.javi.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFavouriteBooksFragment : Fragment(R.layout.fragment_home_favourite_books) {

    private var _binding: FragmentHomeFavouriteBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BooksAdapter

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFavouriteBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        homeViewModel.onEvent(HomeUiEvents.GetFavouriteBooks)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .favouriteBooksUiState
                    .collect {
                        renderUi(it)
                    }
            }
        }
    }

    private fun renderUi(uiState: FavouriteBooksUiState) {
        binding.progressLoader.setVisible(uiState.isLoading)

        if (uiState.selectedBook != null) {
            requireContext().startActivity(BookDetailActivity::class.java)
        }

        if (uiState.hasBooks) {
            bookAdapter.setData(uiState.books)
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