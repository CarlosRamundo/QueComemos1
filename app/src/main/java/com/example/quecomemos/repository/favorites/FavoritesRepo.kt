package com.example.quecomemos.repository.favorites

import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe

interface FavoritesRepo {
    suspend fun getFavoritesRecipe(): Result<List<Recipe>>
}