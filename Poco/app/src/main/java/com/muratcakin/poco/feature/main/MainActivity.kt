package com.muratcakin.poco.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.muratcakin.poco.R
import com.muratcakin.poco.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    companion object {
        const val KEY_NAVIGATE_PRODUCTS = "KEY_NAVIGATE_PRODUCTS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is MainUiState.Success -> {
                            initNavController(it.isNavigateProducts)
                        }
                    }
                }
            }
        }
        supportActionBar?.hide()
    }


    // Initialize navigation
    private fun initNavController(isNavigateToProducts: Boolean) {
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        // Redirect to the login page if not logged in
        if (isNavigateToProducts.not()) {
            navController.navigate(R.id.sign_in_graph)
        }

        // Navigate when click Basket Icon
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.basket -> {
                    navController.navigate(R.id.action_mainActivity_to_basketFragment)
                    supportFragmentManager.removeOnBackStackChangedListener { }
                    true
                }
                else -> false
            }
        }
    }
}