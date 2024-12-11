package com.fulbiopretell.data.repository

import com.fulbiopretell.data.extensions.toDomain
import com.fulbiopretell.data.local.RecipeDao
import com.fulbiopretell.data.local.entity.RecipeEntity
import com.fulbiopretell.data.remote.ApiService
import com.fulbiopretell.domain.Recipe
import com.fulbiopretell.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val apiService: ApiService
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRecipeById(id: Int): Flow<Recipe?> {
        return recipeDao.getRecipeById(id).map { it?.toDomain() }
    }

    override suspend fun toggleFavorite(recipeId: Int) {
        withContext(Dispatchers.IO) {
            val current = recipeDao.getRecipeById(recipeId).firstOrNull() ?: return@withContext
            val updated = current.copy(isFavorite = !current.isFavorite)
            recipeDao.updateRecipe(updated)
        }
    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes().map { list ->
            list.filter { it.isFavorite }.map { it.toDomain() }
        }
    }

    override suspend fun updateRecipesFromRemote() {
        withContext(Dispatchers.IO) {
            val remoteRecipesDto = apiService.getRecipes()
            val remoteRecipes = remoteRecipesDto.map { it.toDomain() }
            val entities = remoteRecipes.map { it.toEntity() }
            recipeDao.insertAll(entities)
        }
    }

   private fun Recipe.toEntity(): RecipeEntity {
        return RecipeEntity(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl,
            shortDescription = this.shortDescription,
            isFavorite = this.isFavorite
        )
    }
}

