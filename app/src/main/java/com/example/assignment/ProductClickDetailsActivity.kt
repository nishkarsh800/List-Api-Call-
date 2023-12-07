package com.example.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.assignment.databinding.ActivityProductClickDetailsBinding
import com.example.assignment.viewmodel.ProductInfoViewModel

class ProductClickDetailsActivity : AppCompatActivity() {
    private var productId: Int = 0 // Initialize with a default value
    private lateinit var productName: String
    private lateinit var productThumb: String
    private lateinit var binding: ActivityProductClickDetailsBinding
    private lateinit var productMvvm : ProductInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductClickDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productMvvm = ViewModelProviders.of(this)[ProductInfoViewModel::class.java]

        getProductInfoFromIntent()

        setInformationInViews()

        productMvvm.getProductDetail(productId)
        observableProductDetailLiveData()
    }

    private fun observableProductDetailLiveData() {
        productMvvm.observableProductDetailLiveData().observe(this
        ) { value ->
            val product = value

            binding.productTitle.text = "Title: ${product!!.title}"
            binding.productPrice.text = "Price: ${product.price.toString()}"
            binding.productDiscount.text = "Discount: ${product.discountPercentage.toString()}"
            binding.productDescriptionDetailed.text = product.description
            binding.productBrand.text = "Brand: ${product!!.brand}"
            binding.productCategory.text = "Category: ${product!!.category}"
            binding.productRating.text = "Rating: ${product!!.rating}"
            binding.productStock.text = "Stock: ${product.stock}"
        }
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(productThumb)
            .into(binding.imgProductImage)

        binding.collapsingToolbar.title = productName
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))

    }

    private fun getProductInfoFromIntent() {
        val intent = intent
        productId = intent.getIntExtra(HomeFragment.PRODUCT_ID, 0) // Provide a default value for getIntExtra
        productName = intent.getStringExtra(HomeFragment.PRODUCT_NAME)!!
        productThumb = intent.getStringExtra(HomeFragment.PRODUCT_THUMB)!!
    }
}
