package com.muratcakin.poco.feature.signup

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.poco.R
import com.muratcakin.poco.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel by viewModels<SignUpViewModel>()
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        // Navigate to the Products Page when signed up
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SignUpViewEvent.NavigateToMain -> {
                            navController.navigate(
                                resId = R.id.action_signUpFragment_to_productsFragment,
                                null,
                                navOptions = NavOptions.Builder().setPopUpTo(0, true).build()
                            )
                            Toast.makeText(requireContext(), "Sign Up Success", Toast.LENGTH_SHORT)
                                .show()

                        }
                        is SignUpViewEvent.ShowError -> {
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        // Navigate to the Sign In Page
        binding.btnSignInPage.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.etUsernameSignUp.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
        binding.etEmailSignUp.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        binding.etPasswordSignUp.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
        binding.etConfirmPasswordSignUp.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }

        initViews()
    }

    // Init Sign Up UI
    private fun initViews() {
        with(binding) {
            btnSignUp.setOnClickListener {
                viewModel.signUp(
                    etEmailSignUp.text?.trim().toString(),
                    etPasswordSignUp.text?.trim().toString(),
                    etConfirmPasswordSignUp.text?.trim().toString(),
                    etUsernameSignUp.text?.trim().toString()
                )
            }
        }
    }

    // Hide keyboard when press the Enter button
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