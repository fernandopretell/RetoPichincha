package com.fulbiopretell.domain

import javax.inject.Inject

class UpdateRecipesFromRemoteUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() {
        repository.updateRecipesFromRemote()
    }
}
