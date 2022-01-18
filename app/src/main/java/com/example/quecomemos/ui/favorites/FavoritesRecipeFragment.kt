package com.example.quecomemos.ui.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.quecomemos.R
import com.example.quecomemos.core.Result
import com.example.quecomemos.data.remote.favorites.FavoritesDataSource
import com.example.quecomemos.databinding.FragmentFavoritesRecipeBinding
import com.example.quecomemos.presentation.favorites.FavoritesRecipeViewModel
import com.example.quecomemos.presentation.favorites.FavoritesRecipeViewModelFactory
import com.example.quecomemos.repository.favorites.FavoritesRepoImpl
import com.example.quecomemos.ui.favorites.adapter.FavoritesRecipeAdapter

class FavoritesRecipeFragment : Fragment(R.layout.fragment_favorites_recipe) {

    private lateinit var binding: FragmentFavoritesRecipeBinding
    private val viewModel by viewModels<FavoritesRecipeViewModel> {
        FavoritesRecipeViewModelFactory(
            FavoritesRepoImpl(FavoritesDataSource())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesRecipeBinding.bind(view)
        viewModel.fetchLateRecipe().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success->{
                    binding.progressBar.visibility = View.GONE
                    binding.rvFavoritesRecipe.adapter = FavoritesRecipeAdapter(result.data)
                }
                is Result.Failure->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error al cargar las recetas Favoritas, intente más tarde..!!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}