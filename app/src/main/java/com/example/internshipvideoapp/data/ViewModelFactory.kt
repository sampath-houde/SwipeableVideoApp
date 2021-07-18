package com.example.topleaplifeskillsapp.login_register_section.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internshipvideoapp.constants.BaseRepository
import com.example.internshipvideoapp.data.VideoRepository
import com.example.internshipvideoapp.data.VideoViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(VideoViewModel::class.java) -> VideoViewModel(repository as VideoRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }

}