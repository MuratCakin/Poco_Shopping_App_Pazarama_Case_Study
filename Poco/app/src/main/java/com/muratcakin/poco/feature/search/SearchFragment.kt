package com.muratcakin.poco.feature.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.poco.R
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.FragmentSearchBinding
import com.muratcakin.poco.feature.loadingprogress.LoadingProgressBar
import com.muratcakin.poco.feature.search.adapter.OnSearchProductClickListener
import com.muratcakin.poco.feature.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), OnSearchProductClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())
        navController = findNavController()

        searchProduct()

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is SearchViewState.Success -> {
                            loadingProgressBar.hide()
                            if (it.filteredProduct.isEmpty().not()) {
                                initAdapter(it.filteredProduct)
                            } else {
                                it.product?.let { it1 -> initAdapter(it1) }
                            }
                        }
                        is SearchViewState.Loading -> {
                            loadingProgressBar.show()
                        }
                    }
                }
            }

            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SearchViewEvent.ShowError -> {
                            loadingProgressBar.hide()
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

    // Navigate to Detail Page
    override fun onSearchProductDetailClick(product: ProductDTO, view: View) {
        product.id?.let { viewModel.getProductById(it) }
        navController.navigate(R.id.action_searchFragment_to_productDetailFragment, Bundle().apply {
            product.id?.let { product.id?.let { it1 -> putInt("productId", it1) } }
        })
    }

    private fun initAdapter(product: List<ProductDTO>) {
        binding.rvSearchList.adapter =
            SearchAdapter(this@SearchFragment).apply {
                submitList(product)
            }
    }

    // Search product by name
    private fun searchProduct() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchProduct(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.length > 2) {
                        viewModel.searchProduct(newText)
                    } else {
                        viewModel.searchProduct("")
                    }
                }
                return false
            }
        })
    }
}