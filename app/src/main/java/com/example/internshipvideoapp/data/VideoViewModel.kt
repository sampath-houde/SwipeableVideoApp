package com.example.internshipvideoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topleaplifeskillsapp.login_register_section.api.ApiResponseHandler
import kotlinx.coroutines.launch

class VideoViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _videoData: MutableLiveData<ApiResponseHandler<VideoModel>> = MutableLiveData()

    val videoData: LiveData<ApiResponseHandler<VideoModel>>
        get() = _videoData

    fun getAllVideos() = viewModelScope.launch {
        _videoData.value = repository.getAllVideos()
    }

}