package com.example.assignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.model.Product
import com.example.assignment.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val productRepository = ProductRepository()

    fun getProductList(): LiveData<List<Product>> {
        return productRepository.getProductList()
    }

    fun getProductDetail(id: Int): LiveData<Product?> {
        return productRepository.getProductDetail(id)
    }
}
