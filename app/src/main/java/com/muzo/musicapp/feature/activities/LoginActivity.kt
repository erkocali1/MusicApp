package com.muzo.musicapp.feature.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.ActivityLoginBinding
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
        loginName()
        enter()
    }


    private fun enter() {
        binding.logButton.setOnClickListener {

            sharedPreferences = getSharedPreferences("entryInformation", MODE_PRIVATE)
            val userName = binding.etName.text.toString()

            if (userName.isNotEmpty()) {
                val sp = sharedPreferences.edit()
                sp.putString("userName", userName)
                sp.apply()

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)


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

    private fun loginInfo() {

        sharedPreferences = getSharedPreferences("entryInformation", MODE_PRIVATE)

        val authenticationInfo = sharedPreferences.getString("userName", "")

        if (authenticationInfo!!.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun loginName(){
        binding.includedToolbar.UserName.text="Are U Ready For Fun "
    }
}