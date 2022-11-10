package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.astroanastariq.bankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

//        binding.navHostFragmentContentMain
//            .getFragment<NavHostFragment>().navController
        setSupportActionBar(binding.topAppBar)
        //I've used .getFragment instead of findNavController
        val navController = binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController

        val config = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, config)

        setContentView(binding.root)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return binding.navHostFragmentContentMain
//            .getFragment<NavHostFragment>().navController
//            .navigateUp() || super.onSupportNavigateUp()
//    }
}