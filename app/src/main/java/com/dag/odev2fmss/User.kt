package com.dag.odev2fmss

import android.content.Context
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

data class User(
    val email: String,
    val username: String,
    val password: String
) {
    companion object {
        /**
         * Gets user information from the disk.
         *
         * @return user information as [User].
         */
        fun getUserInfo(context: Context) = runBlocking {
            context.dataStore.data.map { preferences ->
                val email = preferences[PreferencesKeys.EMAIL] ?: ""
                val username = preferences[PreferencesKeys.USERNAME] ?: ""
                val password = preferences[PreferencesKeys.PASSWORD] ?: ""

                User(email = email, username = username, password = password)
            }.first()
        }
    }
}

