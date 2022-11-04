package com.muratcakin.poco.feature.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.muratcakin.poco.R
import com.muratcakin.poco.feature.main.SharedViewModel
import com.muratcakin.poco.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment() : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        sharedViewModel.email.observe(viewLifecycleOwner, { email ->
            binding.tvEmail.setText(email)
        })

        sharedViewModel.password.observe(viewLifecycleOwner, {password ->
            binding.tvPassword.setText(password)
        })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        when(sharedViewModel.email.value) {
            "frodobaggins@gmail.com" -> {
                binding.tvUsername.text = "Frodo"
                binding.ivUser.setImageResource(R.drawable.frodo)
            }
            "samwisegamgee@gmail.com" -> {
                binding.tvUsername.text = "Sam"
                binding.ivUser.setImageResource(R.drawable.sam)
            }
            "aragorn@gmail.com" -> {
                binding.tvUsername.text = "Aragorn"
                binding.ivUser.setImageResource(R.drawable.aragorn)
            }
            "gandalf@gmail.com" -> {
                binding.tvUsername.text = "Gandalf"
                binding.ivUser.setImageResource(R.drawable.gandalf)
            }
            else -> {
                binding.tvUsername.text = "Username"
                binding.ivUser.setImageResource(R.drawable.user)
            }
        }

        binding.btnSignOut.setOnClickListener{
            firebaseAuth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToSignInGraph()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
