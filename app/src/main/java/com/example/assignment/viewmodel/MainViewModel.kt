package com.example.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.model.Product
import com.example.assignment.model.ProductList
import com.example.assignment.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private var productLiveData = MutableLiveData<List<Product>>()

    fun getProductList(){

        RetrofitInstance.api.getProductList().enqueue(object :Callback<ProductList>{
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
               if (response.body() != null){
                 productLiveData.value = response.body()!!.products
               }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.d("MainViewModel",t.message.toString())
            }

        })
    }

    fun observeProductList():LiveData<List<Product>>{
        return productLiveData
    }
}