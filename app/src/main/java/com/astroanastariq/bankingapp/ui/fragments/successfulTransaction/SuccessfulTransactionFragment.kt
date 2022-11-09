package com.astroanastariq.bankingapp.ui.fragments.successfulTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.astroanastariq.bankingapp.database.CustomerDatabase
import com.astroanastariq.bankingapp.databinding.FragmentSuccessfulTransactionBinding
import com.astroanastariq.bankingapp.R

class SuccessfulTransactionFragment : Fragment() {

    private lateinit var binding: FragmentSuccessfulTransactionBinding
    private lateinit var viewModel: SuccessfulTransactionViewModel

    private val args by navArgs<SuccessfulTransactionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSuccessfulTransactionBinding.inflate(inflater)

        val transferAmount = args.transferAmount
        val senderCustomer = args.senderCustomer
        val receiverCustomer = args.receiverCustomer

        val application = requireNotNull(this.activity).application
        val databaseInstance = CustomerDatabase.getInstance(application)
        val customerDatasource = databaseInstance.customerDao
        val transactionRecordDatasource = databaseInstance.transactionRecordDao
        val viewModelFactory =
            SuccessfulTransactionViewModelFactory(customerDatasource, transactionRecordDatasource)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[SuccessfulTransactionViewModel::class.java]

        viewModel.initiateTransaction(
            requireContext(),
            senderCustomer,
            receiverCustomer,
            transferAmount
        )

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {

            findNavController().navigate(R.id.action_successfulTransactionFragment_to_homeFragment)
        }

        viewModel.updatedSenderCustomer(senderCustomer)
        viewModel.receiverSenderCustomer(receiverCustomer)
        binding.transferredAmountTextView.text = transferAmount.toString()

        binding.lifecycleOwner = this
        binding.customerViewmodel = viewModel
        return binding.root
    }
}