package com.fulbiopretell.retopichincha

import com.fulbiopretell.domain.GetRecipesUseCase
import com.fulbiopretell.domain.Recipe
import com.fulbiopretell.domain.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetRecipesUseCaseTest {

    @Test
    fun `getRecipes returns expected list`() = runBlocking {
        val fakeRepo = object : RecipeRepository {
            override fun getRecipes(): Flow<List<Recipe>> = flow {
                emit(listOf(Recipe(1, "Ceviche", "fake-url", "Ceviche es el mejor plato del mundo.")))
            }

            override fun getRecipeById(id: Int): Flow<Recipe?> = flow { emit(null) }
            override suspend fun toggleFavorite(recipeId: Int) {}
            override fun getFavoriteRecipes(): Flow<List<Recipe>> = flow { emit(emptyList()) }
            override suspend fun updateRecipesFromRemote() {}
        }

        val useCase = GetRecipesUseCase(fakeRepo)
        val result = useCase().toList()
        assertEquals(1, result[0].size)
        assertEquals("Test", result[0][0].name)
    }
}
