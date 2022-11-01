package com.muratcakin.poco.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.muratcakin.poco.feature.main.MainActivity
import com.muratcakin.poco.R
import com.muratcakin.poco.feature.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SplashViewEvent.NavigateToOnBoarding -> {
                            navigateToOnBoarding()
                        }
                        is SplashViewEvent.NavigateToMain -> {
                            navigateToMain(it.isNavigateProducts)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMain(isNavigateProducts: Boolean) {
        lifecycleScope.launch {
            delay(1500)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra(MainActivity.KEY_NAVIGATE_PRODUCTS, isNavigateProducts)
            startActivity(intent)
            finish()
        }
    }

    private fun navigateToOnBoarding() {
        lifecycleScope.launch {
            delay(1500)
            val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}