package com.dag.odev2fmss

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

interface Authenticator {
    /**
     * Stores user information to the disk.
     *
     * @param email a valid email address
     * @param username a valid username
     * @param password a valid password
     */
    fun registerAccount(email: String, username: String, password: String)

    /**
     * Gets user information from the disk.
     *
     * @return user information as [User].
     */
    fun getUserInfo(): User
}

class AuthenticatorImpl(private val context: Context) : Authenticator {
    override fun registerAccount(email: String, username: String, password: String) {
        runBlocking {
            context.dataStore.edit { preferences ->
                preferences[PreferencesKeys.EMAIL] = email
                preferences[PreferencesKeys.USERNAME] = username
                preferences[PreferencesKeys.PASSWORD] = password
            }
        }
    }

    override fun getUserInfo() = runBlocking {
        context.dataStore.data.map { preferences ->
            val email = preferences[PreferencesKeys.EMAIL] ?: ""
            val username = preferences[PreferencesKeys.USERNAME] ?: ""
            val password = preferences[PreferencesKeys.PASSWORD] ?: ""

            User(email = email, username = username, password = password)
        }.first()
    }
}