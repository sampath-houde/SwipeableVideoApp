package com.example.internshipvideoapp

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipvideoapp.data.Data
import com.example.internshipvideoapp.data.VideoModel
import com.example.internshipvideoapp.databinding.ItemVideoBinding

class VideoAdapter(arrVideo:List<Data>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    var arrVideoModel:List<Data> = arrVideo


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrVideoModel.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        Log.d("List", arrVideoModel.toString())
        holder.setVideoData(arrVideoModel[position])
    }

    class VideoViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root){

        fun setVideoData(videoModel: Data){

            binding.tvTitle.text = videoModel.user_info.username
            binding.tvDesc.text = videoModel.description
            binding.videoView.setVideoPath("https"+videoModel.video.substring(4,videoModel.video.length-1))
            binding.videoView.setOnPreparedListener(object : MediaPlayer.OnPreparedListener{
                override fun onPrepared(mp: MediaPlayer) {
                    binding.progressBar.visibility = View.GONE
                    mp.start()

                    binding.videoView.setOnClickListener {
                        if(mp.isPlaying) {
                            binding.playPauseBtn.visibility = View.VISIBLE
                            mp.pause()
                        } else {
                            binding.playPauseBtn.visibility = View.GONE
                            mp.start()
                        }
                    }
                }
            })



            binding.videoView.setOnCompletionListener { object : MediaPlayer.OnCompletionListener{
                override fun onCompletion(mp: MediaPlayer) {
                    mp.start()
                }
            } }

        }

    }

}