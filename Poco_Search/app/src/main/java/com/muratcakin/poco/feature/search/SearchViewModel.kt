package com.muratcakin.poco.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import com.muratcakin.poco.feature.products.ProductsViewEvent
import com.muratcakin.poco.feature.products.ProductsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<SearchViewState>(SearchViewState.Success(mutableListOf(), mutableListOf()))
    val uiState: StateFlow<SearchViewState> = _uiState

    private val _uiEvent = MutableSharedFlow<SearchViewEvent>(replay = 0)
    val uiEvent: SharedFlow<SearchViewEvent> = _uiEvent

    private var _productCacheData = MutableLiveData<List<Product>?>()
    private val productCacheData: LiveData<List<Product>?>
        get() = _productCacheData

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productsRepository.getProducts().collect {
                when (it) {
                    is DataState.Success -> {
                        _uiState.value = SearchViewState.Success(it.data.toMutableList(),it.data.toMutableList())
                    }
                    is DataState.Error -> {
                        _uiEvent.emit(SearchViewEvent.ShowError(it.error?.status_message))
                    }
                    is DataState.Loading -> {
                        _uiState.value = SearchViewState.Loading
                    }
                }
            }
        }
    }

    fun getProductById(id: Int): Product? {
        return productCacheData.value?.find { it.id == id }
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            val updateQuery = query.lowercase(Locale.getDefault())
            val currentData = (_uiState.value as SearchViewState.Success).product
            if (updateQuery != "") {
                currentData?.let {
                    val filteredList = it.filter{it.title?.lowercase(Locale.getDefault())?.contains(updateQuery) ?: false}
                    _uiState.value = SearchViewState.Success(currentData,filteredList.toMutableList())
                }
            } else {
                _uiState.value = SearchViewState.Success(currentData, mutableListOf())
            }
        }
    }
}

sealed class SearchViewEvent {
    data class ShowError(val message: String?) : SearchViewEvent()
}

sealed class SearchViewState {
    data class Success(
        val product: List<Product>?,
        val filteredProduct: List<Product>
        ) : SearchViewState()
    object Loading : SearchViewState()
}