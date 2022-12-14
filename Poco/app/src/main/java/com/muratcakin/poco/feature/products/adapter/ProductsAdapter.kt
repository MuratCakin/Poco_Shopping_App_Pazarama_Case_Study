package com.muratcakin.poco.feature.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.ItemProductBinding

class ProductsAdapter(private val listener: OnProductClickListener) :
    ListAdapter<ProductDTO, ProductsAdapter.ProductsViewHolder>(ProductsDiffUtil()) {
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
        fun bind(data: ProductDTO, listener: OnProductClickListener) {
            binding.dataHolder = data
            binding.cvProduct.setOnClickListener {
                listener.onProductDetailClick(data, it)
            }
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    class ProductsDiffUtil : DiffUtil.ItemCallback<ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnProductClickListener {
    fun onProductDetailClick(product: ProductDTO, view: View)
}