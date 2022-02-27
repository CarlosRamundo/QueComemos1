package com.example.quecomemos.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.quecomemos.core.Result
import com.example.quecomemos.repository.recipe.RecipeRepo
import kotlinx.coroutines.Dispatchers

class RecipeViewModel(private val repo: RecipeRepo):ViewModel() {
    fun fetchLateRecipe() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(repo.getRecipe())
        }catch (e : Exception){
            emit(Result.Failure(e))
        }
    }
}
class RecipeViewModelFactory(private val repo: RecipeRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RecipeRepo::class.java).newInstance(repo)
    }
}