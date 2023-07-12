package com.muzo.musicapp.feature.fragment.section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.FragmentSectionTwoBinding
import com.muzo.musicapp.feature.adapter.SecondPageAdapter
import com.muzo.musicapp.feature.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SectionTwoFragment : BaseFragment() {
    private lateinit var binding: FragmentSectionTwoBinding
    private lateinit var adapter: SecondPageAdapter
    private lateinit var list: List<Music>
    private val viewModel: SectionTwoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionTwoBinding.inflate(LayoutInflater.from(context), container, false)

        val name = binding.included
        userInfo(name)
        observeData()
        return binding.root
    }


    private fun setupAdapter() {
        adapter = SecondPageAdapter(list) { item ->

            navigateToDetailFragment(item)

            lifecycleScope.launch {

                val lastClickedList=viewModel.convertToMusicLocalDataList(item)
                viewModel.saveLastClicked(lastClickedList)

            }
        }
        binding.rv2.apply {
            layoutManager = GridLayoutManager(requireContext(), 2) // spanCount değerini belirleyin
            adapter = this@SectionTwoFragment.adapter
        }
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel._uiState.collect { uiState ->
                when {
                    uiState.loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rv2.visibility = View.GONE
                        binding.resultNumber.visibility = View.GONE
                    }

                    uiState.musicList != null -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rv2.visibility = View.VISIBLE
                        binding.resultNumber.visibility = View.VISIBLE

                        // Veriler alındığında yapılacak işlemler
                        list = uiState.musicList.results
                        binding.resultNumber.text =
                            "${uiState.musicList.resultCount} results found "
                        setupAdapter()
                    }

                    else -> {
                        // Hata durumunda yapılacak işlemler
                    }
                }
            }
        }
    }

    private fun navigateToDetailFragment(item: Music) {

        val bundle = Bundle().apply {
            putString("artworkUrl100", item.artworkUrl100)
            putString("trackName", item.trackName)
            putString("artistName", item.artistName)
            putString("collectionName", item.collectionName)
            putString("releaseDate", item.releaseDate)
            putString("trackPrice", item.trackPrice.toString())
            putString("trackUrl", item.previewUrl)
            putInt("artistId",item.artistId)

        }

        findNavController().navigate(R.id.action_sectionTwoFragment_to_detailFragment, bundle)

    }


}