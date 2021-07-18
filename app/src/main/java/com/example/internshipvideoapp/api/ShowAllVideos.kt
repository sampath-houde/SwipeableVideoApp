package com.example.internshipvideoapp.api

import com.example.internshipvideoapp.data.VideoModel
import retrofit2.http.GET

interface ShowAllVideos {

    @GET("/app_api/index.php?p=showAllVideos")
    suspend fun getAllVideos() : VideoModel

}