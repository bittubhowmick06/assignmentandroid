package com.example.assignmentandroid.data.remote

import com.example.assignmentandroid.data.model.AuthorData
import com.example.assignmentandroid.data.remote.repository.ApiRepositoryHelper
import com.example.assignmentandroid.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AppApiHelper @Inject constructor(
    private val repository: ApiRepositoryHelper
) {
    operator fun invoke(page:Int,limit:Int): Flow<ApiResponse<List<AuthorData>>> = flow {
        try {
            emit(ApiResponse.Loading())
            val dailyQuote = repository.getAuthor(page, limit).map { it }
            emit(ApiResponse.Success(dailyQuote))
        } catch (e: HttpException) {
            emit(ApiResponse.Error("An unexpected error occurred! \uD83D\uDE31"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("Server unreachable, check your internet connection \uD83E\uDD15"))
        }
    }
}
