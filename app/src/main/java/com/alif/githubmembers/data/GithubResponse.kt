package com.alif.githubmembers.data

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @field:SerializedName("items")
    val items: ArrayList<GithubUser>
)
