package com.javi.domain.use_case.user

import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.repository.FakeUserData
import com.javi.data.repository.FakeUserRepository
import com.javi.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetUserUseCaseTest {

    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserUseCase = GetUserUseCase(fakeUserRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `Success response, returns valid success resource`() {
        fakeUserRepository.setUserData(FakeUserData.validUser)

        runBlocking {
            getUserUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(User::class.java)
            }
        }
    }

    @Test
    fun `Success response with empty data, returns empty data error resource with nullpointer`() {
        fakeUserRepository.setUserData(FakeUserData.emptyUser)

        runBlocking {
            getUserUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(NullPointerException::class.java)
            }
        }
    }

    @Test
    fun `Error response, returns valid error resource`() {
        fakeUserRepository.shouldReturnError()

        runBlocking {
            getUserUseCase().collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(IOException::class.java)
            }
        }
    }

    @Test
    fun `Loading response, returns valid loading resource`() {
        fakeUserRepository.shouldReturnLoading()

        runBlocking {
            getUserUseCase().collect {
               assertThat(it).isInstanceOf(Resource.Loading::class.java)
               assertThat(it.isLoading).isTrue()
            }
        }
    }
}