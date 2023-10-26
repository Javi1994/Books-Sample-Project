package com.javi.data

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val DATABASE_NAME = "SampleBookProject"
    const val USER_PREFERENCES = "user_preferences"

    val USER_USERNAME_KEY = stringPreferencesKey("user_username")
    val USER_TOKEN_KEY = stringPreferencesKey("user_token")
}