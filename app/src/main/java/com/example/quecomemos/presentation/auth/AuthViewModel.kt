package com.example.quecomemos.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.quecomemos.core.Result
import com.example.quecomemos.repository.auth.AuthRepo
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo: AuthRepo): ViewModel() {
    fun signIn(email:String, password:String)= liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.signIn(email, password)))
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }
    fun signUp(email: String, password: String)= liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.singUp(email, password)))
        }catch (e : Exception){
            emit(Result.Failure(e))
        }
    }
}

class AuthViewModelFactory(private val repo: AuthRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repo) as T
    }
}