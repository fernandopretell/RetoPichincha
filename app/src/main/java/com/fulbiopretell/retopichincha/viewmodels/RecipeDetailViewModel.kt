package com.fulbiopretell.retopichincha.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.domain.GetRecipeDetailUseCase
import com.fulbiopretell.domain.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    getRecipeDetailUseCase: GetRecipeDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recipeId = savedStateHandle.get<Int>("recipeId") ?: 0

    val recipe: StateFlow<Recipe?> = getRecipeDetailUseCase(recipeId).stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    )
}
