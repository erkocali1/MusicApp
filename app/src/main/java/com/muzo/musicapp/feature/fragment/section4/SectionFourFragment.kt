package com.muzo.musicapp.feature.fragment.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.FragmentSectionFourBinding
import com.muzo.musicapp.feature.adapter.ForthPageAdapter
import com.muzo.musicapp.feature.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SectionFourFragment : BaseFragment() {
    private lateinit var binding: FragmentSectionFourBinding
    private lateinit var adapter: ForthPageAdapter
    private val viewModel: SectionFourViewModel by viewModels()
    private lateinit var list: List<FavLocalData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionFourBinding.inflate(LayoutInflater.from(context), container, false)

        val name=binding.included
        userInfo(name)
        observeData()
        return binding.root

    }


    private fun setAdapter() {
        adapter = ForthPageAdapter(list,
            onMusicClickListener = { item ->
                lifecycleScope.launch {
                    navigateToDetailFragment(item)
                }
            },
            onDeleteClickListener = { item ->
                lifecycleScope.launch {
                    viewModel.deleteFromRoom(item.uid)
                }
            }
        )
        binding.apply {
            rv3.adapter = adapter
            rv3.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun observeData() {

        lifecycleScope.launch {
            viewModel._uiState.collect { uiState ->
                when {
                    uiState.loading -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            rv3.visibility = View.GONE
                        }
                    }
                    uiState.favMusicList != null -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            rv3.visibility = View.VISIBLE
                            list = uiState.favMusicList
                            setAdapter()
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
    private fun navigateToDetailFragment(item: FavLocalData) {

        val bundle = Bundle().apply {
            putString("artworkUrl100", item.artworkUrl100)
            putString("trackName", item.trackName)
            putString("artistName", item.artistName)
            putString("collectionName", item.collectionName)
            putString("releaseDate", item.releaseDate)
            putString("trackPrice", item.trackPrice)
            putString("trackUrl", item.previewUrl)
            putBoolean("isFav",true)
        }

        findNavController().navigate(R.id.action_sectionFourFragment_to_detailFragment, bundle)

    }
}