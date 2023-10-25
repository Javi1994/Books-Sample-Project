package com.javi.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.javi.data.Constants.USER_TOKEN_KEY
import com.javi.data.Constants.USER_USERNAME_KEY
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveUser(user: UserDto) {
        dataStore.edit { preferences ->
            preferences[USER_USERNAME_KEY] = user.username
            preferences[USER_TOKEN_KEY] = user.token
        }
        println("User saved to preferences: $user")
    }

    fun hasData(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences.contains(USER_USERNAME_KEY) && preferences.contains(USER_TOKEN_KEY)
            }
    }

    suspend fun getUser(): Flow<UserDto?> {
        return dataStore.data.map { preferences ->
            val hasData = hasData().firstOrNull()
            if (hasData == true) {
                val username = preferences[USER_USERNAME_KEY]
                val token = preferences[USER_TOKEN_KEY]

                UserDto(
                    username = username!!,
                    token = token!!
                )
            } else {
                null
            }
        }
    }

    suspend fun clearPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}