package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.ProductsListAdapter
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.model.Product
import com.example.assignment.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm : MainViewModel
    private lateinit var productListAdapter:ProductsListAdapter

    companion object{
        const val PRODUCT_ID = "com.example.assignment.idProduct"
        const val PRODUCT_NAME = "com.example.assignment.nameProduct"
        const val PRODUCT_THUMB = "com.example.assignment.thumbProduct"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[MainViewModel::class.java]

        productListAdapter = ProductsListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareProductListItemView()

        homeMvvm.getProductList()
        observeProductListLiveData()
        onProductItemClick()
    }

    private fun onProductItemClick() {
       productListAdapter.onItemClick = {
           product ->
           val intent = Intent(activity,ProductClickDetailsActivity::class.java)
           intent.putExtra(PRODUCT_ID,product.id)
           intent.putExtra(PRODUCT_NAME,product.title)
           intent.putExtra(PRODUCT_THUMB,product.thumbnail)
           startActivity(intent)
       }
    }

    private fun prepareProductListItemView() {
        binding.recView.apply {
           layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            adapter = productListAdapter
        }
    }

    private fun observeProductListLiveData() {
        homeMvvm.observeProductList().observe(viewLifecycleOwner) { product ->
            productListAdapter.setProduct(products = product as ArrayList<Product>)
        }
    }
}