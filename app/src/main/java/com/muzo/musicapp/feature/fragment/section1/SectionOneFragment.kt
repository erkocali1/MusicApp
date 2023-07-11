package com.muzo.musicapp.feature.fragment.section1


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.databinding.FragmentSectionOneBinding
import com.muzo.musicapp.feature.adapter.FirstPageAdapter
import com.muzo.musicapp.feature.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SectionOneFragment : BaseFragment() {
    private lateinit var binding: FragmentSectionOneBinding
    private lateinit var mvAdapter: FirstPageAdapter
    private val viewModel: PaginationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionOneBinding.inflate(inflater, container, false)

        val name = binding.included
        userInfo(name)
        loadingData()




        return binding.root
    }

    private fun loadingData() {
        mvAdapter = FirstPageAdapter { item ->

            navigateToDetailFragment(item)

        }
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mvAdapter

            mvAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }

        }

        lifecycleScope.launch {
            viewModel.getSearchResult("jack+johnson")
                .collectLatest { pagingData ->
                    mvAdapter.submitData(pagingData)
                }

        }
    }

    private fun navigateToDetailFragment(item: PaginationList) {

        val bundle = Bundle().apply {
            putString("artworkUrl100", item.artworkUrl100)
            putString("trackName", item.trackName)
            putString("artistName", item.artistName)
            putString("collectionName", item.collectionName)
            putString("releaseDate", item.releaseDate)
            putString("trackPrice", item.trackPrice.toString())
            putString("trackUrl", item.previewUrl)
        }

        findNavController().navigate(R.id.action_sectionOneFragment_to_detailFragment, bundle)

    }


}