package com.example.internshipvideoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.internshipvideoapp.api.RetrofitInstance
import com.example.internshipvideoapp.api.ShowAllVideos
import com.example.internshipvideoapp.data.VideoModel
import com.example.internshipvideoapp.data.VideoRepository
import com.example.internshipvideoapp.data.VideoViewModel
import com.example.internshipvideoapp.databinding.ActivityMainBinding
import com.example.topleaplifeskillsapp.login_register_section.api.ApiResponseHandler
import com.example.topleaplifeskillsapp.login_register_section.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    var arrVideoModel = ArrayList<VideoModel>()
    var videoAdapter:VideoAdapter? = null
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private lateinit var videoModel: VideoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window?.statusBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        }

        viewPager = binding.viewPager

        val videoViewModel = getViewModel()

//        arrVideoModel.add(VideoModel("Tree with flowers","The branches of a tree wave in the breeze, with pointy leaves ","https://assets.mixkit.co/videos/preview/mixkit-tree-with-yellow-flowers-1173-large.mp4"))
//        arrVideoModel.add(VideoModel("multicolored lights","A man with a small beard and mustache wearing a white sweater, sunglasses, and a backwards black baseball cap turns his head in different directions under changing colored lights.","https://assets.mixkit.co/videos/preview/mixkit-man-under-multicolored-lights-1237-large.mp4"))
//        arrVideoModel.add(VideoModel("holding neon light","Bald man with a short beard wearing a large jean jacket holds a long tubular neon light thatch","https://assets.mixkit.co/videos/preview/mixkit-man-holding-neon-light-1238-large.mp4"))
//        arrVideoModel.add(VideoModel("Sun over hills","The sun sets or rises over hills, a body of water beneath them.","https://assets.mixkit.co/videos/preview/mixkit-sun-over-hills-1183-large.mp4"))

        val videoModel = getVideoData(videoViewModel)



    }

    private fun getViewModel(): VideoViewModel {
        val retrofit = RetrofitInstance
        val repository = VideoRepository(retrofit.buildApi(ShowAllVideos::class.java, null))
        val factory = ViewModelFactory(repository)
        return ViewModelProvider(this, factory).get(VideoViewModel::class.java)
    }

    private fun getVideoData(videoViewModel: VideoViewModel){
        videoViewModel.getAllVideos()

        videoViewModel.videoData.observe(this, {response->
            Log.d("List", response.toString())
            when(response) {
                is ApiResponseHandler.Success -> {
                    videoAdapter = VideoAdapter(response.value.msg)
                    Log.d("List", response.value.msg.toString())
                    viewPager.adapter = videoAdapter
                }

                is ApiResponseHandler.Failure -> {
                    if(response.isNetworkError) {

                    }
                }
            }
        })
    }
}