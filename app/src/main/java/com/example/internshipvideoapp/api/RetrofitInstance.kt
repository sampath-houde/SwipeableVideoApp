package com.example.internshipvideoapp.api

import com.example.internshipvideoapp.constants.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun <Api> buildApi(api: Class<Api>, accessToken: String? = null): Api {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $accessToken")
                        }.build())
                    }
                    .also { client ->
                        val logging =
                            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
