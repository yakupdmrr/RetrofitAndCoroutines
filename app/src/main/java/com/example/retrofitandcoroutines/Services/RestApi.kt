package com.example.retrofitandcoroutines.Services

import com.example.retrofitandcoroutines.Model.PostModel
import retrofit2.http.GET

interface RestApi {

    @GET("posts")
    suspend fun getData(): List<PostModel>
}