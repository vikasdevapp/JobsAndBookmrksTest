package com.example.jobsandbookmarkstest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jobsandbookmarkstest.Screens.HomePage.HomeScreen
import com.example.jobsandbookmarkstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent= Intent(this,HomeScreen::class.java)
        startActivity(intent)
        finish()
    }
}