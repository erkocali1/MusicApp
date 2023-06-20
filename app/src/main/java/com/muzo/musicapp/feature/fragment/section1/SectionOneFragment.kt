package com.muzo.musicapp.feature.fragment.section1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentSectionOneBinding


class SectionOneFragment : Fragment() {
    private lateinit var binding: FragmentSectionOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSectionOneBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root

}}