package com.astroanastariq.bankingapp.ui.fragments.transactionRecord

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.astroanastariq.bankingapp.R
import com.astroanastariq.bankingapp.database.CustomerDatabase
import com.astroanastariq.bankingapp.databinding.FragmentTransactionRecordBinding


class TransactionRecordFragment : Fragment() {

    private lateinit var binding: FragmentTransactionRecordBinding
    private lateinit var viewModel: TransactionRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTransactionRecordBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource = CustomerDatabase.getInstance(application).transactionRecordDao
        val viewModelFactory = TransactionRecordViewModelFactory(dataSource)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[TransactionRecordViewModel::class.java]

        val adapter = TransactionRecordAdapter(TransactionRecordClickListener { transactionRecord ->
            viewModel.openDeleteTransactionRecordDialogBox(requireContext(), transactionRecord)
        })

        binding.transactionRecordRecyclerView.adapter = adapter

        viewModel.transactionRecordList.observe(
            viewLifecycleOwner
        ) { transactionRecordList ->
            adapter.submitList(transactionRecordList)
        }

        binding.viewModel = viewModel
//        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.delete_all -> {
                        viewModel.deleteAllTransaction(requireContext())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}