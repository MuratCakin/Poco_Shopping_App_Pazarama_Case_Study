package com.muratcakin.poco.feature.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcakin.poco.data.model.ProductDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<BasketViewState>(
            BasketViewState.Success(
                mutableListOf()
            )
        )
    val uiState: StateFlow<BasketViewState> = _uiState

    init { getBasketList() }

    // Get products from Firebase
    fun getBasketList() {
        viewModelScope.launch {
            val id = auth.currentUser?.uid
            val docRef = fireStore.collection("basket").document(id.toString()).collection("products").get()
            docRef.addOnSuccessListener { result ->
                if (result.isEmpty.not()) {
                    val list = result.documents.map {
                        ProductDTO(
                            id = it.get("id") as Int?,
                            image = it.get("image") as String?,
                            price = it.get("price") as Double?,
                            title = it.get("title") as String?,
                            category = it.get("category") as String?,
                            description = it.get("description") as String?,
                            onBasket = true
                        )
                    }
                    _uiState.value =
                        BasketViewState.Success(list.toMutableList())
                }
            }
                .addOnFailureListener { }
        }
    }
}

sealed class BasketViewState {
    data class Success(
        val data: MutableList<ProductDTO>
    ) : BasketViewState()

    object Loading : BasketViewState()
    data class Error(val message: String?) : BasketViewState()
}