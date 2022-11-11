package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dark_mode -> {
                Toast.makeText(applicationContext, "Dark/Light mode enabled", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}