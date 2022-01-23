package com.example.retrofitandcoroutines.ViewModel.BaseViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitandcoroutines.Services.PostRepository
import com.example.retrofitandcoroutines.Services.RestApiHelper
import com.example.retrofitandcoroutines.ViewModel.PostViewModel

class BaseViewModel(private val restApiHelper: RestApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PostViewModel::class.java))
            return PostViewModel(PostRepository(restApiHelper)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}