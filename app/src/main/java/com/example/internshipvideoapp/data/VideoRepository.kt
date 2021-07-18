package com.example.internshipvideoapp.data

import com.example.internshipvideoapp.api.ShowAllVideos
import com.example.internshipvideoapp.constants.BaseRepository

class VideoRepository(private val api: ShowAllVideos) : BaseRepository() {

    suspend fun getAllVideos() = safeApiCall {
        api.getAllVideos()
    }
}