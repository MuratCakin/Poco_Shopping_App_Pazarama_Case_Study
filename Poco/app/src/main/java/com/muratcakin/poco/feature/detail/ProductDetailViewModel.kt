package com.muratcakin.poco.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailRepository: ProductsRepository,
    private val savedStateHandle: SavedStateHandle,
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<ProductDetailViewState>(
        ProductDetailViewState.Success(
            ProductDTO(0, "", "", 0.0, "", "")
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
                        _uiState.value = ProductDetailViewState.Success(it.data.apply {
                            val data = getBasketList(it.data.id).first()

                            ProductDTO(
                                id = it.data.id,
                                title = it.data.title,
                                image = it.data.image,
                                category = it.data.category,
                                description = it.data.description,
                                price = it.data.price,
                                onBasket = data?.find { c -> c == it.data.id.toString() } != null
                            )
                        })
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

    private fun getBasketList(id: Int?): Flow<MutableList<String>?> = channelFlow {
        val basketList = mutableListOf<String>()
        val callBack =
            fireStore.collection("basket").document(firebaseAuth.currentUser?.uid.toString())
                .collection("products").document(id.toString()).get().addOnSuccessListener {
                    it.data?.values?.forEach { data ->
                        basketList.add(data.toString())
                    }
                    trySendBlocking(basketList)

                }.addOnFailureListener {
                    trySendBlocking(mutableListOf())
                }
        awaitClose { callBack.isCanceled() }
    }

    fun onBasketProduct(data: ProductDTO) {
        viewModelScope.launch {
            val userId = firebaseAuth.currentUser?.uid
            if (!data.onBasket) {
                insertProduct(userId.toString(), data)
            }
        }
    }

    private fun insertProduct(userId: String, data: ProductDTO) {
        fireStore.collection("basket").document(userId).collection("products")
            .let { ref ->
                ref.document("${data.id}")
                    .set(
                        mapOf(
                            "productId" to "${data.id}",
                            "price" to data.price,
                            "title" to data.title,
                            "category" to data.category,
                            "description" to data.description,
                            "image" to data.image
                        )
                    )

                    .addOnSuccessListener {
                        viewModelScope.launch {
                            _uiState.value =
                                ProductDetailViewState.Success((_uiState.value as ProductDetailViewState.Success).product?.apply {
                                    if (id == data.id) {
                                        onBasket = true
                                    }
                                })

                            _uiEvent.emit(ProductDetailViewEvent.ShowError("Product added to basket"))
                        }
                    }
                    .addOnFailureListener { error ->
                        viewModelScope.launch {
                            _uiEvent.emit(ProductDetailViewEvent.ShowError(error.message.toString()))
                        }
                    }
            }
    }

    private fun deleteProduct(userId: String, id: Int?) {
        fireStore.collection("basket").document(userId).collection("products")
            .let { ref ->
                ref.document("$id")
                    .delete()
                    .addOnSuccessListener {
                        viewModelScope.launch {
                            _uiState.value =
                                ProductDetailViewState.Success((_uiState.value as ProductDetailViewState.Success).product?.apply {
                                    if (this.id == id) {
                                        onBasket = false
                                    }
                                })
                            _uiEvent.emit(ProductDetailViewEvent.ShowError("Product deleted from basket"))
                        }
                    }
                    .addOnFailureListener { error ->
                        viewModelScope.launch {
                            _uiEvent.emit(ProductDetailViewEvent.ShowError(error.message.toString()))
                        }
                    }
            }
    }
}

sealed class ProductDetailViewEvent {
    data class ShowError(val message: String?) : ProductDetailViewEvent()
}

sealed class ProductDetailViewState {
    class Success(val product: ProductDTO?) : ProductDetailViewState()
    object Loading : ProductDetailViewState()
}