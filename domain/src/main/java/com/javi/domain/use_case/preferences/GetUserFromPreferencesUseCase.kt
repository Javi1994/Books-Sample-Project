package com.javi.domain.use_case.preferences

import com.javi.data.datasource.local.UserPreferences
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserFromPreferencesUseCase @Inject constructor(
    private val userPreferences: UserPreferences
) : BaseUseCase() {

    operator fun invoke(): Flow<User?> {
        return userPreferences.getUser()
            .map {
                it?.toUser()
            }
    }
}