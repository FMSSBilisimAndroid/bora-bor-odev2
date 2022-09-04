package com.dag.odev2fmss

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dag.odev2fmss.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.ibBack.setOnClickListener { finish() }


        binding.btLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // initializing user by getting user information from the disk
            user = AuthenticatorImpl(this).getUserInfo()

            val isUsernameCorrect = checkUsername(username)
            val isPasswordCorrect = checkPassword(password)

            // showing toast and starting HomeActivity if conditions are met
            if (isUsernameCorrect && isPasswordCorrect) {
                Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()

                Intent(this, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }
        }

        binding.tvCreateAccount.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply { startActivity(this) }
        }
    }

    /**
     * Checks if the username is correct.
     *
     * @param username account username
     * @return
     * true - if the username is correct.
     *
     * false - if the username is not correct.
     */
    private fun checkUsername(username: String): Boolean {
        val textInputLayout = binding.tilUsername

        if (username.isEmpty()) {
            textInputLayout.error = getString(R.string.empty_username)
            return false
        }

        if (username != user.username) {
            textInputLayout.error = getString(R.string.incorrect_username)
            return false
        }

        textInputLayout.error = null
        return true
    }

    /**
     * Checks if the password is correct.
     *
     * @param password account password
     * @return
     * true - if the password is correct.
     *
     * false - if the password is not correct.
     */
    private fun checkPassword(password: String): Boolean {
        val textInputLayout = binding.tilPassword

        if (password.isEmpty()) {
            textInputLayout.error = getString(R.string.empty_password)
            return false
        }

        if (password != user.password) {
            textInputLayout.error = getString(R.string.incorrect_password)
            return false
        }

        textInputLayout.error = null
        return true
    }
}