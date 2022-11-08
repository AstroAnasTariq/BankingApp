package com.astroanastariq.bankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.databinding.FragmentCustomersBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CustomersFragment : Fragment() {

    private var _binding: FragmentCustomersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.customer_view_card.setOnClickListener {
//            findNavController().navigate(R.id.action_CustomersFragment_to_customerDetailsFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}