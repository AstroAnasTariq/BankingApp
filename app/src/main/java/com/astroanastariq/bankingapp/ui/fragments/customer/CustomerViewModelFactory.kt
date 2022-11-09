package com.astroanastariq.bankingapp.ui.fragments.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astroanastariq.bankingapp.database.CustomerDao
import java.lang.IllegalArgumentException

class CustomerViewModelFactory(private val datasource: CustomerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}