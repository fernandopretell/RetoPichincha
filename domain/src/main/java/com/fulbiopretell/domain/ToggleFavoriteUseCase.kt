package com.fulbiopretell.domain

import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) {
        repository.toggleFavorite(recipeId)
    }
}
