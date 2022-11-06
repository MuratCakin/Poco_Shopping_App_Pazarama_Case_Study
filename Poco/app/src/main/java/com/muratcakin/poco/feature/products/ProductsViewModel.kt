package com.muratcakin.poco.feature.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<ProductsViewState>(ProductsViewState.Success(mutableListOf()))
    val uiState: StateFlow<ProductsViewState> = _uiState

    private val _uiEvent = MutableSharedFlow<ProductsViewEvent>(replay = 0)
    val uiEvent: SharedFlow<ProductsViewEvent> = _uiEvent

    private var _productCacheData = MutableLiveData<List<ProductDTO>?>()
    private val productCacheData: LiveData<List<ProductDTO>?>
    get() = _productCacheData

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productsRepository.getProducts().collect {
                when (it) {
                    is DataState.Success -> {
                        _uiState.value = ProductsViewState.Success(it.data.toMutableList())
                    }
                    is DataState.Error -> {
                        _uiEvent.emit(ProductsViewEvent.ShowError(it.error?.status_message))
                    }
                    is DataState.Loading -> {
                        _uiState.value = ProductsViewState.Loading
                    }
                }
            }
        }
    }

    fun getProductById(id: Int): ProductDTO? {
        return productCacheData.value?.find { it.id == id }
    }
}

sealed class ProductsViewEvent {
    data class ShowError(val message: String?) : ProductsViewEvent()
}

sealed class ProductsViewState {
    class Success(val product: List<ProductDTO>?) : ProductsViewState()
    object Loading : ProductsViewState()
}