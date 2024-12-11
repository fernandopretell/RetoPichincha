package com.fulbiopretell.domain


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int): Flow<Recipe?> = repository.getRecipeById(id)
}
