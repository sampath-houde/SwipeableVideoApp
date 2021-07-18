package com.example.internshipvideoapp.constants

import com.example.topleaplifeskillsapp.login_register_section.api.ApiResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun<T> safeApiCall(
        apiCall: suspend () -> T
    ) : ApiResponseHandler<T>{
        return withContext(Dispatchers.IO) {
            try {
                ApiResponseHandler.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        ApiResponseHandler.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }

                    else -> {
                        ApiResponseHandler.Failure(true, null, null)
                    }
                }
            }
        }
    }



}