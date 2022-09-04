package com.dag.odev2fmss

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dag.odev2fmss.databinding.ActivitySignUpBinding

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

            // registering account, showing toast, and finishing this activity if the conditions are met
            if (isEmailValid && isUsernameValid && isPasswordValid) {
                AuthenticatorImpl(this).registerAccount(email, username, password)

                Toast.makeText(this, getString(R.string.sign_up_successful), Toast.LENGTH_SHORT).show()

                finish()
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
}