package com.astroanastariq.bankingapp.ui.fragments.transactionRecord

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
            viewLifecycleOwner,
            Observer { transactionRecordList ->
                adapter.submitList(transactionRecordList)
            })

        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            viewModel.deleteAllTransaction(requireContext())
        }
        return false
    }
}