package com.javi.testproject.data.datasource.mock

import com.javi.testproject.data.datasource.remote.UserApi
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