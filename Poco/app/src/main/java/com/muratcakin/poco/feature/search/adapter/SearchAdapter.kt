package com.muratcakin.poco.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.ItemSearchProductBinding

class SearchAdapter(private val listener: OnSearchProductClickListener) :
    ListAdapter<ProductDTO, SearchAdapter.SearchViewHolder>(SearchDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class SearchViewHolder(private val binding: ItemSearchProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductDTO, listener: OnSearchProductClickListener) {
            binding.dataHolder = data
            binding.cvSearchProduct.setOnClickListener {
                listener.onSearchProductDetailClick(data, it)
            }
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    class SearchDiffUtil : DiffUtil.ItemCallback<ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnSearchProductClickListener {
    fun onSearchProductDetailClick(product: ProductDTO, view: View)
}