package com.muzo.musicapp.feature.fragment.section1


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentSectionOneBinding
import com.muzo.musicapp.feature.adapter.FirstPageAdapter
import com.muzo.musicapp.feature.viewmodel.PaginationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        loadingData()

        return binding.root
    }

    private fun loadingData() {
        mvAdapter = FirstPageAdapter{

            item ->
            val bundle =Bundle()
            bundle.putString("artworkUrl100",item.artworkUrl100)
            bundle.putString("trackName",item.trackName)
            bundle.putString("artistName",item.artistName)
            bundle.putString("collectionName",item.collectionName)
            bundle.putString("releaseDate",item.releaseDate)
            bundle.putString("trackPrice",item.trackPrice.toString())
            bundle.putString("trackUrl",item.previewUrl)

            findNavController().navigate(R.id.action_sectionOneFragment_to_detailFragment,bundle)


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

}