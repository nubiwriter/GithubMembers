package com.alif.githubmembers.data

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String
)
