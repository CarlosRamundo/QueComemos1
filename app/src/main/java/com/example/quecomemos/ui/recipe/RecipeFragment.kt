package com.example.quecomemos.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.quecomemos.R
import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe
import com.example.quecomemos.data.remote.recipe.RecipeDataSource
import com.example.quecomemos.databinding.FragmentRecipeBinding
import com.example.quecomemos.presentation.recipe.RecipeViewModel
import com.example.quecomemos.presentation.recipe.RecipeViewModelFactory
import com.example.quecomemos.repository.recipe.RecipeRepoImpl
import com.example.quecomemos.ui.favorites.adapter.FavoritesRecipeAdapter

class RecipeFragment : Fragment(R.layout.fragment_recipe) {
    private lateinit var binding: FragmentRecipeBinding
    private lateinit var recipe: Recipe
    private val args by navArgs<RecipeFragmentArgs>()
    private val viewModel by viewModels<RecipeViewModel> {
        RecipeViewModelFactory(
            RecipeRepoImpl(
                RecipeDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeBinding.bind(view)
        viewModel.fetchLateRecipe().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recipeName.text = args.id.toString()
                    Log.d("id", args.id.toString())
                    if (args.id == 1) {
                        val recipeList = recipe
                        binding.progressBar.visibility = View.GONE
                        binding.recipeName.text = recipe.recipe_name
                        Glide.with(this).load(recipe.recipe_image).centerCrop()
                            .into(binding.recipeImage)
                        binding.txtIngredients.text = recipe.recipe_ingredients
                        binding.recipeDescription.text = recipe.recipe_description
                    }
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error al cargar la receta, compruebe su conexión e  intente más tarde..!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        })
    }
}