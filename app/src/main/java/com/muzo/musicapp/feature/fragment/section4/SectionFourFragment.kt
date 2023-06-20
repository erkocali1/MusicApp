package com.muzo.musicapp.feature.fragment.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentSectionFourBinding


class SectionFourFragment : Fragment() {
    private lateinit var binding:FragmentSectionFourBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSectionFourBinding.inflate(LayoutInflater.from(context),container,false)

        return binding.root
    }


}