package com.javi.testproject.data.mock

import com.javi.testproject.data.remote.UserApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserApiMock : UserApi {
    override fun logout(): Flow<Unit> {
        return flow {
            delay(100)
            emit(Unit)
        }
    }
}