package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.astroanastariq.bankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController
            .navigateUp() || super.onSupportNavigateUp()
    }
}