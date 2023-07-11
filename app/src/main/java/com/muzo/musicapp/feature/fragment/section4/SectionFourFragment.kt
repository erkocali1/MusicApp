package com.muzo.musicapp.feature.fragment.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
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
    private lateinit var list: List<MusicLocalData>


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
        adapter = ForthPageAdapter(list){item ->
            lifecycleScope.launch {
                    viewModel.deleteRoom(item.uid)
            }
        }
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
                    uiState.musicListLocal != null -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            rv3.visibility = View.VISIBLE
                            list = uiState.musicListLocal
                            setAdapter()
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}