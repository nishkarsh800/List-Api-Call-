package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.databinding.ProductItemBinding
import com.example.assignment.model.Product

class ProductsListAdapter() : RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder>() {

    lateinit var onItemClick:((Product)-> Unit)
    private var product = ArrayList<Product>()

    fun setProduct(products: List<Product>) {
        this.product = products as ArrayList<Product>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        return ProductsListViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(product[position].thumbnail)
            .into(holder.binding.image)

        holder.binding.txtRecView.text = product[position].title
        holder.itemView.setOnClickListener {
            onItemClick.invoke(product[position])
        }

    }

    class ProductsListViewHolder(var binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}