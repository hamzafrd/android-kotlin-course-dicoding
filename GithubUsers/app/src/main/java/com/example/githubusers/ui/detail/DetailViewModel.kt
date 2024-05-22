package com.example.githubusers.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.UserRepository
import com.example.githubusers.data.api.ApiConfig
import com.example.githubusers.data.api.model.DetailGithubUser
import com.example.githubusers.data.api.model.GithubUser
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.helper.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {
    private val _detailUsers = MutableLiveData<DetailGithubUser>()
    val detailUsers: LiveData<DetailGithubUser> = _detailUsers

    private val _following = MutableLiveData<List<GithubUser>>()
    val following: LiveData<List<GithubUser>> = _following

    private val _followers = MutableLiveData<List<GithubUser>>()
    val followers: LiveData<List<GithubUser>> = _followers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingTab = MutableLiveData<Boolean>()
    val isLoadingTab: LiveData<Boolean> = _isLoadingTab

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    private var _isFavorite = MutableLiveData<Boolean?>()
    val isFavorite: LiveData<Boolean?> = _isFavorite

    private val mUserRepository = UserRepository(application)

    fun getDetailUsers(username: String) {
        _isLoading.value = true
        val clients = ApiConfig.getApiService().getUserDetail(username)
        clients.enqueue(object : Callback<DetailGithubUser> {
            override fun onResponse(
                call: Call<DetailGithubUser>,
                response: Response<DetailGithubUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUsers.postValue(response.body())
                } else {
                    Log.e(TAG, "isNOTSuccessful ${response.body()}")
                }
            }

            override fun onFailure(call: Call<DetailGithubUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "FAILURE RESPONSE : ${t.message}, $call")
            }
        })
    }

    fun getUsersFollowers(username: String) {
        _isLoadingTab.value = true
        val clients = ApiConfig.getApiService().getUserFollowers(username)
        clients.enqueue(object : Callback<List<GithubUser>> {
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                _isLoadingTab.value = false
                if (response.isSuccessful) {
                    _followers.postValue(response.body())
                } else {
                    Log.e(TAG, "isNOTSuccessful ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                _isLoadingTab.value = false
                Log.e(TAG, "FAILURE RESPONSE : ${t.message}, $call")
            }
        })
    }

    fun getUsersFollowing(username: String) {
        _isLoadingTab.value = true
        val clients = ApiConfig.getApiService().getUserFollowing(username)
        clients.enqueue(object : Callback<List<GithubUser>> {
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                _isLoadingTab.value = false
                if (response.isSuccessful) {
                    _following.postValue(response.body())
                } else {
                    Log.e(TAG, "isNOTSuccessful ${response.body()}")
                    _toastText.value = Event("NOTSuccessfully")
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                _isLoadingTab.value = false
                _toastText.value = Event("FAILURE RESPONSE")
                Log.e(TAG, "FAILURE RESPONSE : ${t.message}, $call")
            }
        })
    }

    fun setFavorite(isFavorite: Boolean) {
        _isFavorite.value = isFavorite
    }

    fun addToFavorite(user: UserEntity) {
        mUserRepository.insert(user)
    }

    fun removeFromFavorite(user: UserEntity) {
        mUserRepository.delete(user)
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}