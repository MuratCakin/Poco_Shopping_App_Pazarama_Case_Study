package com.muratcakin.poco.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.ItemSearchProductBinding

class ProductDetailAdapter(private val listener: OnDetailProductClickListener) :
    ListAdapter<ProductDTO, ProductDetailAdapter.ProductDetailViewHolder>(ProductDetailDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailViewHolder {
        return ProductDetailViewHolder(
            ItemSearchProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductDetailViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ProductDetailViewHolder(private val binding: ItemSearchProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductDTO, listener: OnDetailProductClickListener) {
            binding.dataHolder = data
            binding.executePendingBindings()
        }
    }

    class ProductDetailDiffUtil : DiffUtil.ItemCallback<ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnDetailProductClickListener {
    fun onBasketClick(productDTO: ProductDTO)
}
