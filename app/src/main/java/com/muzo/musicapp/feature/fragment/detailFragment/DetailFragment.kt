package com.muzo.musicapp.feature.fragment.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.muzo.musicapp.databinding.FragmentDetailBinding
import com.muzo.musicapp.feature.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)



        loadData()
        return binding.root
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel._uiState.collect { uiState ->

                when {
                    uiState.loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            IvSigner.visibility=View.GONE
                            trackName.visibility = View.GONE
                            signerName.visibility = View.GONE
                            collectionName.visibility = View.GONE
                            trackPrice.visibility = View.GONE
                            releaseDate.visibility = View.GONE
                            iconPlay.visibility = View.GONE
                        }
                    }

                    uiState.musicList != null -> {

                        binding.apply {
                            getData()
                            progressBar.visibility = View.GONE
                            IvSigner.visibility=View.VISIBLE
                            trackName.visibility = View.VISIBLE
                            signerName.visibility = View.VISIBLE
                            collectionName.visibility = View.VISIBLE
                            trackPrice.visibility = View.VISIBLE
                            releaseDate.visibility = View.VISIBLE
                            iconPlay.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
    private fun getData(){
        val Ivurl=arguments?.getString("artworkUrl100")
        val trackName=arguments?.getString("trackName")
        val signerName=arguments?.getString("artistName")
        val collectionName=arguments?.getString("collectionName")
        val trackPrice=arguments?.getString("trackPrice")
        val releaseDate=arguments?.getString("releaseDate")

        binding.IvSigner.load(Ivurl){
            crossfade(true)
            crossfade(1000)
        }
        binding.trackName.text=trackName
        binding.signerName.text=signerName
        binding.collectionName.text=collectionName
        binding.trackPrice.text=trackPrice
        binding.releaseDate.text=releaseDate

    }
}