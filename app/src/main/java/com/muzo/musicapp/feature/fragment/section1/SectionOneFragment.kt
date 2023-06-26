package com.muzo.musicapp.feature.fragment.section1


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.databinding.FragmentSectionOneBinding
import com.muzo.musicapp.feature.adapter.FirstPageAdapter
import com.muzo.musicapp.feature.viewmodel.PaginationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SectionOneFragment : Fragment() {
    private lateinit var binding: FragmentSectionOneBinding
    private lateinit var mvAdapter: FirstPageAdapter
    private val viewModel: PaginationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionOneBinding.inflate(inflater, container, false)

        setupRv()
        loadingData()

        return binding.root
    }

    private fun loadingData() {
        mvAdapter = FirstPageAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mvAdapter
        }

        lifecycleScope.launch {
            viewModel.getSearchResult("jack+johnson")
                .collect { pagingData ->
                    mvAdapter.submitData(pagingData)
                }
        }
    }

    private fun setupRv() {
        mvAdapter = FirstPageAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mvAdapter
        }
    }
}