package com.muzo.musicapp.feature.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagingDataAdapter
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.databinding.ActivityMainBinding
import com.muzo.musicapp.feature.adapter.FirstPageAdapter
import com.muzo.musicapp.feature.viewmodel.HomeViewModel
import com.muzo.musicapp.feature.viewmodel.PaginationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    private val viewModelx: PaginationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomBar()

    }


    private fun setupBottomBar() {
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(binding.bottomenu, navController)
    }


}