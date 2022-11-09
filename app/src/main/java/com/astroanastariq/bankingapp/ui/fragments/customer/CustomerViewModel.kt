package com.astroanastariq.bankingapp.ui.fragments.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroanastariq.bankingapp.database.Customer
import com.astroanastariq.bankingapp.database.CustomerDao
import kotlinx.coroutines.launch

class CustomerViewModel(private val datasource: CustomerDao):ViewModel() {

    lateinit var customerList: LiveData<List<Customer>>

    init {
        getCustomerList()
    }

    private fun getCustomerList() {
        viewModelScope.launch {
            customerList=datasource.getAllCustomer()
        }
    }

}