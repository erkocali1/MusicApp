package com.muzo.musicapp.feature.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.ActivityMainBinding
import com.muzo.musicapp.feature.fragment.section1.SectionOneFragment
import com.muzo.musicapp.feature.fragment.section2.SectionTwoFragment
import com.muzo.musicapp.feature.fragment.section3.SectionThreeFragment
import com.muzo.musicapp.feature.fragment.section4.SectionFourFragment
import com.muzo.musicapp.feature.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView


    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomBar()


        lifecycleScope.launch {
            viewModel.getMusic()
        }
    }


    private fun setupBottomBar() {
       navController= Navigation.findNavController(this,R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(binding.bottomenu,navController)
    }
}