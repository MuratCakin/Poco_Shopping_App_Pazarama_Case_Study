package com.muratcakin.poco

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var _email = MutableLiveData("")
    val email: LiveData<String> = _email
    private var _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun saveUserInfo(newEmail: String, newPassword: String) {
        _email.value = newEmail
        _password.value = newPassword
    }
}