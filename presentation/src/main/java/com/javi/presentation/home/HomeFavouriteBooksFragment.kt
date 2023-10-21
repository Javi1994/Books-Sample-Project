package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.presentation.Util.startActivity
import com.javi.domain.model.Book
import com.javi.presentation.R
import com.javi.presentation.book_detail.BookDetailActivity
import com.javi.presentation.databinding.FragmentHomeFavouriteBooksBinding
import com.javi.presentation.home.adapter.FavouriteBooksAdapter
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.javi.presentation.model.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFavouriteBooksFragment : Fragment(R.layout.fragment_home_favourite_books) {

    private var _binding: FragmentHomeFavouriteBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: FavouriteBooksAdapter

    private val homeViewModel: HomeViewModel by viewModels()

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

        homeViewModel.getFavouriteBooks()
        binding.homeBooksList.apply {
            this.layoutManager = LinearLayoutManager(context)
            bookAdapter = FavouriteBooksAdapter { book ->
                requireContext().startActivity(BookDetailActivity::class.java)
            }
            this.adapter = bookAdapter
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel
                    .uiStateFavouriteBooks
                    .collect {
                        render(it)
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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