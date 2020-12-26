package com.example.zxy2018110465.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "I am clicked!"
    }
    val text: LiveData<String> = _text
}