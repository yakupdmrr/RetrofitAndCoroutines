package com.example.retrofitandcoroutines.Services

class RestApiHelper(private val restApi: RestApi) {
    suspend fun getData() = restApi.getData()
}