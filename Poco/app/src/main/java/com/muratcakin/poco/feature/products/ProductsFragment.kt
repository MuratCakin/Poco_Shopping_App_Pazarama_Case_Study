package com.muratcakin.poco.feature.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.poco.R
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.FragmentProductsBinding
import com.muratcakin.poco.feature.products.adapter.OnProductClickListener
import com.muratcakin.poco.feature.products.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment(), OnProductClickListener {
    private val viewModel by viewModels<ProductsViewModel>()
    private lateinit var binding: FragmentProductsBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is ProductsViewState.Success -> {
                            binding.rvProductList.adapter =
                                ProductsAdapter(this@ProductsFragment).apply {
                                    submitList(it.product)
                                }
                            binding.rvProductList.layoutManager = GridLayoutManager(context, 3)
                        }
                        is ProductsViewState.Loading -> {
                        }
                    }
                }
            }

            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is ProductsViewEvent.ShowError -> {
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

    override fun onProductDetailClick(product: ProductDTO, view: View) {
        product.id?.let { viewModel.getProductById(it) }
        navController.navigate(
            R.id.action_productsFragment_to_productDetailFragment,
            Bundle().apply {
                product.id?.let { product.id?.let { it1 -> putInt("productId", it1) } }
            })
    }
}