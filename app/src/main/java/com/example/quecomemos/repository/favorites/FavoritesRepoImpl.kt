package com.example.quecomemos.repository.favorites

import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe
import com.example.quecomemos.data.remote.favorites.FavoritesDataSource

class FavoritesRepoImpl(private val dataSource: FavoritesDataSource) :FavoritesRepo {
    override suspend fun getFavoritesRecipe(): Result<List<Recipe>> = dataSource.getFavoritesRecipe()
}