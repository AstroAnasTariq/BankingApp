package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setSupportActionBar(binding.topAppBar)
        //I've used .getFragment instead of findNavController to prevent an error
        val navController = binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController

        val config = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, config)

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment_content_main)
            .navigateUp()
}