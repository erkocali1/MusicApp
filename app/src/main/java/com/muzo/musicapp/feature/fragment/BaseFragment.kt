package com.muzo.musicapp.feature.fragment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.muzo.musicapp.databinding.MyToolbarBinding

open class BaseFragment:Fragment() {
    lateinit var sharedPreferences: SharedPreferences


    protected fun  userInfo(binding:MyToolbarBinding){

        sharedPreferences =requireActivity().getSharedPreferences("entryInformation", AppCompatActivity.MODE_PRIVATE)

        val userName=sharedPreferences.getString("userName","")

        binding.UserName.text="Welcome $userName enjoy it!"

    }

}