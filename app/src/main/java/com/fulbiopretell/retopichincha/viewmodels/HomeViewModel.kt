package com.fulbiopretell.retopichincha.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.domain.GetFavoriteRecipesUseCase
import com.fulbiopretell.domain.GetRecipesUseCase
import com.fulbiopretell.domain.Recipe
import com.fulbiopretell.domain.ToggleFavoriteUseCase
import com.fulbiopretell.domain.UpdateRecipesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRecipesUseCase: GetRecipesUseCase,
    getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val updateRecipesFromRemoteUseCase: UpdateRecipesFromRemoteUseCase
) : ViewModel() {

    private val _allRecipes = getRecipesUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _favoriteRecipes = getFavoriteRecipesUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allRecipes: StateFlow<List<Recipe>> get() = _allRecipes
    val favoriteRecipes: StateFlow<List<Recipe>> get() = _favoriteRecipes

    fun refreshRecipes() {
        viewModelScope.launch {
            try {
                updateRecipesFromRemoteUseCase()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error updating recipes", e)
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(id)
        }
    }
}
