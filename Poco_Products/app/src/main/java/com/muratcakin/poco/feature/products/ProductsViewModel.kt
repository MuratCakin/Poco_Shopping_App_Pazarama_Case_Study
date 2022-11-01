package com.muratcakin.poco.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<ProductsViewState>(ProductsViewState.Success(mutableListOf()))
    val uiState: StateFlow<ProductsViewState> = _uiState

    private val _uiEvent = MutableSharedFlow<ProductsViewEvent>(replay = 0)
    val uiEvent: SharedFlow<ProductsViewEvent> = _uiEvent

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productsRepository.getProducts().collect {
                when(it) {
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
}

sealed class ProductsViewEvent {
    data class ShowError(val message: String?) : ProductsViewEvent()
}

sealed class ProductsViewState {
    class Success(val product: List<Product>?) : ProductsViewState()
    object Loading : ProductsViewState()
}