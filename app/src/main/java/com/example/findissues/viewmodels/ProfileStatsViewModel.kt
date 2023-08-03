package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.repository.ProfileStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ProfileStatsViewModel @Inject constructor(private val repository: ProfileStatsRepository) :
    ViewModel() {

    private val _profileLiveData = MutableLiveData<InputStream>()
    val profileLiveData: LiveData<InputStream> get() = _profileLiveData

    fun profileStats() {
        repository.getProfileSummary().enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _profileLiveData.value = responseBody.byteStream()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {

            }

        })
    }

}