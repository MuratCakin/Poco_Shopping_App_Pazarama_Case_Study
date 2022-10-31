package com.muratcakin.poco.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcakin.poco.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {

    fun setOnBoardingStatus() {
        viewModelScope.launch {
            dataStoreManager.setOnBoardingVisible(true)
        }
    }
}