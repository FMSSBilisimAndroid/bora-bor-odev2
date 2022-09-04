package com.dag.odev2fmss

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

const val USER_PREFERENCES = "user_preferences"

val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)

object PreferencesKeys {
    val EMAIL = stringPreferencesKey("email")
    val USERNAME = stringPreferencesKey("username")
    val PASSWORD = stringPreferencesKey("password")
}