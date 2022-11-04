package com.muratcakin.poco.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailRepository: ProductsRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<ProductDetailViewState>(
        ProductDetailViewState.Success(
            Product("", "", 0, "", 0.0, "")
        )
    )
    val uiState: StateFlow<ProductDetailViewState> = _uiState

    private val _uiEvent = MutableSharedFlow<ProductDetailViewEvent>(replay = 0)
    val uiEvent: SharedFlow<ProductDetailViewEvent> = _uiEvent

    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            getProductDetail(productId)
        }
    }

    private fun getProductDetail(productId: Int) {
        viewModelScope.launch {
            productDetailRepository.getProductDetail(productId).collect {
                when (it) {
                    is DataState.Success -> {
                        _uiState.value = ProductDetailViewState.Success(it.data)
                    }
                    is DataState.Error -> {
                        _uiEvent.emit(ProductDetailViewEvent.ShowError(it.error?.status_message))
                    }
                    is DataState.Loading -> {
                        _uiState.value = ProductDetailViewState.Loading
                    }
                }
            }
        }
    }
}

sealed class ProductDetailViewEvent {
    data class ShowError(val message: String?) : ProductDetailViewEvent()
}

sealed class ProductDetailViewState {
    class Success(val product: Product) : ProductDetailViewState()
    object Loading : ProductDetailViewState()
}