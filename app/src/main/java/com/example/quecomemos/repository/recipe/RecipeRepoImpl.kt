package com.example.quecomemos.repository.recipe

import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe
import com.example.quecomemos.data.remote.recipe.RecipeDataSource

class RecipeRepoImpl(private val dataSource : RecipeDataSource):RecipeRepo {
    override suspend fun getRecipe(): Result<List<Recipe>> = dataSource.getRecipe()
}