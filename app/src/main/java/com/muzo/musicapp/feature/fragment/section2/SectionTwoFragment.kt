package com.muzo.musicapp.feature.fragment.section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentSectionOneBinding
import com.muzo.musicapp.databinding.FragmentSectionTwoBinding

class SectionTwoFragment : Fragment() {
    private lateinit var binding: FragmentSectionTwoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSectionTwoBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

}