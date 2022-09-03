package com.dag.odev2fmss

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dag.odev2fmss.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.ibBack.setOnClickListener { finish() }

        binding.tvCreateAccount.setOnClickListener {
            Intent(this,SignUpActivity::class.java).apply { startActivity(this) }
        }
    }
}