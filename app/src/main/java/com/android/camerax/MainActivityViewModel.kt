package com.android.camerax

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.camerax.db.Event
import com.android.camerax.db.Image
import com.android.camerax.db.ImageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: ImageRepository) : ViewModel(), Observable {

    val albums = repository.albums
    val images = repository.images

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    fun insert(image: Image): Job =
        viewModelScope.launch {
            val newRowId = repository.insert(image)
            if (newRowId > -1) {
                statusMessage.value = Event("$newRowId Insert Successfully!!")
            } else {
                statusMessage.value = Event("Insert Error!!")
            }

        }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}