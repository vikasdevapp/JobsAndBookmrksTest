package com.example.jobsandbookmarkstest.Screens.HomePage

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jobsandbookmarkstest.R
import com.example.jobsandbookmarkstest.Screens.BottomNavigation.BookmarksFragment
import com.example.jobsandbookmarkstest.Screens.BottomNavigation.JobsFragment
import com.example.jobsandbookmarkstest.databinding.ActivityHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity() {

    private lateinit var binding:ActivityHomeScreenBinding
    private lateinit var appBarText: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBarText = binding.appBarText

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_nav_job -> {
                    replaceFragment(JobsFragment(), "Jobs")
                    true
                }
                R.id.bottom_nav_bookmarks -> {
                    replaceFragment(BookmarksFragment(), "Bookmarks")
                    true
                }
                else -> false
            }
        }

        replaceFragment(JobsFragment(), "Jobs")

        val selectedItem = intent.getIntExtra("selected_nav_index", -1)
        println("selectedItem -> $selectedItem")
        if (selectedItem != -1) {
            bottomNavigationView.selectedItemId = R.id.bottom_nav_job

            when (selectedItem) {
                R.id.bottom_nav_job -> {
                    replaceFragment(JobsFragment(), "Jobs")
                }
                R.id.bottom_nav_bookmarks -> {
                    replaceFragment(BookmarksFragment(), "Bookmarks")
                }
            }
        } else {
            replaceFragment(JobsFragment(), "Jobs")
        }
    }


    fun replaceFragment(fragment: Fragment, appText: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()
        replaceTitle(appText)
    }
    private fun replaceTitle(title: String) {
        supportActionBar?.title = title
        appBarText.text = title
    }
}