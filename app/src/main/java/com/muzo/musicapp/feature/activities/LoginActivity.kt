package com.muzo.musicapp.feature.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.ActivityLoginBinding
import com.muzo.musicapp.feature.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            loginInfo()
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enter()
    }


    private fun enter() {
        binding.logButton.setOnClickListener {

            sharedPreferences = getSharedPreferences("entryInformation", MODE_PRIVATE)
            val userName = binding.etName.text.toString()

            if (userName.isNotEmpty()) {
                val sp = sharedPreferences.edit()
                sp.putString("userName",userName)
                sp.apply()
                startActivity(Intent(this, MainActivity::class.java))
            } else
                alertDialog()
        }
    }

    private fun alertDialog() {
        builder = AlertDialog.Builder(this)
        builder.setTitle("Login Error!")
            .setMessage("You can continue without Username")
            .setCancelable(true)
            .setIcon(R.drawable.ic_person)
            .setPositiveButton("OK") { dialogInterface, it ->
            }
            .show()
    }

    private fun loginInfo(){

        sharedPreferences = getSharedPreferences("entryInformation", MODE_PRIVATE)

        val authenticationInfo=sharedPreferences.getString("userName","")

        if (authenticationInfo!!.isNotEmpty()){
            startActivity(Intent(this,MainActivity::class.java ))
        }
    }
}