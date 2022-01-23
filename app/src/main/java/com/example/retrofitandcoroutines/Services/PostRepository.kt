package com.example.retrofitandcoroutines.Services

class PostRepository(private val restApiHelper: RestApiHelper) {
    suspend fun getData() = restApiHelper.getData()
}