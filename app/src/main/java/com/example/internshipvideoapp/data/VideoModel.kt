package com.example.internshipvideoapp.data


data class VideoModel(
    var s: String,
    var code: String,
    var msg: List<Data>
)

data class Data(
    var tp: Int,
    var uid: String,
    var liked: Long,
    var score: Long,
    var status: String,
    var is_block: Int,
    var description: String,
    var city: String,
    var _id: String,
    var id: String,
    var fb_id: String,
    var user_info: User,
    var count: Count,
    var video: String,
    var thum: String,
    var gif: String,
    var sound: Sound,
    var created: String,
    var __v: Int
)

data class User(
    var first_name: String,
    var last_name: String,
    var fb_id: String,
    var profile_pic: String,
    var gender: String,
    var verified: String,
    var _id: String,
    var username: String
)

data class Count(
    var like_count: Long,
    var video_comment_count: Long,
    var view: Long,
    var _id: String
)

data class Sound(
    var id: Long,
    var sound_name: String,
    var description: String,
    var thum: String,
    var section: String,
    var _id: String,
    var created: String,
    var audio_path: AudioPath,

)

data class AudioPath(
    var mp3: String,
    var acc: String
)