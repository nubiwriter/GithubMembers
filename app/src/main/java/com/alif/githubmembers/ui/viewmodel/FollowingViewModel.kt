package com.alif.githubmembers.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alif.githubmembers.data.GithubUser
import com.alif.githubmembers.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    private val listFollowing = MutableLiveData<ArrayList<GithubUser>>()

    fun setListFollowing(username: String) {
        ApiConfig.ApiConfig.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<GithubUser>> {
                override fun onResponse(
                    call: Call<ArrayList<GithubUser>>,
                    response: Response<ArrayList<GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getListFollowing(): LiveData<ArrayList<GithubUser>> {
        return listFollowing
    }
}