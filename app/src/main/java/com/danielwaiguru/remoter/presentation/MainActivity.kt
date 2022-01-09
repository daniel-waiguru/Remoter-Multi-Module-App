package com.danielwaiguru.remoter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danielwaiguru.remoter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}