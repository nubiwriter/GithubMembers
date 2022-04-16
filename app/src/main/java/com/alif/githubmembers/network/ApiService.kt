package com.alif.githubmembers.network

import com.alif.githubmembers.data.DetailUserResponse
import com.alif.githubmembers.data.GithubResponse
import com.alif.githubmembers.data.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token xxxxxxxxxxxxxxxxxx")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token xxxxxxxxxxxxxxxxxx")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token xxxxxxxxxxxxxxxxxx")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<GithubUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token xxxxxxxxxxxxxxxxxx")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<GithubUser>>
}