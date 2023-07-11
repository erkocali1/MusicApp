package com.muzo.musicapp.feature.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muzo.musicapp.R
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.MyToolbarBinding

open class BaseFragment:Fragment() {
    lateinit var sharedPreferences: SharedPreferences


    protected fun  userInfo(binding:MyToolbarBinding){

        sharedPreferences =requireActivity().getSharedPreferences("entryInformation", AppCompatActivity.MODE_PRIVATE)

        val userName=sharedPreferences.getString("userName","")

        binding.UserName.text="Welcome $userName enjoy it!"

    }




}