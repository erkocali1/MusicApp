package com.muzo.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.muzo.musicapp.databinding.ActivityMainBinding
import com.muzo.musicapp.feature.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
 private  lateinit  var binding:ActivityMainBinding

    private val viewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            viewModel.getMusic()
        }
    }
    private fun setupTabBar(){

        binding.menu.setOnItemSelectedListener {

        }
    }
}