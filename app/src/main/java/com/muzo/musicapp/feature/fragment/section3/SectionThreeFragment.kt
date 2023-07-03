package com.muzo.musicapp.feature.fragment.section3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import com.muzo.musicapp.databinding.FragmentSectionThreeBinding
import com.muzo.musicapp.feature.adapter.ThirdPageAdapter
import com.muzo.musicapp.feature.fragment.section2.SectionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SectionThreeFragment : Fragment() {
    private lateinit var binding:FragmentSectionThreeBinding
    private lateinit var adapter: ThirdPageAdapter
    private val viewModel:SectionsViewModel by viewModels()
    private lateinit var list:List<MusicLocalData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSectionThreeBinding.inflate(LayoutInflater.from(context),container,false)


        observeData()

        return binding.root

    }



    private fun setupAdapter() {
        adapter = ThirdPageAdapter(list)
        binding.rv2.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SectionThreeFragment.adapter
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
                    uiState.musicListLocal != null -> {
                        binding.progressBar.visibility=View.GONE
                        binding.rv2.visibility=View.VISIBLE
                        binding.resultNumber.visibility=View.VISIBLE
                        // Veriler alındığında yapılacak işlemler
                        list = uiState.musicListLocal
                        binding.resultNumber.text="${list.size} adet sonuc bulundu "
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