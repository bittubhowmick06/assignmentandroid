package com.example.assignmentandroid.data.remote.repository

import com.example.assignmentandroid.data.model.AuthorData

interface ApiRepositoryHelper {
    suspend fun getAuthor(page :Int,limit:Int): List<AuthorData>
}