package com.example.nguyenngocsonc.myapplication.models

data class Feed (
        var fullName: String,
        var avatarUrl: String,
        var createAt: String,
        var feedContent: String,
        var visibleMode: String,
        var reactionCount: Int,
        var commentCount: Int,
        var sharingCount: Int,
        var feedImages: ArrayList<String>
)
