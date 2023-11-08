package com.javi.domain.use_case.login

import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.repository.FakeLoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LogoutUseCaseTest {

    private lateinit var logoutUseCase: LogoutUseCase
    private lateinit var fakeLoginRepository: FakeLoginRepository

    @Before
    fun setUp() {
        fakeLoginRepository = FakeLoginRepository()
        logoutUseCase = LogoutUseCase(fakeLoginRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `Success response, returns valid success resource`() {
        runBlocking {
            logoutUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(Unit::class.java)
            }
        }
    }

    @Test
    fun `Error response, returns valid error resource`() {
        fakeLoginRepository.shouldReturnError()

        runBlocking {
            logoutUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(IOException::class.java)
            }
        }
    }

    @Test
    fun `Loading response, returns valid loading resource`() {
        fakeLoginRepository.shouldReturnLoading()

        runBlocking {
            logoutUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }
}