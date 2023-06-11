package com.example.findissues.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.findissues.R
import com.example.findissues.models.Issues
import com.example.findissues.utils.Constants
import com.example.findissues.api.ServiceHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val issues = ServiceHandler.apiService.getIssue("kotlin",Constants.CREATED)
        issues.enqueue(object : Callback<Issues> {
            override fun onResponse(call: Call<Issues>, response: Response<Issues>) {
                val news = response.body()
                if(news!=null){
                    Log.d("RESPONSE",news.toString())
                }
            }

            override fun onFailure(call: Call<Issues>, t: Throwable) {
                Log.d("ERROR", "Error in fetching news")
            }

        })
    }
}