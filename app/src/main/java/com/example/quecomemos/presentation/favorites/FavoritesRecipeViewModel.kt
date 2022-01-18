package com.example.quecomemos.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.quecomemos.core.Result
import com.example.quecomemos.repository.favorites.FavoritesRepo
import kotlinx.coroutines.Dispatchers

class FavoritesRecipeViewModel(private val repo: FavoritesRepo): ViewModel() {
    fun fetchLateRecipe() = liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            emit(repo.getFavoritesRecipe())
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }
}
class FavoritesRecipeViewModelFactory(private val repo: FavoritesRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FavoritesRepo::class.java).newInstance(repo)
    }
}