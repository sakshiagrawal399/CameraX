package com.android.camerax

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.camerax.db.ImageRepository

class MainActivityViewModelFactory(private val repository: ImageRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}