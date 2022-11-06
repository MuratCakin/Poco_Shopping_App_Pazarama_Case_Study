package com.muratcakin.poco.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.muratcakin.poco.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Empty)
    val uiState: StateFlow<SignInUiState> = _uiState

    private val _uiEvent = MutableSharedFlow<SignInViewEvent>(replay = 0)
    val uiEvent: SharedFlow<SignInViewEvent> = _uiEvent

    // Sign In with Firebase Auth
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            if (isValidFields(email, password)) {
                firebaseAuth.signInWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        viewModelScope.launch {
                            _uiEvent.emit(SignInViewEvent.NavigateToMain)
                        }

                    } else {
                        viewModelScope.launch {
                            _uiEvent.emit(SignInViewEvent.ShowError(task.exception?.message.toString()))
                        }
                    }
                }
            } else {
                _uiEvent.emit(SignInViewEvent.ShowError("Please fill all fields"))
            }
        }
    }

    private fun isValidFields(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

}

sealed class SignInViewEvent {
    object NavigateToMain : SignInViewEvent()
    class ShowError(val error: String) : SignInViewEvent()
}

sealed class SignInUiState {
    object Empty : SignInUiState()
    object Loading : SignInUiState()
    object Success : SignInUiState()
    class Error(val error: String) : SignInUiState()
}