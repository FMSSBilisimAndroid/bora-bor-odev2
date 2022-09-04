package com.dag.odev2fmss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dag.odev2fmss.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.user = getUserInfo()
    }

    /**
     * Gets user information from the disk.
     *
     * @return user information as [User].
     */
    private fun getUserInfo(): User = runBlocking {
        dataStore.data.map { preferences ->
            val email = preferences[PreferencesKeys.EMAIL] ?: ""
            val username = preferences[PreferencesKeys.USERNAME] ?: ""
            val password = preferences[PreferencesKeys.PASSWORD] ?: ""

            User(email = email, username = username, password = password)
        }.first()
    }
}