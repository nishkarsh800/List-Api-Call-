package com.example.assignment.retrofit

import com.example.assignment.model.ProductList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    fun getProductList(): Call<ProductList>

    @GET("products")
    fun getProductDetail(@Query ("productId")id: Int): Call<ProductList>
}