package com.muzo.musicapp.feature.fragment.section3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.databinding.FragmentSectionThreeBinding
import com.muzo.musicapp.feature.adapter.ThirdPageAdapter
import com.muzo.musicapp.feature.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SectionThreeFragment : BaseFragment() {
    private lateinit var binding: FragmentSectionThreeBinding
    private lateinit var adapter: ThirdPageAdapter
    private val viewModel: SectionThreeViewModel by viewModels()
    private lateinit var list: List<LastClikedMusic>
    private lateinit var builder: AlertDialog.Builder


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSectionThreeBinding.inflate(LayoutInflater.from(context), container, false)

        val name = binding.included
        userInfo(name)
        observeData()
        clickListener()

        return binding.root

    }


    private fun setupAdapter() {
        adapter = ThirdPageAdapter(list) { item ->

            navigateToDetailFragment(item)

        }
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
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rv2.visibility = View.GONE
                        binding.resultNumber.visibility = View.GONE
                    }

                    uiState.musicListLocal != null -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rv2.visibility = View.VISIBLE
                        binding.resultNumber.visibility = View.VISIBLE

                        list = uiState.musicListLocal
                        binding.resultNumber.text = "Last listened  ${list.size} results found "
                        setupAdapter()

                    }

                    else -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rv2.visibility = View.VISIBLE
                        binding.resultNumber.visibility = View.VISIBLE

                        list = emptyList() // Boş bir liste oluştur
                        binding.resultNumber.text = "0 results found"
                        setupAdapter()
                    }
                }
            }
        }
    }

    private fun navigateToDetailFragment(item: LastClikedMusic) {

        val bundle = Bundle().apply {
            putString("artworkUrl100", item.artworkUrl100)
            putString("trackName", item.trackName)
            putString("artistName", item.artistName)
            putString("collectionName", item.collectionName)
            putString("releaseDate", item.releaseDate)
            putString("trackPrice", item.trackPrice.toString())
            putString("trackUrl", item.previewUrl)

        }

        findNavController().navigate(R.id.action_sectionThreeFragment_to_detailFragment, bundle)

    }

    private fun clickListener() {

        binding.icClearAll.setOnClickListener {
            alertDialog()

        }


    }

    private fun alertDialog() {
        builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete List")
            .setMessage("Do you want to delete the entire list of recently observed?")
            .setCancelable(true)
            .setIcon(R.drawable.ic_delete)
            .setNegativeButton("NO"){dialogInterface,_ ->}
            .setPositiveButton("OK") { dialogInterface,_ ->

                lifecycleScope.launch {
                    viewModel.deleteAll()
                }


            }
            .show()
    }

}