package com.example.quecomemos.repository.recipe

import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe

interface RecipeRepo {
    suspend fun getRecipe(): Result<List<Recipe>>
}