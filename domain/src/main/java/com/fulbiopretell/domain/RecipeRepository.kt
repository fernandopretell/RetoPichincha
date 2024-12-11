package com.fulbiopretell.domain

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    fun getRecipeById(id: Int): Flow<Recipe?>
    suspend fun toggleFavorite(recipeId: Int)
    fun getFavoriteRecipes(): Flow<List<Recipe>>
    suspend fun updateRecipesFromRemote()
}
