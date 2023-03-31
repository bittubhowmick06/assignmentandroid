package com.example.assignmentandroid.data.remote

import com.example.assignmentandroid.data.model.AuthorData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("list")
    suspend fun getAuthor(@Query("page")page:Int,@Query("limit") limit:Int): List<AuthorData>
}