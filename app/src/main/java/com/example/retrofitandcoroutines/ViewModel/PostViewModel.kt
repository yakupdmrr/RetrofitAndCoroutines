package com.example.retrofitandcoroutines.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.retrofitandcoroutines.Managers.DataManager
import com.example.retrofitandcoroutines.Services.PostRepository
import kotlinx.coroutines.Dispatchers

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun getData() = liveData(Dispatchers.IO) {
        emit(DataManager.loading(data = null))
        try {
            emit(DataManager.success(data = postRepository.getData()))
        } catch (exception: Exception) {
            emit(DataManager.error(data = null, message = exception.message ?: "Error"))
        }
    }
}