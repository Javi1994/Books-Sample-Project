package com.javi.domain.use_case.login

import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.repository.FakeLoginRepository
import com.javi.data.repository.FakeUserData
import com.javi.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LoginWithTokenUseCaseTest {

    private lateinit var loginWithTokenUseCase: LoginWithTokenUseCase
    private lateinit var fakeLoginRepository: FakeLoginRepository

    @Before
    fun setUp() {
        fakeLoginRepository = FakeLoginRepository()
        loginWithTokenUseCase = LoginWithTokenUseCase(fakeLoginRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `Success response, returns valid success resource`() {
        fakeLoginRepository.setUserData(FakeUserData.validUser)

        runBlocking {
            loginWithTokenUseCase("123").collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(User::class.java)
            }
        }
    }

    @Test
    fun `Success response with empty data, returns empty data error resource with nullpointer`() {
        fakeLoginRepository.setUserData(FakeUserData.emptyUser)

        runBlocking {
            loginWithTokenUseCase("123").collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(NullPointerException::class.java)
            }
        }
    }

    @Test
    fun `Error response, returns valid error resource`() {
        fakeLoginRepository.shouldReturnError()

        runBlocking {
            loginWithTokenUseCase("123").collect {
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
            loginWithTokenUseCase("123").collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }
}