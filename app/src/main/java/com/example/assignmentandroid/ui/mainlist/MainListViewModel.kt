package com.example.assignmentandroid.ui.mainlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentandroid.data.model.AuthorData
import com.example.assignmentandroid.data.remote.AppApiHelper
import com.example.assignmentandroid.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor( private val appApiHelper: AppApiHelper) : ViewModel(){
    private val observableMutableLiveData = MutableLiveData<List<AuthorData>>()
    val observableLiveData: LiveData<List<AuthorData>> = observableMutableLiveData

    init {
        getAuthor()
    }

     fun getAuthor() {
        appApiHelper.invoke(2,20).onEach {
            when(it)
            {
                is ApiResponse.Success -> {
                    observableMutableLiveData.postValue(it.data ?: emptyList())

                }
                is ApiResponse.Error -> {
                    observableMutableLiveData.postValue(emptyList())

                }
                is ApiResponse.Loading -> {
                    observableMutableLiveData.postValue(emptyList())

                }
            }
        }.launchIn(viewModelScope)
    }
}