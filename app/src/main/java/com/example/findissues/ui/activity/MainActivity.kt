package com.example.findissues.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.R
import com.example.findissues.models.Issues
import com.example.findissues.utils.Constants
import com.example.findissues.api.ServiceHandler
import com.example.findissues.databinding.ActivityMainBinding
import com.example.findissues.ui.adapters.IssuesAdapter
import com.example.findissues.ui.fragments.HomeFragment
import com.example.findissues.ui.fragments.IssuesFragment
import com.example.findissues.ui.fragments.PullsFragment
import com.example.findissues.ui.fragments.StatusFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: IssuesAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val homeFragmemt = HomeFragment()
        val issuesFragment = IssuesFragment()
        val pullsFragment = PullsFragment()
        val statusFragment = StatusFragment()

        setCurrentFragment(homeFragmemt)

        binding.bottomMenu.setItemSelected(R.id.home)
        binding.bottomMenu.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    setCurrentFragment(homeFragmemt)
                }
                R.id.issues -> {
                    setCurrentFragment(issuesFragment)
                }
                R.id.pulls -> {
                    setCurrentFragment(pullsFragment)
                }
                R.id.status -> {
                    setCurrentFragment(statusFragment)
                }
            }

        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_dashboard, fragment)
            commitNow()
        }

}