package com.example.findissues.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.R
import com.example.findissues.models.Issues
import com.example.findissues.utils.Constants
import com.example.findissues.api.ServiceHandler
import com.example.findissues.databinding.ActivityMainBinding
import com.example.findissues.ui.adapters.IssuesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: IssuesAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNews()
    }

    private fun getNews() {
        val issues = ServiceHandler.apiService.getIssue("kotlin",Constants.CREATED)
        issues.enqueue(object : Callback<Issues> {
            override fun onResponse(call: Call<Issues>, response: Response<Issues>) {
                val news = response.body()
                if(news!=null){
                    Log.d("RESPONSE",news.toString())
                    adapter = IssuesAdapter(this@MainActivity, news.items)
                    binding.issuesList.adapter = adapter
                    binding.issuesList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }
            }

            override fun onFailure(call: Call<Issues>, t: Throwable) {
                Log.d("ERROR", "Error in fetching news")
            }

        })
    }
}