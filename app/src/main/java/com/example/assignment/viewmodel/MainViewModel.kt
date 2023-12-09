package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.model.Product
import com.example.assignment.repository.ProductRepository

class MainViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    fun getProductList(): LiveData<List<Product>> {
        return productRepository.getProductList()
    }

    fun getProductDetail(id: Int): LiveData<Product?> {
        return productRepository.getProductDetail(id)
    }
}
