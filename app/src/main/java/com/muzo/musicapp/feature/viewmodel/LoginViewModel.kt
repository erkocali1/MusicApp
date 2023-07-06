package com.muzo.musicapp.feature.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject




@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : ViewModel() {
    private var sharedPreferences =
        application.getSharedPreferences("entryInformation", Context.MODE_PRIVATE)




}