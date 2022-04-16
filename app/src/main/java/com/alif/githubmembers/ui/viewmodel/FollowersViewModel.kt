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

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<GithubUser>>()

    fun setListFollowers(username: String) {
        ApiConfig.ApiConfig.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<GithubUser>> {
                override fun onResponse(
                    call: Call<ArrayList<GithubUser>>,
                    response: Response<ArrayList<GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<GithubUser>> {
        return listFollowers
    }
}