package com.astroanastariq.bankingapp.ui.fragments.transactionRecord

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroanastariq.bankingapp.database.TransactionRecord
import com.astroanastariq.bankingapp.database.TransactionRecordDao
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class TransactionRecordViewModel(private val transactionRecordDatasource: TransactionRecordDao) :
    ViewModel() {
    private val _transactionRecordList = MutableLiveData<List<TransactionRecord>>()
    val transactionRecordList: LiveData<List<TransactionRecord>>
        get() = _transactionRecordList

    init {
        getAllTransaction()
    }

    private fun getAllTransaction() {
        viewModelScope.launch {
            _transactionRecordList.value = transactionRecordDatasource.getAllTransaction()
        }
    }

    private fun deleteTransactionRecord(transactionRecord: TransactionRecord) {
        viewModelScope.launch {
            transactionRecordDatasource.deleteTransactionRecord(transactionRecord)
            _transactionRecordList.value = transactionRecordDatasource.getAllTransaction()
        }
    }

    private fun deleteAllTransaction() {
        viewModelScope.launch {
            transactionRecordDatasource.deleteAllTransaction()
            _transactionRecordList.value = transactionRecordDatasource.getAllTransaction()
        }
    }


    fun openDeleteTransactionRecordDialogBox(
        context: Context,
        transactionRecord: TransactionRecord
    ) {
        val builder = MaterialAlertDialogBuilder(context).apply {
            setPositiveButton("Yes") { _, _ ->
                deleteTransactionRecord(transactionRecord)
                Toast.makeText(context, "Transaction deleted successfully.", Toast.LENGTH_SHORT)
                    .show()
            }

            setNegativeButton("No") { _, _ ->

            }
            setTitle("Delete Transaction?")
            setMessage("Are you sure you want to delete this transaction ?")
        }
        builder.create().show()
    }

    fun deleteAllTransaction(context: Context) {
        val builder = MaterialAlertDialogBuilder(context).apply {
            setPositiveButton("Yes") { _, _ ->
                deleteAllTransaction()
                Toast.makeText(context, "All Transaction deleted successfully.", Toast.LENGTH_SHORT)
                    .show()
            }

            setNegativeButton("No") { _, _ ->

            }
            setTitle("Delete All Transactions?")
            setMessage("Are you sure you want to delete all transactions ?")
        }
        builder.create().show()
    }
}