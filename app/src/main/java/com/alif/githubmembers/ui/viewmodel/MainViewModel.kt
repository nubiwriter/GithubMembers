package com.alif.githubmembers.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alif.githubmembers.data.GithubResponse
import com.alif.githubmembers.data.GithubUser
import com.alif.githubmembers.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val listUsers = MutableLiveData<ArrayList<GithubUser>>()

    fun setSearchUsers(query:String){
        ApiConfig.ApiConfig.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<GithubResponse> {
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getSearchUsers() : LiveData<ArrayList<GithubUser>> {
        return listUsers
    }
}