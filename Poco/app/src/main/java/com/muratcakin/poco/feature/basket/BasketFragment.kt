package com.muratcakin.poco.feature.basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.databinding.FragmentBasketBinding
import com.muratcakin.poco.feature.detail.adapter.OnDetailProductClickListener
import com.muratcakin.poco.feature.detail.adapter.ProductDetailAdapter
import com.muratcakin.poco.feature.loadingprogress.LoadingProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment(), OnDetailProductClickListener {

    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBasketList()

        loadingProgressBar = LoadingProgressBar(requireContext())

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is BasketViewState.Success -> {
                            loadingProgressBar.hide()
                            initAdapter(it.data)
                        }
                        is BasketViewState.Loading -> {
                            loadingProgressBar.show()
                        }
                        is BasketViewState.Error -> {
                            loadingProgressBar.hide()
                        }
                    }
                }
            }
        }
    }

    private fun initAdapter(data: MutableList<ProductDTO>) {
        binding.rvBasket.adapter =
            ProductDetailAdapter(this@BasketFragment).apply {
                submitList(data)
            }
    }

    override fun onBasketClick(productDTO: ProductDTO) {
        TODO("Not yet implemented")
    }
}