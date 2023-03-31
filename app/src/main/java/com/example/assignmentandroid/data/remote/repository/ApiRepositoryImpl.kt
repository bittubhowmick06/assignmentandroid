package com.example.assignmentandroid.data.remote.repository

import com.example.assignmentandroid.data.model.AuthorData
import com.example.assignmentandroid.data.remote.ApiClient
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiClient
): ApiRepositoryHelper {


    override suspend fun getAuthor(page: Int, limit: Int): List<AuthorData> {
        return  api.getAuthor(page,limit)
    }
}