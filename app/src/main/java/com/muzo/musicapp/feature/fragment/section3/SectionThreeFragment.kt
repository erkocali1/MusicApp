package com.muzo.musicapp.feature.fragment.section3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentSectionThreeBinding
import com.muzo.musicapp.databinding.FragmentSectionTwoBinding


class SectionThreeFragment : Fragment() {
    private lateinit var binding:FragmentSectionThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSectionThreeBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }
}