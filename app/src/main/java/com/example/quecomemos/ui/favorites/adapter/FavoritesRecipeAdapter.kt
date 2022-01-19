package com.example.quecomemos.ui.favorites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quecomemos.core.BaseViewHolder
import com.example.quecomemos.core.Result
import com.example.quecomemos.data.model.Recipe
import com.example.quecomemos.databinding.FavoritesRecipeViewBinding

class FavoritesRecipeAdapter(private val recipeList: List<Recipe>) :RecyclerView.Adapter<BaseViewHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = FavoritesRecipeViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesRecipeViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is FavoritesRecipeViewHolder->holder.bind(recipeList[position])
        }
    }

    override fun getItemCount(): Int = recipeList.size

    private inner class FavoritesRecipeViewHolder(
        val binding: FavoritesRecipeViewBinding,
        val context: Context
    ):BaseViewHolder<Recipe>(binding.root) {
        override fun bind(item: Recipe) {
            binding.nameRecipe.text = item.recipe_name
            Glide.with(context).load(item.recipe_image).centerCrop().into(binding.photoRecipe)
            binding.recipeDescription.text = item.recipe_description
        }
    }
}