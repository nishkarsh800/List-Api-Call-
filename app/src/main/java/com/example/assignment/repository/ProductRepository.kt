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
        val productDetailsLiveData = MutableLiveData<Product?>()

        RetrofitInstance.api.getProductDetail(id).enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                handleResponse(response) { productList ->
                    val product = productList.find { it.id == id }
                    productDetailsLiveData.value = product
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                // Handle failure if needed
            }
        })

        return productDetailsLiveData
    }

    private inline fun handleResponse(
        response: Response<ProductList>,
        onSuccess: (List<Product>) -> Unit
    ) {
        if (response.isSuccessful) {
            val productList = response.body()?.products.orEmpty()
            onSuccess(productList)
        } else {
            // Handle the case when the response is not successful
        }
    }


}
