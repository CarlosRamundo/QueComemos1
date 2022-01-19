package com.example.quecomemos.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quecomemos.R
import com.example.quecomemos.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnSaludables.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnVeganas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnGourmet.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnPastas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnCarnes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnPescados.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnRapidas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnEconomicas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnPostres.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }
        binding.btnQuecomemos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }

    }
}