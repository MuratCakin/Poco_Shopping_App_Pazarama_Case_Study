package com.muratcakin.poco.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class SharedViewModel: ViewModel() {

    // Data from Sign I to Profile
    private var _email = MutableLiveData("")
    val email: LiveData<String> = _email
    private var _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun saveUserInfo(newEmail: String, newPassword: String) {
        _email.value = newEmail
        _password.value = newPassword
    }
}