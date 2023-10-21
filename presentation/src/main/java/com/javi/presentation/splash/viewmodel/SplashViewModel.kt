package com.javi.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.domain.model.Book
import com.javi.domain.use_case.book.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel() {

    val booksState: MutableStateFlow<List<Book>> = MutableStateFlow(listOf())

    init {
        getAllBooksUseCase.invoke()
            .onEach {
                booksState.emit(it)
            }
            .launchIn(viewModelScope + Dispatchers.IO)
    }
}