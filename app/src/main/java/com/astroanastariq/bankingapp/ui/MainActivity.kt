package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.navHostFragmentContentMain
            .getFragment<NavHostFragment>().navController
            .navigateUp() || super.onSupportNavigateUp()
    }
}