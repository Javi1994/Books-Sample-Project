package com.javi.domain.use_case.book

import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.repository.FakeBookData
import com.javi.data.repository.FakeBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllBooksUseCaseTest {

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private lateinit var fakeBooksRepository: FakeBookRepository

    @Before
    fun setUp() {
        fakeBooksRepository = FakeBookRepository()
        getAllBooksUseCase = GetAllBooksUseCase(FakeBookRepository(), Dispatchers.Unconfined)
    }

    @Test
    fun `Success response, returns valid success resource`() {
        fakeBooksRepository.setAllBooksData(FakeBookData.validBooks)

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
            }
        }
    }

    @Test
    fun `Error response, returns valid error resource`() {
        fakeBooksRepository.shouldReturnError()

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
            }
        }
    }

    @Test
    fun `Loading response, returns valid loading resource`() {
        fakeBooksRepository.shouldReturnLoading()

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }
}