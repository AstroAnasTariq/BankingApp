package com.astroanastariq.bankingapp.ui.fragments.transactionRecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astroanastariq.bankingapp.database.TransactionRecordDao
import java.lang.IllegalArgumentException

class TransactionRecordViewModelFactory(private val transactionRecordDatasource: TransactionRecordDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionRecordViewModel::class.java)) {
            return TransactionRecordViewModel(
                transactionRecordDatasource
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}