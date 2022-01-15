package com.example.quecomemos.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quecomemos.R
import com.example.quecomemos.core.Result
import com.example.quecomemos.data.remote.auth.AuthDataSource
import com.example.quecomemos.databinding.FragmentLoginBinding
import com.example.quecomemos.databinding.FragmentRegistrerBinding
import com.example.quecomemos.presentation.auth.AuthViewModel
import com.example.quecomemos.presentation.auth.AuthViewModelFactory
import com.example.quecomemos.repository.auth.AuthRepo
import com.example.quecomemos.repository.auth.AuthRepoImpl
import com.google.firebase.auth.FirebaseAuth

class RegistrerFragment : Fragment(R.layout.fragment_registrer) {

    private lateinit var binding: FragmentRegistrerBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrerBinding.bind(view)
        singUp()

    }

    private fun singUp() {
        binding.btnRegistrer.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            createdUser(email, pass)
        }
    }

    private fun createdUser(email: String, pass: String) {
        viewModel.signUp(email, pass).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success->{
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registrerFragment_to_homeFragment)
                }
                is Result.Failure->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error en la creación del nuevo usuario, intente nuevamente mas tarde!!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}