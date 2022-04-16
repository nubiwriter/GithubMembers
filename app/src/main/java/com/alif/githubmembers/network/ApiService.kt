package com.alif.githubmembers.network

import com.alif.githubmembers.data.DetailUserResponse
import com.alif.githubmembers.data.GithubResponse
import com.alif.githubmembers.data.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

// substitute "xxx" with your Personal Access Token from Github

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token xxx")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token xxx")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token xxx")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<GithubUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token xxx")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<GithubUser>>
}