package com.example.quecomemos.data.remote.favorites

import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FavoritesDataSource {
    suspend fun getFavoritesRecipe():Result<List<Recipe>>{
        val recipeList = mutableListOf<Recipe>()
        val user = FirebaseAuth.getInstance().currentUser
        val recipeFavorites = FirebaseFirestore.getInstance().collection("favorites").get().await()
        for (recipe in recipeFavorites.documents){
            recipe.toObject(Recipe::class.java)?.let {
                recipeList.add(it)
            }
        }
        return  Result.Success(recipeList)
    }
}