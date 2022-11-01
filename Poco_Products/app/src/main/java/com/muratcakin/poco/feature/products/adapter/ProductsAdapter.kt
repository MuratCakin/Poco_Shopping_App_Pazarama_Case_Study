package com.muratcakin.poco.feature.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.databinding.ItemProductBinding

class ProductsAdapter(private val listener: OnProductClickListener) :
    ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(PopularMovieDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ProductsViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Product, listener: OnProductClickListener) {
            binding.dataHolder = data
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    class PopularMovieDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnProductClickListener {
    fun onProductClick(id: Int?)
}