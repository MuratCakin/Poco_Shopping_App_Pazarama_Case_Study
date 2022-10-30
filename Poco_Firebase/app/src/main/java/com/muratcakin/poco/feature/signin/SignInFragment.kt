package com.muratcakin.poco.feature.signin

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.muratcakin.poco.R
import com.muratcakin.poco.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel by viewModels<SignInViewModel>()
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController :NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SignInViewEvent.NavigateToMain -> {
                            navController.navigate(
                                R.id.action_signInFragment_to_productsFragment,
                                null,
                                androidx.navigation.NavOptions.Builder().setPopUpTo(0, true).build()
                            )
                            Toast.makeText(requireContext(), "Sign In Success", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is SignInViewEvent.ShowError -> {
                            Toast.makeText(requireContext(), "Sign In Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.signIn(
                binding.etEmailSignIn.text?.trim().toString(),
                binding.etPasswordSignIn.text?.trim().toString()
            )
        }

        binding.btnSignUpPage.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.etEmailSignIn.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
        binding.etPasswordSignIn.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    // Klavyenin Enter a basıldıktan sonra gizlenmesi
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}