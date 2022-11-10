package com.astroanastariq.bankingapp.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.detailsCustomerNameTextView.text = args.customer.customerName
        binding.detailsAccountNumberTextView.text = args.customer.customerAccountNumber
        binding.detailsMobileNumberTextView.text = args.customer.customerMobileNumber
        binding.detailsAccountBalanceTextView.text = args.customer.accountBalance.toString()
        binding.detailsSwiftTextView.text = args.customer.swiftCode
        binding.detailsEmailTextView.text = args.customer.customerEmail

        binding.transferButton.setOnClickListener {
            openTransferAmountDialogBox()
        }

//        onSupportNavigateUp()

        return binding.root
    }

    private fun openTransferAmountDialogBox() {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogLayout = inflater.inflate(R.layout.transfer_amount, null)

        editText = dialogLayout.findViewById(R.id.et_amount)
        builder.setView(dialogLayout).apply {
            setPositiveButton("Transfer") { _, _ ->
                if (editText.text.isEmpty()) {
                    editText.error = "Enter Amount"
                    Toast.makeText(requireContext(), "Enter Amount", Toast.LENGTH_SHORT).show()
                } else {
                    val amount = Integer.parseInt(editText.text.toString())
                    val customer = args.customer
                    val action =
                        DetailsFragmentDirections.actionDetailsFragmentToTransactionFragment(
                            amount,
                            customer
                        )
                    findNavController().navigate(action)
                }

            }
            setNegativeButton("Cancel") { _, _ -> }
        }
        builder.create()
        builder.show()
    }

//    private fun onSupportNavigateUp() {
//        requireActivity()
//            .onBackPressedDispatcher.addCallback(viewLifecycleOwner)
//            {
//                handleOnBackPressed()
//            }
//    }
}