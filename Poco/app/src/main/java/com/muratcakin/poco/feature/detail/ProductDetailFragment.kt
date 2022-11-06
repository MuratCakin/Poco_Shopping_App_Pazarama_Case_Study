package com.muratcakin.poco.feature.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.FragmentProductDetailBinding
import com.muratcakin.poco.feature.detail.adapter.OnDetailProductClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment(), OnDetailProductClickListener {
    private val viewModel by viewModels<ProductDetailViewModel>()
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is ProductDetailViewState.Success -> {
                            binding.dataHolder = it.product

                            binding.btnAddToBasket.setOnClickListener { _ ->
                                it.product?.let { it1 -> viewModel.onBasketProduct(it1) }
                            }
                        }
                        is ProductDetailViewState.Loading -> {
                        }
                    }
                }
            }

            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is ProductDetailViewEvent.ShowError -> {
                            Snackbar.make(
                                binding.root,
                                it.message.toString(),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onBasketClick(data: ProductDTO) {
        viewModel.onBasketProduct(data)
    }
}