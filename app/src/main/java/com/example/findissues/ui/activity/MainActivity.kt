package com.example.findissues.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.findissues.FindIssuesApp.Companion.context
import com.example.findissues.R
import com.example.findissues.databinding.ActivityMainBinding
import com.example.findissues.ui.fragments.HomeFragment
import com.example.findissues.ui.fragments.IssuesFragment
import com.example.findissues.ui.fragments.PullsFragment
import com.example.findissues.ui.fragments.StatusFragment
import com.example.findissues.utils.Network
import com.example.findissues.utils.Toaster
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val homeFragment = HomeFragment()
        val issuesFragment = IssuesFragment()
        val pullsFragment = PullsFragment()
        val statusFragment = StatusFragment()

        setCurrentFragment(homeFragment)

        if (!hasNetwork()) {
            Toaster.show(binding.root,"Connect to internet")
        }

        binding.bottomMenu.setItemSelected(R.id.home)
        binding.bottomMenu.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    setCurrentFragment(homeFragment)
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

    private fun hasNetwork(): Boolean {
        return Network.isConnected(this)
    }

}