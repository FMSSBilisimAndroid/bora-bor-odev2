package com.dag.odev2fmss

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.preferences.core.edit
import com.dag.odev2fmss.databinding.ActivitySignUpBinding
import kotlinx.coroutines.runBlocking

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.ibBack.setOnClickListener { finish() }

        binding.btSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()


            val isEmailValid = checkEmail(email)
            val isUsernameValid = checkUsername(username)
            val isPasswordValid = checkPassword(password)

            // registering account and starting HomeActivity if the requirements are met
            if (isEmailValid && isUsernameValid && isPasswordValid) {
                registerAccount(email, username, password)

                Intent(this, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }
        }
    }

    /**
     * Checks if the email address is valid.
     *
     * @param email account email address
     * @return
     * true - if the email address is not empty and is a valid pattern.
     *
     * false - if the email address is empty or is not a valid pattern.
     */
    private fun checkEmail(email: String): Boolean {
        val textInputLayout = binding.tilEmail

        if (email.isEmpty()) {
            textInputLayout.error = getString(R.string.empty_email)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher((email)).matches()) {
            textInputLayout.error = getString(R.string.invalid_email)
            return false
        }

        textInputLayout.error = null
        return true
    }

    /**
     * Checks if the username is valid.
     *
     * @param username account username
     * @return
     * true - if the username is not empty and doesn't contain any space.
     *
     * false - if the username is empty or contains any space.
     */
    private fun checkUsername(username: String): Boolean {
        val textInputLayout = binding.tilUsername

        if (username.isEmpty()) {
            textInputLayout.error = getString(R.string.empty_username)
            return false
        }

        if (username.contains(" ")) {
            textInputLayout.error = getString(R.string.invalid_username)
            return false
        }

        textInputLayout.error = null
        return true
    }

    /**
     * Checks if the password is valid.
     *
     * @param password account password
     * @return
     * true - if the password is not empty and at least 6 characters long.
     *
     * false - if the password is empty or not long enough.
     */
    private fun checkPassword(password: String): Boolean {
        val textInputLayout = binding.tilPassword

        if (password.isEmpty()) {
            textInputLayout.error = getString(R.string.empty_password)
            return false
        }

        if (password.length < 6) {
            textInputLayout.error = getString(R.string.invalid_password)
            return false
        }

        textInputLayout.error = null
        return true
    }

    /**
     * Stores user information to the disk.
     *
     * @param email a valid email address
     * @param username a valid username
     * @param password a valid password
     */
    private fun registerAccount(email: String, username: String, password: String) = runBlocking {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.EMAIL] = email
            preferences[PreferencesKeys.USERNAME] = username
            preferences[PreferencesKeys.PASSWORD] = password
        }
    }
}