package com.muratcakin.poco.feature.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.poco.databinding.FragmentProductsBinding
import com.muratcakin.poco.feature.products.adapter.OnProductClickListener
import com.muratcakin.poco.feature.products.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment(), OnProductClickListener {
    private val viewModel by viewModels<ProductsViewModel>()
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when(it) {
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
                    when(it) {
                        is ProductsViewEvent.ShowError -> {
                            Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onProductClick(id: Int?) {
        TODO("Not yet implemented")
    }

}