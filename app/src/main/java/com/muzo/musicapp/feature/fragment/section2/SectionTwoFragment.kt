package com.muzo.musicapp.feature.fragment.section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.FragmentSectionTwoBinding
import com.muzo.musicapp.feature.adapter.SecondPageAdapter
import com.muzo.musicapp.feature.fragment.BaseFragment
import com.muzo.musicapp.feature.fragment.detailFragment.DetailFragment
import com.muzo.musicapp.feature.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SectionTwoFragment : BaseFragment() {
    private lateinit var binding: FragmentSectionTwoBinding
    private lateinit var adapter: SecondPageAdapter
    private lateinit var list:List<Music>
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentSectionTwoBinding.inflate(LayoutInflater.from(context),container,false)


        val name=binding.included
        userInfo(name)
        observeData()
        return binding.root

    }


    private fun setupAdapter() {
        adapter = SecondPageAdapter(list)
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
                        binding.progressBar.visibility=View.VISIBLE
                        binding.rv2.visibility=View.GONE
                        binding.resultNumber.visibility=View.GONE
                    }
                    uiState.musicList != null -> {
                        binding.progressBar.visibility=View.GONE
                        binding.rv2.visibility=View.VISIBLE
                        binding.resultNumber.visibility=View.VISIBLE
                        // Veriler alındığında yapılacak işlemler
                        list = uiState.musicList.results
                        binding.resultNumber.text="${uiState.musicList.resultCount} results found "
                        setupAdapter()
                    }
                    else -> {
                        // Hata durumunda yapılacak işlemler
                    }
                }
            }
        }
    }


}