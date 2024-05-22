package com.example.githubusers.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.githubusers.data.api.ApiConfig
import com.example.githubusers.data.api.model.GithubResponse
import com.example.githubusers.data.api.model.GithubUser
import com.example.githubusers.helper.SettingPreference
import com.example.githubusers.helper.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val pref: SettingPreference) : ViewModel() {
    private val _searchUsers = MutableLiveData<List<GithubUser>>()
    val searchUsers: LiveData<List<GithubUser>> = _searchUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSearched = MutableLiveData<Boolean>()
    val isSearched: LiveData<Boolean> = _isSearched

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    init {
        getUsers("hamza")
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun getUsers(query: String) {
        _isLoading.value = true
        _isSearched.value = true
        val clients = ApiConfig.getApiService().getSearchUser(query, 30)
        clients.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body()?.totalCount != 0) {
                    _isSearched.postValue(true)
                    _searchUsers.value = (response.body()?.items)
                } else {
                    _isSearched.value = false
                    _searchUsers.value = (response.body()?.items)
                    _toastText.value = Event("NOTSuccessfully")
                    Log.e(TAG, "FAILURE ${response.raw()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                _isSearched.value = false
                _toastText.value = Event("FAILURE RESPONSE")
                Log.e(TAG, "FAILURE RESPONSE : ${t.message}, $call")
            }
        })
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}