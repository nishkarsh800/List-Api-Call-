package com.example.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment.model.Product
import com.example.assignment.model.ProductList
import com.example.assignment.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {
    private val productLiveData = MutableLiveData<List<Product>>()
    private val productDetailsLiveData = MutableLiveData<Product?>()

    fun getProductList(): LiveData<List<Product>> {
        RetrofitInstance.api.getProductList().enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                if (response.body() != null) {
                    productLiveData.value = response.body()!!.products
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                // Handle failure if needed
            }
        })
        return productLiveData
    }

    fun getProductDetail(id: Int): LiveData<Product?> {
        RetrofitInstance.api.getProductDetail(id).enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                if (response.isSuccessful) {
                    val productList = response.body()?.products
                    if (!productList.isNullOrEmpty()) {
                        // Find the product with the matching id
                        val product = productList.find { it.id == id }
                        if (product != null) {
                            productDetailsLiveData.value = product
                        } else {
                            // Handle the case when the product with the given id is not found
                        }
                    } else {
                        // Handle the case when the product list is null or empty
                    }
                } else {
                    // Handle the case when the response is not successful
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                // Handle failure if needed
            }
        })
        return productDetailsLiveData
    }
}