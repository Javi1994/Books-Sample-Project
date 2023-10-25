package com.javi.domain.use_case.preferences

import com.javi.data.datasource.local.UserPreferences
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserFromPreferencesUseCase @Inject constructor(
    private val userPreferences: UserPreferences,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): Flow<User?> = withContext(defaultDispatcher) {
        return@withContext userPreferences.getUser()
            .map {
                it?.toUser()
            }
    }
}