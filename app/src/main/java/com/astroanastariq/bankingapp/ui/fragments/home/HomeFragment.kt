package com.astroanastariq.bankingapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cutomers.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_customerFragment)
        }

        binding.transactions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionRecordFragment)
        }
    }
}