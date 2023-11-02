package com.javi.domain.use_case.book

import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.repository.FakeBookData
import com.javi.data.repository.FakeBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAllBooksUseCaseTest {

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private lateinit var fakeBookRepository: FakeBookRepository

    @Before
    fun setUp() {
        fakeBookRepository = FakeBookRepository()
        getAllBooksUseCase = GetAllBooksUseCase(FakeBookRepository(), Dispatchers.Unconfined)
    }

    @Test
    fun `Success response, returns valid success resource`() {
        fakeBookRepository.setAllBooksData(FakeBookData.validBooks)

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
            }
        }
    }

    @Test
    fun `Success response with empty data, returns empty data error resource with nullpointer`() {
        fakeBookRepository.setAllBooksData(FakeBookData.emptyBooks)

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(NullPointerException::class.java)
            }
        }
    }

    @Test
    fun `Error response, returns valid error resource`() {
        fakeBookRepository.shouldReturnError()

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(IOException::class.java)
            }
        }
    }

    @Test
    fun `Loading response, returns valid loading resource`() {
        fakeBookRepository.shouldReturnLoading()

        runBlocking {
            getAllBooksUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }
}